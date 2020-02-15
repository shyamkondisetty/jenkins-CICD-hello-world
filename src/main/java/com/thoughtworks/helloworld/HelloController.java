package com.thoughtworks.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HelloController {

    @GetMapping
    public String sayHello(String helloMessage){
        if(helloMessage==null){
            return "hello world";
        }
        return "hello "+helloMessage;
    }
}
