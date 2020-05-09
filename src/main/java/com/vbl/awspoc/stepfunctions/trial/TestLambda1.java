package com.vbl.awspoc.stepfunctions.trial;

public class TestLambda1 {

    public TestDto handleRequest(Object input) {
        TestDto dto = new TestDto();
        dto.setField1("fromLambda11");
        dto.setFields2("fromLambda12");
        return dto;
    }

}
