package com.cohad.springai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prompt/openai")
public class PromptController {

    private final ChatClient openAi;

    public PromptController(
            @Qualifier("openAiChatClient") ChatClient openAi) {
        this.openAi = openAi;
    }

    String promptTemplate = "A customer named {customerName} sent the following message: " +
            "{customerMessage}. Write a polite and helpful email response addressing the issue. " +
            "Maintain a professional tone and provide reassurance. Respond as if you're " +
            "writing the email body only. Don't include subject, signature";

    @GetMapping("/joke")
    public String chat(@RequestParam("message") String message){

        return openAi
                .prompt()
                .system("You specialize in telling jokes. " +
                        "If user asks anything else then please tell them that " +
                        "you can assist only within your defined scope")
                .user(message)
                .call().content();
    }
    @GetMapping("/email")
    public String emailResponse(@RequestParam("customerName") String customerName,
                        @RequestParam("customerMessage") String customerMessage){

        return openAi
                .prompt()
                .system("You specialize in writing email bodies " +
                        "If user asks anything else then please tell them that " +
                        "you can assist only within your defined scope.")
                .user(promptUserSpec ->
                promptUserSpec.text(promptTemplate)
                        .param("customerName", customerName)
                        .param("customerMessage", customerMessage))
                .call().content();
    }
}
