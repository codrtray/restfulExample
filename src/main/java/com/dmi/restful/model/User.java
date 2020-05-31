package com.dmi.restful.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ApiModel(value="User Details", description="Contains all details of a user")
public class User {

    private Integer id;

    @Size(min = 2, message="Name should have atleast 2 characters")
    @ApiModelProperty(notes = "Name should have atleast 2 characters")
    private String name;

    @Past
    @ApiModelProperty(notes = "Birth Date should be in the Past")
    private Date birthDate;
}
