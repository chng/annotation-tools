package me.test;

import me.generator.GenerateInterface;
import org.junit.Test;

/**
 * Created by OurEDA on 2016/1/1.
 */
public class AnnotationTest {

    @GenerateInterface
    public void method1() {

    }

    @GenerateInterface
    public void method1(int l) {

    }

    @Test
    public void test() {
    }
}
