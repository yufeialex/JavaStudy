package jdkproxy;

public class HelloImpl implements Hello {
    public void Say(String name) {
        System.out.println("Hello, " + name);
    }
}
