package com.learning.sarafan.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.learning.sarafan.model.Message;
import com.learning.sarafan.model.Views;
import com.learning.sarafan.repository.MessageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageController(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Message> getListMessages() {
        return messageRepository.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getMessage(@PathVariable("id") Message message) {
        return messageRepository.getOne(message.getId());
    }

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return messageRepository.save(message);
    }

    @PutMapping("{id}")
    public Message updateMessage(
            @PathVariable("id") Message messageFromDB,
            @RequestBody Message message) {
        BeanUtils.copyProperties(message, messageFromDB, "id");

        return messageRepository.save(messageFromDB);
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable("id") Message message) {
        messageRepository.delete(message);
    }
}
