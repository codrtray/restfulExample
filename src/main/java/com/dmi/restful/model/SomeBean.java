package com.dmi.restful.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonFilter("SomeBeanFilter")
@AllArgsConstructor
@Getter
@Setter
public class SomeBean {

    private String field1;

    private String field2;

    private String field3;
}
