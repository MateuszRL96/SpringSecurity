package com.example.auth.entity;

public enum Code {

    SUCCESS("Operation end Success"),
    A1("wrong");
    public final String label;
    private Code(String label){
        this.label = label;
    }

}
