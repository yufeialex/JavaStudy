package jdkproxy;

public class StaticProxy implements Hello {

    private HelloImpl helloImpl;

    public StaticProxy(HelloImpl helloImpl) {
        this.helloImpl = helloImpl;
    }

    private void Before() {
        System.out.println("before!");
    }

    public void Say(String name) {
        this.Before();
        helloImpl.Say(name);
        this.After();
    }

    private void After() {

        System.out.println("after!");
    }

    // 可以单独写一个主类，也可以在这里写，方便
    public static void main(String[] args) {
        HelloImpl hello = new HelloImpl();
        StaticProxy helloProxy = new StaticProxy(hello);
        helloProxy.Say("xinyufei");
    }
}
