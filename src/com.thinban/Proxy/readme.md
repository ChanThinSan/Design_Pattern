## 代理模式三个角色
1. Subject抽象主题角色
2. RealSubject具体主题角色
3. Proxy代理主题角色 

## 代理模式的优点
1. 职责清晰
2. 高拓展性

## 代理模式的拓展
### 普通代理normal
代理类代理作为参数传入的主题角色执行业务
### 强制代理forceproxy
由具体主题角色指定的代理来执行业务
### 动态代理dynamic
实现InvocationHandler接口
或者通过CGLIB