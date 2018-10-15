package com.thinban.Command;

/**
 * 命令模式：三个角色（接受者，命令角色，调用者）
 * 使用场景：你认为有命令的地方就可以使用命令模式
 * 优点：类间解耦，可拓展性，命令模式可以结合责任链模式，实现命令族解析任务；结合模板方法模式，则可以减少
 * Command子类的膨胀问题。
 * 缺点：command子类膨胀
 */
public class Invoker {
    private Command command;

    //受气包，接受命令
    public void setCommand(Command _command) {
        this.command = _command;
    }

    //执行命令
    public void action() {
        this.command.execute();
    }

    public static void main(String[] args) {
//首先声明调用者Invoker
        Invoker invoker = new Invoker();
//        定义接收者//TODO 通过setter把接收者注入命令实现类中的话，就可以指定接收者
//        Receiver receiver = new ConcreteReciver1();
//定义一个发送给接收者的命令
        Command command = new ConcreteCommand1();
//把命令交给调用者去执行
        invoker.setCommand(command);
        invoker.action();
        Command command2 = new ConcreteCommand2();
        invoker.setCommand(command2);
        invoker.action();
    }
}


/**
 * 抽象接受者：干活的
 */
abstract class Receiver {
    //抽象接收者，定义每个接收者都必须完成的业务
    public abstract void doSomething();
}


class ConcreteReciver1 extends Receiver {
    //每个接收者都必须处理一定的业务逻辑
    @Override
    public void doSomething() {
        System.out.println("ConcreteReciver1 was done");
    }
}

class ConcreteReciver2 extends Receiver {
    //每个接收者都必须处理一定的业务逻辑
    @Override
    public void doSomething() {
        System.out.println("ConcreteReciver2 was done");
    }
}

/**
 * 抽象命令
 */
abstract class Command {
    //定义一个子类的全局共享变量
    protected final Receiver receiver;

    //实现类必须定义一个接收者
    public Command(Receiver _receiver) {
        this.receiver = _receiver;
    }

    //每个命令类都必须有一个执行命令的方法
    public abstract void execute();
}

class ConcreteCommand1 extends Command {

    //构造函数传递接收者
    public ConcreteCommand1() {
        super(new ConcreteReciver1());
    }

    //必须实现一个命令
    @Override
    public void execute() {
//业务处理
        this.receiver.doSomething();
    }
}

class ConcreteCommand2 extends Command {

    //构造函数传递接收者
    public ConcreteCommand2() {
        super(new ConcreteReciver2());
    }

    @Override
    public void execute() {
        this.receiver.doSomething();
    }
}
