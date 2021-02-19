package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
class QuestionCommandRepositoryImpl implements QuestionCommandRepository {
    private QuestionRepository questionRepository;

    @Autowired()
    QuestionCommandRepositoryImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Question save(Question question) {
        return null;
    }
}
