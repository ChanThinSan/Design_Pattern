package com.thinban.Proxy.forceproxy;

/**
 * 代理模式-强制代理：由被代理的对象强制指定使用哪个代理来处理。
 */
public class ForceGamePlayer implements IGamePlayer {

    public static void main(String[] args) {
        //定义一个游戏的角色
        IGamePlayer player = new ForceGamePlayer("张三");
//开始打游戏，记下时间戳
        //然后再定义一个代练者
        IGamePlayer proxy = player.getProxy();
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy.login("zhangSan", "password");
//开始杀怪
        proxy.killBoss();
//升级
        proxy.upgrade();
//记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
    }


    private String name = "";
    //我的代理是谁
    private IGamePlayer proxy = null;

    public ForceGamePlayer(String _name) {
        this.name = _name;
    }

    //找到自己的代理
    public IGamePlayer getProxy() {
        this.proxy = new GamePlayerProxy(this);
        return this.proxy;
    }

    //打怪，最期望的就是杀老怪
    public void killBoss() {
        if (this.isProxy()) {
            System.out.println(this.name + "在打怪！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    //进游戏之前你肯定要登录吧，这是一个必要条件
    public void login(String user, String password) {
        if (this.isProxy()) {
            System.out.println("登录名为" + user + "的用户" + this.name + "登录成功！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    //升级，升级有很多方法，花钱买是一种，做任务也是一种
    public void upgrade() {
        if (this.isProxy()) {
            System.out.println(this.name + " 又升了一级！");
        } else {
            System.out.println("请使用指定的代理访问");
        }
    }

    //校验是否是代理访问
    private boolean isProxy() {
        if (this.proxy == null) {
            return false;
        } else {
            return true;
        }
    }
}

class GamePlayerProxy implements IGamePlayer, IProxy {
    private IGamePlayer gamePlayer = null;

    //构造函数传递用户名
    public GamePlayerProxy(IGamePlayer _gamePlayer) {
        this.gamePlayer = _gamePlayer;
    }

    //代练杀怪
    public void killBoss() {
        this.gamePlayer.killBoss();
    }

    //代练登录
    public void login(String user, String password) {
        this.gamePlayer.login(user, password);
    }

    //代练升级
    public void upgrade() {
        this.gamePlayer.upgrade();
        this.calMoney();
    }

    //代理的代理暂时还没有，就是自己
    public IGamePlayer getProxy() {
        return this;
    }

    //计费
    public void calMoney() {
        System.out.println("代理赚了100元");
    }
}

interface IGamePlayer {
    void login(String user, String password);

    void upgrade();

    void killBoss();

    IGamePlayer getProxy();

}

interface IProxy {
    void calMoney();
}

