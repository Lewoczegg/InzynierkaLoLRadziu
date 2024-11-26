package pl.inz.stronadonaukiwybranegojezykaprogramowania.service;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.MessageResponse;

@Service
public class CodeAnalysisService {
    @Autowired
    private OllamaChatModel chatModel;

    public CodeAnalysisService() {
        OllamaApi ollamaApi = new OllamaApi("http://localhost:11434/");
        this.chatModel = new OllamaChatModel(ollamaApi);
    }

    public MessageResponse analyzeCode(String userCode){
        String prompt = String.format(
                "Analyze the following code in detail:\n\n" +
                        "1. Explain what the code does.\n" +
                        "2. Identify any errors or potential issues in the code.\n" +
                        "3. Provide suggestions on how to improve or optimize the code.\n" +
                        "4. Estimate the time complexity of the code.\n" +
                        "5. Estimate the space complexity of the code.\n" +
                        "Code:\n%s\n\n",
                userCode
        );

        ChatResponse response = chatModel.call(
                new Prompt(
                        prompt,
                        OllamaOptions.create()
                                .withModel("codellama")
                ));
        return new MessageResponse(response.getResult().getOutput().getContent());
    }
}
