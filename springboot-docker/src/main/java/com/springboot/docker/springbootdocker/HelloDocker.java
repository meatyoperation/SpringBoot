package com.springboot.docker.springbootdocker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/docker/hi")
public class HelloDocker {

    @GetMapping
    public String helloDocker(){
        return "Hello!!!, this is docker";
    }
}
