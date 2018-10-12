package com.thinban.Proxy.normal;


/**
 * 代理模式-普通代理
 */
class Client {
    public static void main(String[] args) {
//然后再定义一个代练者
        IGamePlayer proxy = new GamePlayerProxy("张三");
//开始打游戏，记下时间戳
        System.out.println("开始时间是：2009-8-25 10:45");
        proxy.login("zhangSan", "password");
//开始杀怪
        proxy.killBoss();
//升级
        proxy.upgrade();
//记录结束游戏时间
        System.out.println("结束时间是：2009-8-26 03:40");
    }
}

public class GamePlayer implements IGamePlayer {
    private String name = "";

    public String getName() {
        return name;
    }

    //构造函数限制谁能创建对象，并同时传递姓名
    public GamePlayer(IGamePlayer _gamePlayer, String _name) throws Exception {
        if (_gamePlayer == null) {
            throw new Exception("不能创建真实角色！");
        } else {
            this.name = _name;
        }
    }

    //打怪，最期望的就是杀老怪
    public void killBoss() {
        System.out.println(this.name + "在打怪！");
    }

    //进游戏之前你肯定要登录吧，这是一个必要条件
    public void login(String user, String password) {
        System.out.println("登录名为" + user + "的用户" + this.name + "登录成功！");
    }

    //升级，升级有很多方法，花钱买是一种，做任务也是一种
    public void upgrade() {
        System.out.println(this.name + " 又升了一级！");
    }
}

class GamePlayerProxy implements IGamePlayer, IProxy {
    private IGamePlayer gamePlayer = null;

    public String getName() {
        return null;
    }

    //通过构造函数传递要对谁进行代练
    public GamePlayerProxy(String name) {
        try {
            gamePlayer = new GamePlayer(this, name);
        } catch (Exception e) {
        }
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

    //计费
    public void calMoney() {
        System.out.println(this.gamePlayer.getName() + "消耗了100元");
    }
}

interface IGamePlayer {
    void login(String user, String password);

    void upgrade();

    void killBoss();

    String getName();
}

interface IProxy {
    void calMoney();
}
