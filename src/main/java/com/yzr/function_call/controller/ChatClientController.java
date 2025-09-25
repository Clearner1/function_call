package com.yzr.function_call.controller;


import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/test")
public class ChatClientController {

    private final ChatClient chatClient;

    public ChatClientController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

////    alibaba对chatModel重新封装
//    public ChatClientController(DashScopeChatModel chatModel) {
//        this.chatClient = ChatClient.builder(chatModel)
////                配置模型
//                .defaultOptions(DashScopeChatOptions.builder()
//                        .withModel("qwen3-max")
//                        .build())
//                .build();
//    }


    @GetMapping
    public Flux<String> stream(@RequestParam("user_prompt") String user_prompt,
                               @RequestHeader(value = "model", required = false) String model,
                               HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return chatClient.prompt(user_prompt)
                .options(DashScopeChatOptions.builder()
                        .withModel(model)
                        .build()
                ).stream()
                .content();
    }



}
