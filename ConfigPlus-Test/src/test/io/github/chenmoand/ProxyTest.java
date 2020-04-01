package io.github.chenmoand;

public class ProxyTest {

    public static void main(String[] args) {
        JdkProxy jdkProxy = new JdkProxy();
        final Brag bind = (Brag) jdkProxy.bind(new Emj());
        bind.say();

        System.out.println("---------分割线-----------");

        CglibProxy cglibProxy = new CglibProxy();
        final Emj emj = (Emj) cglibProxy.bind(new Emj());
        emj.say();


    }
}
