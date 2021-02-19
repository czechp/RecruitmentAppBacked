package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Profile("development")
@Component()
class QuestionWarmup {
    private QuestionCommandService questionCommandService;

    @Autowired()
    QuestionWarmup(final QuestionCommandService questionCommandService) {
        this.questionCommandService = questionCommandService;
    }

    @EventListener(ApplicationReadyEvent.class)
    void init() {
        Question question1 = new Question("First question", Category.PLC);
        Question question2 = new Question("Second question", Category.ELECTRIC);
        Question question3 = new Question("Third question", Category.SCHEMATIC);

        Arrays.asList(
                question1,
                question2,
                question3
        ).stream()
                .forEach(element -> questionCommandService.save(element));
    }
}
