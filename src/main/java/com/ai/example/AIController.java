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

    @GetMapping("/staticPrompt")
    public String getPromptResponse(@RequestParam String prompt) {

        String response = chatModel.call(prompt);
        return response;
    }


    @PostMapping(value = "/dynamicPrompt", consumes = "text/plain")
    public String receiveString(@RequestBody String input) {

        String response = chatModel.call(input);
        return response;
    }

}
