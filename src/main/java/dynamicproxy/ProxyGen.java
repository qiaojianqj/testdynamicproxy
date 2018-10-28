package dynamicproxy;

import bo.Bird;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import bo.Flyable;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @description: This is used to generate timeproxy class
 * @author: Qiao.Jian
 * @create: 2018-09-19 22:33
 */
public class ProxyGen {

    public static Object newProxyInstance() throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //生成.java源文件
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxy")
                .addSuperinterface( Flyable.class);

        FieldSpec fieldSpec = FieldSpec.builder(Flyable.class, "flyable", Modifier.PRIVATE).build();
        typeSpecBuilder.addField(fieldSpec);

        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(Flyable.class, "flyable")
                .addStatement("this.flyable = flyable")
                .build();
        typeSpecBuilder.addMethod(constructorMethodSpec);

        Method[] methods = Flyable.class.getDeclaredMethods();
        for (Method method : methods) {
            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addStatement("long start = $T.currentTimeMillis()", System.class)
                    .addCode("\n")
                    .addStatement("this.flyable." + method.getName() + "()")
                    .addCode("\n")
                    .addStatement("long end = $T.currentTimeMillis()", System.class)
                    .addStatement("$T.out.println(\"Fly Time =\" + (end - start))", System.class)
                    .build();
            typeSpecBuilder.addMethod(methodSpec);
        }

        JavaFile javaFile = JavaFile.builder("dynamicproxy.timeproxy", typeSpecBuilder.build()).build();
        javaFile.writeTo(new File ("/Users/qiaojian/Downloads/testdynamicproxy/src/main/java/"));

        //编译源文件,生成.class文件
        ProxyCompiler.compile(new File("/Users/qiaojian/Downloads/testdynamicproxy/src/main/java/dynamicproxy/timeproxy/TimeProxy.java"));

        //第一次执行main生成class文件的同时加载class文件报错：classnotfound
        //再次执行main，OK
        //Debug发现，的确在main执行完成后才生后相应的文件夹和文件 why？

        //加载class文件到内存并通过反射创建对象
        String sourcePath = "/Users/qiaojian/Downloads/testdynamicproxy/src/main/java/";
        URL[] urls = new URL[] {new URL("file:/" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass( "dynamicproxy.timeproxy.TimeProxy" );
        Constructor constructor = clazz.getConstructor( Flyable.class);
        constructor.setAccessible ( true );
        Flyable flyable = (Flyable) constructor.newInstance(new Bird ());
        flyable.fly();
        return null;
    }
}
