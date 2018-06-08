package cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {

    private static CGLibProxy cgLibProxy = new CGLibProxy();

    private CGLibProxy() {}

    public static CGLibProxy getInstance() {
        return cgLibProxy;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);  // 这里的this也可以换成匿名对象
    }

    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        Before();
        Object result = methodProxy.invokeSuper(o, objects);
        After();
        return result;
    }

    private void Before() {
        System.out.println("before!");
    }

    private void After() {

        System.out.println("after!");
    }

    public static void main(String[] args) {
        JustHello proxy = CGLibProxy.getInstance().getProxy(JustHello.class);
        proxy.Say("ljw");
    }
}
