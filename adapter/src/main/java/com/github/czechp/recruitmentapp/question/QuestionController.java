package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/questions")
@CrossOrigin("*")
class QuestionController {
    private QuestionCommandService questionCommandService;

    @Autowired()
    QuestionController(QuestionCommandService questionCommandService) {
        this.questionCommandService = questionCommandService;
    }




    @EventListener(ApplicationReadyEvent.class)
    void init(){
        System.out.println("----------------------Application ready test------------------------");
    }
}
