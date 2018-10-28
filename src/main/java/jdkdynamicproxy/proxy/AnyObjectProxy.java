package jdkdynamicproxy.proxy;

import bo.Flyable;
import java.lang.Override;
import java.lang.reflect.Method;
import jdkdynamicproxy.JdkInvocationHandler;

public class AnyObjectProxy implements Flyable {
  private JdkInvocationHandler handler;

  public AnyObjectProxy(JdkInvocationHandler handler) {
    this.handler = handler;
  }

  @Override
  public void fly() {
    try {
    	Method method = bo.Flyable.class.getMethod("fly");
    	this.handler.invoke(this, method, null);
    } catch(Throwable e) {
    	e.printStackTrace();
    }
  }
}
