package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.QuestionCommandDto;
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
        QuestionCommandDto question1 = new QuestionCommandDto(0L, "First question", Category.PLC);
        QuestionCommandDto question2 = new QuestionCommandDto(0L, "Second question", Category.ELECTRIC);
        QuestionCommandDto question3 = new QuestionCommandDto(0L, "Third question", Category.SCHEMATIC);

        Arrays.asList(
                question1,
                question2,
                question3
        ).stream()
                .forEach(element -> questionCommandService.save(element));
    }
}
