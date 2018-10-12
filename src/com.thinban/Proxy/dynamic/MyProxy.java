package com.thinban.Proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 *
 *
 * 实现方式： 实现InvocationHandler接口。
 * CGLIB可以实现不需要接口也可以实现动态代理
 * 切面（Aspect）、切入点 * （JoinPoint）、通知（Advice）、织入（Weave）
 * springboot使用动态代理常用接口：
 *
 * @Aspect
 * @Pointcut("execution(public * com.emspost.controller.api..*.*(..))")
 * @Before("webLog()")
 * @After
 * @AfterThrowing(value = "webLog()", throwing = "exception")
 * @Around(value = "webLog()")
 * ...
 */
public class MyProxy {
    public static void main(String[] args) {
        //定义一个主题
        Subject subject = new RealSubject();
        //定义主题的代理
        Subject proxy = DynamicProxy.newProxyInstance(subject);
        //代理的行为
        proxy.doSomething("Finish");
    }
}

//封装给高层模块调用动态代理
class DynamicProxy<T> {
    public static <T> T newProxyInstance(Subject subject) {
        ClassLoader loader = subject.getClass().getClassLoader();
        Class<?>[] interfaces = subject.getClass().getInterfaces();
        InvocationHandler handler = new MyInvocationHandler(subject);
//寻找JoinPoint连接点，AOP框架使用元数据定义
        if (true) {
//执行一个前置通知
            (new BeforeAdvice()).exec();
        }
        return (T) Proxy.newProxyInstance(loader, interfaces, handler);
    }
}

interface Subject {
    //业务操作
    public void doSomething(String str);
}

class RealSubject implements Subject {
    //业务操作
    public void doSomething(String str) {
        System.out.println("do something!---->" + str);
    }
}

class MyInvocationHandler implements InvocationHandler {
    //被代理的对象
    private Object target = null;

    //通过构造函数传递一个对象
    public MyInvocationHandler(Object _obj) {
        this.target = _obj;
    }

    //代理方法
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
//执行被代理的方法
        System.out.println("调用代理方法method.invoke前");
        Object o = method.invoke(this.target, args);
        System.out.println("调用代理方法method.invoke后");
        return o;
    }
}


interface IAdvice {
    //通知只有一个方法，执行即可
    void exec();
}

class BeforeAdvice implements IAdvice {
    public void exec() {
        System.out.println("我是前置通知，我被执行了！----可以在创建代理对象前：Proxy.newProxyInstance(loader, interfaces, invocationHandler)");
    }
}


