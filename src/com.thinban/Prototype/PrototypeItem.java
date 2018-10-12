package com.thinban.Prototype;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * 原型模式：（其实实用场景比较苛刻：不要final,只拷贝基本类型！）
 * Cloneable是一个方法都没有的，这个接口只是一个标记作用，在JVM中具有这个标记
 * 的对象才有可能被拷贝.
 * <p>
 * 优点：
 * ● 性能优良
 * 原型模式是在内存二进制流的拷贝，要比直接new一个对象性能好很多。
 * ● 逃避构造函数的约束
 * 这既是它的优点也是缺点，直接在内存中拷贝，【构造函数是不会执行的】
 * <p>
 * 缺点：
 * Object类提供的方法clone只是拷贝本对象，其对象内部的数组、引用对象等都不拷贝 【也就是浅拷贝】。
 * 【可以通过二进制流来实现深拷贝】
 * 【成员变量是final的clone会报错】
 */
public class PrototypeItem implements Cloneable {
    private String name;
    private int age;
    private boolean isVip;
    private ArrayList<String> arrayList = new ArrayList<String>();

    @Override
    protected PrototypeItem clone() {
        Object o = null;
        try {
            o = super.clone();
            //TODO 1.具有复杂对象和数组的时候需要在这里拷贝一遍
//            this.arrayList = (ArrayList<String>) this.arrayList.clone();
            //TODO 2. 或者通过二进制流深拷贝
//            ByteArrayOutputStream bos = new ByteArrayOutputStream();
//            ObjectOutputStream oos = new ObjectOutputStream(bos);
//            oos.writeObject(this);
//            // 反序列化
//            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
//            ObjectInputStream ois = new ObjectInputStream(bis);
//            o = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (PrototypeItem) o;
    }


    public static void main(String[] args) {
        PrototypeItem item = new PrototypeItem("thinban", 20);
        System.out.println(JSON.toJSONString(item));
        System.out.println("clone...");
        PrototypeItem cloneItem = item.clone();
        System.out.println(JSON.toJSONString(cloneItem));
        System.out.println("查看浅拷贝:(基本类型（包括string）会被拷贝，而数组和引用对象(注意valus)是不会被拷贝的)");
        cloneItem.setName("不是本人");
        cloneItem.addValue("1");
        System.out.println("item: " + JSON.toJSONString(item));
        System.out.println("cloneItem: " + JSON.toJSONString(cloneItem));
    }


    public PrototypeItem() {
        System.out.println("构造方法被执行了1");
    }

    public PrototypeItem(String name, int age) {
        System.out.println("构造方法被执行了2");
        this.name = name;
        this.age = age;
    }


    public void addValue(String s) {
        this.arrayList.add(s);
    }

    public ArrayList<String> getValus() {
        return this.arrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }


}
