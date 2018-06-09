package jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    // 被代理的对象要知道是谁;想比静态代理的改进就是：这里不用特别具体，可以用Object表示
    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    private void Before() {
        System.out.println("before!");
    }

    private void After() {

        System.out.println("after!");
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.Before();
        Object invoke = method.invoke(target, args);
        this.After();
        return invoke;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), // 第二个参数也可以自己new接口数组
                this); //这里的this也可以直接替换为匿名实现

        // 第三个参数用匿名实现
        /*return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), // 第二个参数也可以自己new接口数组
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
                        Before();
                        Object invoke = method.invoke(target, arguments);
                        After();
                        return invoke;
                    }
                });*/
    }

    // 可以单独写一个主类，也可以在这里写，方便
    public static void main(String[] args) {
/*        HelloImpl hello = new HelloImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(hello);

        // 第一种得到代理的思路
        Hello helloProxy = (Hello) Proxy.newProxyInstance(
                hello.getClass().getClassLoader(),
                hello.getClass().getInterfaces(), // 第二个参数也可以自己new接口数组
                dynamicProxy);*/

        // 第二种，简化的思路
        DynamicProxy dynamicProxy = new DynamicProxy(new HelloImpl());
        Hello helloProxy = (Hello) dynamicProxy.getProxy();

        helloProxy.Say("xinyufei");
/**
 * 代理3步走：
 * 1，知道代理那个类
 * 2，知道代理什么方法
 * 3，代理后干点啥
 * 4，有一个代理对象干活
 *
 * 动态代理中的1是由InvocationHandler实现类中的target字段说明的；
 * 动态代理中的3是由InvocationHandler的invoke方法说明的；
 * 动态代理中的2是由Proxy类的newProxyInstance方法的第二个参数说明的，实际是这个参数所代表的接口文件说明的
 * 动态代理中的4是由Proxy类的newProxyInstance方法动态生成的，因为方法和类都是不确定的，都是通过这个方法在运行时生成的。
 */
// staticProxy.Hello("xinyufei"); 这是静态的，用静态代理类直接调用就行；
// 但是动态代理了的特点就是动态，动态就要随机应变，给什么类，生成一个针对他的真正代理类；
// 只是填入代理的类和用代理类的方法干什么这一头一尾是由我们实现的，中间的生成真正代理类过程是JDK做的。

    }
}
