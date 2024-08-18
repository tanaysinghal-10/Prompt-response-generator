package com.ai.example;

import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final OllamaChatModel chatModel;

    private static final String PROMPT = "Tell me a joke in Hindi";

    public AIController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    // For getting complete output at once
    @GetMapping("/staticPrompt")
    public String getPromptResponse1(@RequestParam String prompt) {

        String response = chatModel.call(prompt);
        return response;
    }

    // For getting output in chunks (Flux of String) just like ChatGpt
    @GetMapping("/staticPromptFlux")
    public Flux<String> getPromptResponse2(@RequestParam String prompt) {

        Flux<String> response = chatModel.stream(prompt);
        return response;
    }

    // For getting a JSON response instead of Text
    @GetMapping("/staticPromptJSON")
    public Flux<ChatResponse> getPromptResponse3(@RequestParam String prompt) {

        Prompt promptActual = new Prompt(prompt);
        Flux<ChatResponse> response = chatModel.stream(promptActual);
        return response;
    }



    @PostMapping(value = "/dynamicPrompt", consumes = "text/plain")
    public String receiveString1(@RequestBody String input) {

        String response = chatModel.call(input);
        return response;
    }


    @PostMapping(value = "/dynamicPromptFlux", consumes = "text/plain")
    public Flux<String> receiveString2(@RequestBody String input) {

        Flux<String> response = chatModel.stream(input);
        return response;
    }

}
