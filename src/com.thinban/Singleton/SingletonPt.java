package com.thinban.Singleton;

/**
 * 使用场景: 1.生成唯一序列号  2. 共享访问点和共享数据  3. 创建一个对象的成本太大时（IO，网络，数据库等）
 * 注意事项：懒汉式要防止高并发问题，饿汉不会有这个问题
 * 优点：1. 减少内存开支，降低系统开销 2.避免同时写 3. 全局访问点，优化和共享资源访问
 * 缺点：1. 没有接口，拓展困难
 */
public class SingletonPt {
    private static SingletonPt singletonPt = new SingletonPt();

    private SingletonPt() {
    }

    public SingletonPt getSingletonPt() {
        return singletonPt;
    }

    public static void doSomeThing() {

    }

}

class SinglePt2 {
    //懒汉式
    private static SinglePt2 singletonPt = null;

    private SinglePt2() {
    }

    public synchronized SinglePt2 getSingletonPt() {
        if (singletonPt == null) {
            singletonPt = new SinglePt2();
        }
        return singletonPt;
    }

}
