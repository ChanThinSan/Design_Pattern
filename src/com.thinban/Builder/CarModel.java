package com.thinban.Builder;

import java.util.ArrayList;

/**
 * 建造者模式：
 * 使用场景:1. 相同的方法，不同的执行顺序，产生不同的事件结果时 2. 多个部件或零件，都可以装配到一个对象中
 * 优点：1.  封装性  2.建造者独立，容易扩展
 * 注意事项：与工厂模式不同的是：工厂模式关心的零件的制造，而建造者模式关系的是装配，即零件是否需要和零件的顺序等。
 */
class Director {
    private ArrayList<String> sequence = new ArrayList();
    private BenzBuilder benzBuilder = new BenzBuilder();
    private BMWBuilder bmwBuilder = new BMWBuilder();

    public CarModel getABenz() {
        this.sequence.clear();
//ABenzModel的执行顺序
        this.sequence.add("start");
        this.sequence.add("stop");
//按照顺序返回一个奔驰车
        this.benzBuilder.setSequence(this.sequence);
        return (BenzModel) this.benzBuilder.getCarModel();
    }

    public CarModel getBBMW() {
        this.sequence.clear();
        this.sequence.add("engine boom");
        this.sequence.add("start");
        this.sequence.add("stop");
        this.bmwBuilder.setSequence(this.sequence);
        return (BMWModel) this.bmwBuilder.getCarModel();
    }

    public static void main(String[] args) {
        Director director = new Director();
        director.getABenz().run();
        director.getBBMW().run();
    }
}

public abstract class CarModel {
    private ArrayList<String> sequence = new ArrayList<String>();

    protected abstract void start();

    protected abstract void stop();

    protected abstract void alarm();

    protected abstract void engineBoom();

    public void run() {
        for (String s : sequence) {
            if ("start".equalsIgnoreCase(s)) {
                this.start();
            } else if ("stop".equalsIgnoreCase(s)) {
                this.stop();
            } else if ("alarm".equalsIgnoreCase(s)) {
                this.alarm();
            } else if ("engine boom".equalsIgnoreCase(s)) {
                this.engineBoom();
            } else {
                System.out.println("参数异常");
            }
        }
    }

    public void setSequence(ArrayList sequence) {
        this.sequence = sequence;
    }

}


class BenzModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("benz start");
    }

    @Override
    protected void stop() {
        System.out.println("benz stop");

    }

    @Override
    protected void alarm() {
        System.out.println("benz alarm");
    }

    @Override
    protected void engineBoom() {
        System.out.println("benz engineBoom");
    }
}

class BMWModel extends CarModel {
    @Override
    protected void start() {
        System.out.println("bmw start");
    }

    @Override
    protected void stop() {
        System.out.println("bmw stop");

    }

    @Override
    protected void alarm() {
        System.out.println("bmw alarm");
    }

    @Override
    protected void engineBoom() {
        System.out.println("bmw engineBoom");
    }
}


abstract class CarBuilder {
    public abstract void setSequence(ArrayList sequence);

    public abstract CarModel getCarModel();
}

class BenzBuilder extends CarBuilder {
    private BenzModel benz = new BenzModel();

    @Override
    public void setSequence(ArrayList sequence) {
        this.benz.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.benz;
    }
}

class BMWBuilder extends CarBuilder {
    private BMWModel bmw = new BMWModel();

    @Override
    public void setSequence(ArrayList sequence) {
        this.bmw.setSequence(sequence);
    }

    @Override
    public CarModel getCarModel() {
        return this.bmw;
    }
}






