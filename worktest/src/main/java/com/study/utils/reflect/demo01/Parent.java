package com.study.utils.reflect.demo01;

public class Parent<Integer> {
    public String publicField = "parent_publicField";
    protected String protectField = "parent_protectField";
    String defaultField = "parent_defaultField";
    private String privateField = "parent_privateField";

    public void parent_public_method(){};
    private void parent_private_method(){};
    void parent_default_method(){};
    protected void parent_protected_method(){};
}

