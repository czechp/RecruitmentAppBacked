package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
class QuestionCommandService {
    private QuestionCommandRepository questionCommandRepository;

    @Autowired()
    QuestionCommandService(QuestionCommandRepository questionCommandRepository) {
        this.questionCommandRepository = questionCommandRepository;
    }

    Question save(Question question){
        return questionCommandRepository.save(question);
    }
}
