package br.com.fourdev.orderfood.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import br.com.fourdev.orderfood.model.Greeting;
import br.com.fourdev.orderfood.model.HelloMessage;

@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(180); // simulated delay
        return new Greeting(message.getName());
    }
//
//
//    @MessageMapping("/status")
//    @SendTo("/topic/greetings")
//    public Greeting greetings(HelloMessage message) throws Exception {
//        Thread.sleep(180); // simulated delay
//        return new Greeting(message.getName());
//    }


}
