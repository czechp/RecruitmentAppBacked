package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service()
class QuestionCommandService {
    private QuestionCommandRepository questionCommandRepository;

    @Autowired()
    QuestionCommandService(QuestionCommandRepository questionCommandRepository) {
        this.questionCommandRepository = questionCommandRepository;
    }



}
