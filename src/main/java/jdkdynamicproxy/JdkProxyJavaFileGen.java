package jdkdynamicproxy;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @description: This class is used to generate proxy java file
 * @author: Qiao.Jian
 * @create: 2018-09-19 23:24
 */
public class JdkProxyJavaFileGen {
    public static Object newProxyInstance(Class inf, JdkInvocationHandler handler) throws Exception {
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("AnyObjectProxy")
                .addModifiers( Modifier.PUBLIC)
                .addSuperinterface(inf); //inf为代理类需要实现的接口

        FieldSpec fieldSpec = FieldSpec.builder(JdkInvocationHandler.class, "handler", Modifier.PRIVATE).build();
        typeSpecBuilder.addField(fieldSpec);

        MethodSpec constructorMethodSpec = MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(JdkInvocationHandler.class, "handler") //handler为方法调用的实际处理者
                .addStatement("this.handler = handler")
                .build();

        typeSpecBuilder.addMethod(constructorMethodSpec);

        Method[] methods = inf.getDeclaredMethods();
        for (Method method : methods) {
            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addCode("try {\n")
                    .addStatement("\t$T method = " + inf.getName() + ".class.getMethod(\"" + method.getName() + "\")", Method.class)
                    // 为了简单起见，这里参数直接写死为空
                    .addStatement("\tthis.handler.invoke(this, method, null)")
                    .addCode("} catch(Throwable e) {\n")
                    .addCode("\te.printStackTrace();\n")
                    .addCode("}\n")
                    .build();
            typeSpecBuilder.addMethod(methodSpec);
        }

        JavaFile javaFile = JavaFile.builder("jdkdynamicproxy.proxy", typeSpecBuilder.build()).build();
        // 生成源文件
        String sourcePath = "/Users/qiaojian/Downloads/testdynamicproxy/src/main/java/";
        javaFile.writeTo(new File (sourcePath));

        // 编译
        JdkProxyClassFileGen.compile(new File(sourcePath + "jdkdynamicproxy/proxy/AnyObjectProxy.java"));

        // 使用反射load到内存
        URL[] urls = new URL[] {new URL("file:" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("jdkdynamicproxy.proxy.AnyObjectProxy");
        Constructor constructor = clazz.getConstructor(JdkInvocationHandler.class);
        Object obj = constructor.newInstance(handler);

        return obj;
    }
}
