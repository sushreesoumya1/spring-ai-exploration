package com.cohad.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prompt")
public class PromptController {

    private final ChatClient openAi;

    public PromptController(
            @Qualifier("openAiChatClient") ChatClient openAi) {
        this.openAi = openAi;
    }
    @GetMapping("/openai")
    public String chat(@RequestParam("message") String message){

        return openAi
                .prompt()
                .system("You specialize in telling jokes. " +
                        "If user asks anything else then please tell them that " +
                        "you can assist only within your defined scope")
                .user(message)
                .call().content();
    }
}
