package com.example.kafkademo.controller;

import com.example.kafkademo.model.Address;
import com.example.kafkademo.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class MsgController {

    private KafkaTemplate<Long, UserDto> kafkaTemplate;

    public MsgController(KafkaTemplate<Long, UserDto> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private UserDto user = new UserDto(22L, "Ivan");

    @GetMapping
    public void sendOrder(Long msgId, UserDto userDto){
        msgId = 1L;
        userDto =  new UserDto(22L, "Ivan");
        userDto.setAddress(new Address("RUS", "Surgut", "Fadeeva", 29L, 309L));
        ListenableFuture<SendResult<Long, UserDto>> future = kafkaTemplate.send("msg", msgId, userDto);
        future.addCallback(System.out::println, System.err::println);
        kafkaTemplate.flush();
    }
}
