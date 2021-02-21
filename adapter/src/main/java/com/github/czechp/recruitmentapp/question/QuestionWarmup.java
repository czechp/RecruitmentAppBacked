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
        QuestionDto question1 = new QuestionDto(0L, "First question", Category.PLC);
        QuestionDto question2 = new QuestionDto(0L, "Second question", Category.ELECTRIC);
        QuestionDto question3 = new QuestionDto(0L, "Third question", Category.SCHEMATIC);

        Arrays.asList(
                question1,
                question2,
                question3
        ).stream()
                .forEach(element -> questionCommandService.save(element));
    }
}
