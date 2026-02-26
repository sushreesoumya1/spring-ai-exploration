package com.cohad.springai.config;

import com.cohad.springai.advisors.TokenUsageAuditAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.client.advisor.api.Advisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ChatConfiguration {

    @Bean("openAiChatClient")
    ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel)
                .defaultAdvisors(new SimpleLoggerAdvisor(), new TokenUsageAuditAdvisor())
                .build();
    }

    @Bean("chatMemoryClient")
    ChatClient chatMemoryClient(OpenAiChatModel openAiChatModel, ChatMemory chatMemory) {
        Advisor loggerAdvisor = new SimpleLoggerAdvisor();
        Advisor memoryAdvisor = MessageChatMemoryAdvisor.builder(chatMemory).build();
        return  ChatClient.builder(openAiChatModel)
                .defaultAdvisors(List.of(loggerAdvisor, memoryAdvisor))
                .build();
    }

    @Bean("ollamaLlamaClient")
    ChatClient ollamaLlamaClient(OllamaApi ollamaApi) {
        OllamaChatModel llamaModel = OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(OllamaChatOptions.builder()
                        .model("llama3.2:1b")
                        .build())
                .build();

        return ChatClient.builder(llamaModel).build();
    }
    
    @Bean("ollamaGemma3Client")
    ChatClient ollamaGemma3Client(OllamaApi ollamaApi) {
        OllamaChatModel gemmaModel = OllamaChatModel.builder()
                .ollamaApi(ollamaApi)
                .defaultOptions(OllamaChatOptions.builder()
                        .model("gemma3")
                        .build())
                .build();

        return ChatClient.builder(gemmaModel).build();
    }
}
