package com.eslirodrigues.app_spring_ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class ChatController {

    private final ChatClient chatClient;

    @Autowired
    public ChatController(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @GetMapping("/ai/generate")
    public ChatResponse generate() {
        return chatClient.prompt()
                .user("Tell me a short joke")
                .call()
                .chatResponse();
    }

    @GetMapping("/ai/generateStream")
    public Flux<String> generateStream() {
        return  chatClient.prompt()
                .user("Tell me a car name")
                .stream()
                .content();
    }
}