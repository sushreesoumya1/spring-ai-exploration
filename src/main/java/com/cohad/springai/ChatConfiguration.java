package com.cohad.springai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaChatOptions;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfiguration {

    @Bean("openAiChatClient")
    ChatClient openAiChatClient(OpenAiChatModel openAiChatModel) {
        return ChatClient.builder(openAiChatModel).build();
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
