package com.cohad.springai.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private static final Logger log = LoggerFactory.getLogger(ChatController.class);

    private final ChatClient openAi;

    public ChatController(
            @Qualifier("openAiChatClient") ChatClient openAi
    ) {
        this.openAi = openAi;
    }


    @GetMapping("/openai")
    public String getOpenAiChat(@RequestParam("message") String message) {
        log.debug("message: {}", message);
        String response = openAi.prompt(message).call().content();
        log.debug("response: {}", response);
        return response;
    }

}
