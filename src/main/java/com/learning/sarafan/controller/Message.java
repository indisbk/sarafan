package com.learning.sarafan.controller;

import com.learning.sarafan.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class Message {

    private List<Map<String, String>> messages = new ArrayList<>() {{
        add(new HashMap<>() {{put("id", "1"); put("text", "First message");}});
        add(new HashMap<>() {{put("id", "2"); put("text", "Second message");}});
        add(new HashMap<>() {{put("id", "3"); put("text", "Third message");}});
        add(new HashMap<>() {{put("id", "4"); put("text", "Forth message");}});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return messages;
    }

    @GetMapping("{id}")
    public Map<String, String> getOneMessage(@PathVariable String id) throws NotFoundException {
        return messages.stream()
                .filter(message -> message.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public List<Map<String, String>> createMessage(@RequestBody Map<String, String> message) {
        int nextId = messages.size();
        message.put("id", String.valueOf(++nextId));
        messages.add(message);
        return messages;
    }

    @PutMapping("{id}")
    public List<Map<String, String>> update(@PathVariable String id, @RequestBody Map<String, String> message) throws NotFoundException {
        getOneMessage(id);
        deleteMessage(id);
        message.put("id", id);
        messages.add(message);
        return messages;
    }

    @DeleteMapping("{id}")
    public void deleteMessage(@PathVariable String id) throws NotFoundException {
        Map<String, String> message = getOneMessage(id);

        messages.remove(message);
    }
}
