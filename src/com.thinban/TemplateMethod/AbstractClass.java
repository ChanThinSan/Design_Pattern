package com.thinban.TemplateMethod;

/***
 * 模版方法：
 * 使用场景：1. 多个子类有相同的方法,逻辑基本相同时 2.重构时经常把相同的方法抽取到父类，然后通过“钩子函数”约束其行为 3. 封装调用流程，明细由子类自己实现
 * 优点:1. 封装不变部分，扩展可变部分 2. 便于维护 3.行为由父类控制，子类实现
 */
class client {
    public static void main(String[] args) {
        AbstractClass a = new ConcreteClass1();
        AbstractClass b = new ConcreteClass2();
        a.templateMethod();
        b.templateMethod();
    }
}


public abstract class AbstractClass {
    //基本方法
    protected abstract void doSomething();

    //基本方法
    protected abstract void doAnything();

    protected boolean isAllow() {
        return true;
    }

    //模板方法
    public void templateMethod() {
        /*
         * 调用基本方法，完成相关的逻辑
         */
        this.doAnything();
        if (isAllow()) {
            this.doSomething();
        }
    }
}


class ConcreteClass1 extends AbstractClass {
    //实现基本方法
    @Override
    protected void doAnything() {
        //业务逻辑处理
        System.out.println("ConcreteClass1's doAnything");
    }

    @Override
    protected void doSomething() {
        //业务逻辑处理
        System.out.println("ConcreteClass1's doSomething");
    }
}

class ConcreteClass2 extends AbstractClass {
    public static boolean allow = false;

    @Override
    protected boolean isAllow() {
        return allow;
    }

    //实现基本方法
    @Override
    protected void doAnything() {
//业务逻辑处理
        System.out.println("ConcreteClass2's doAnything");
    }

    @Override
    protected void doSomething() {
//业务逻辑处理
        System.out.println("ConcreteClass2's doSomething");
    }
}

