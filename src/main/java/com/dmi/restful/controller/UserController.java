package com.dmi.restful.controller;

import com.dmi.restful.exception.UserNotFoundException;
import com.dmi.restful.model.User;
import com.dmi.restful.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    @ApiOperation(value = "Finds Users by id",
            notes = "Also returns a link to retrieve all users with rel - all-users")
    public Resource<User> retrieveUser(@PathVariable int id) {
        Optional<User> user = userService.findOne(id);

        if(!user.isPresent())
            throw new UserNotFoundException("id-"+ id);


        Resource<User> resource = new Resource<User>(user.get());

        ControllerLinkBuilder linkTo =
                linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        //HATEOAS


        return resource;
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userService.save(user);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        boolean isDeleted = userService.deleteById(id);
        if(!isDeleted)
            throw new UserNotFoundException("id-"+ id);
    }

}
