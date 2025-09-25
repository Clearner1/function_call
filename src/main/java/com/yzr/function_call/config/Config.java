package com.yzr.function_call.config;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
    class Config {
        @Bean
        public ChatClient chatClient(DashScopeChatModel chatModel) {
            return ChatClient.builder(chatModel).defaultSystem("You are a friendly chat bot that answers question in the voice of a Pirate")
                    // 设置 ChatClient 中 ChatModel 的 Options 参数
                    .defaultOptions(
                            DashScopeChatOptions.builder()
                                    .withTopP(0.1)
                                    .withModel("qwen3-max")
                                    .build()
                    )
                    .build();
        }
    }