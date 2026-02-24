package com.cohad.springai.controller;

import com.cohad.springai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
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

    @Value("classpath:/PromptTemplates/emailResponse.st")
    private Resource emailResponseResource;
    @Value("classpath:/PromptTemplates/LeavePolicy.st")
    private Resource leavePolicyResource;

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
                promptUserSpec.text(emailResponseResource)
                        .param("customerName", customerName)
                        .param("customerMessage", customerMessage))
                .call().content();
    }

    @GetMapping("/leave")
    public String promptStuffing(@RequestParam("message") String message){
        return openAi
                .prompt()
                .system(leavePolicyResource)
                .user(message)
                .call().content();
        //Toke usage details: DefaultUsage{promptTokens=193, completionTokens=1251, totalTokens=1444}
    }
}
