package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/api/questions")
@CrossOrigin("*")
class QuestionController {
    private final QuestionCommandService questionCommandService;
    private final QuestionQueryService questionQueryService;

    @Autowired()
    QuestionController(QuestionCommandService questionCommandService, QuestionQueryService questionQueryService) {
        this.questionCommandService = questionCommandService;
        this.questionQueryService = questionQueryService;
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    List<QuestionDto> findAll(){
        return questionQueryService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    QuestionDto save(@RequestBody() @Valid final Question question){
        return questionCommandService.save(question);
    }

}
