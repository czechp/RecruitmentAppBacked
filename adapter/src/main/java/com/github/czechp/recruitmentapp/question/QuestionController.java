package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/questions")
@CrossOrigin("*")
class QuestionController {
    private final QuestionCommandService questionCommandService;
    private final QuestionQueryRepository questionQueryRepository;

    @Autowired()
    QuestionController(final QuestionCommandService questionCommandService, final QuestionQueryRepository questionQueryRepository) {
        this.questionCommandService = questionCommandService;
        this.questionQueryRepository = questionQueryRepository;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<QuestionDto> findAll(){
        return questionQueryRepository.findAll();
    }

}
