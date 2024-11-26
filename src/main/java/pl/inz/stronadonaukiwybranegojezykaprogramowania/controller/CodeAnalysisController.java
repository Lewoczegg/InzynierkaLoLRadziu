package pl.inz.stronadonaukiwybranegojezykaprogramowania.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.api.response.MessageResponse;
import pl.inz.stronadonaukiwybranegojezykaprogramowania.service.CodeAnalysisService;

@RestController
@RequestMapping("/CodeAnalysis")
public class CodeAnalysisController {
    private final CodeAnalysisService codeAnalysisService;

    public CodeAnalysisController(CodeAnalysisService codeAnalysisService) {
        this.codeAnalysisService = codeAnalysisService;
    }
    @PostMapping("/run-and-analyze")
    public MessageResponse runAndAnalyzeCode(@RequestBody MessageResponse messageResponse) {
        return codeAnalysisService.analyzeCode(messageResponse.getMessage());
    }
}
