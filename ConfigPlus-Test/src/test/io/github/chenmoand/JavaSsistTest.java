package io.github.chenmoand;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;

import java.io.Serializable;

public class JavaSsistTest {

    public static void main(String[] args) throws Exception {
        final ClassPool pool = ClassPool.getDefault();
        final CtClass ctClass = pool.get("io.github.chenmoand.Emj");
        final CtMethod find = ctClass.getDeclaredMethod("find");
        find.setBody("return \"卧槽这骚操作\";");
        ctClass.addInterface(pool.get("java.io.Serializable"));
        ctClass.toClass();

        final Emj emj = new Emj();
        System.out.println(emj instanceof Serializable);
        System.out.println(emj.find());
    }
}

