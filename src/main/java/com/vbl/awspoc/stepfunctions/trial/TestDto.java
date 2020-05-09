package com.vbl.awspoc.stepfunctions.trial;

public class TestDto {
    private String field1;
    private String fields2;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getFields2() {
        return fields2;
    }

    public void setFields2(String fields2) {
        this.fields2 = fields2;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "field1='" + field1 + '\'' +
                ", fields2='" + fields2 + '\'' +
                '}';
    }
}
