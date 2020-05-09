package com.vbl.awspoc.stepfunctions.trial;

public class TestLambda2 {

    public TestDto handleRequest(TestDto input) {
        System.out.println("input=" + input);
        input.setFields2("fromLambda22");
        return input;
    }
}
