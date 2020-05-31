package com.dmi.restful.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class HelloWorldBean {
    private final String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }
}
