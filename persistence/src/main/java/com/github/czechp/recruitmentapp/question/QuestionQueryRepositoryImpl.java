package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
class QuestionQueryRepositoryImpl implements QuestionQueryRepository{
    private QuestionRepository questionRepository;

    @Autowired()
    QuestionQueryRepositoryImpl(final QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<QuestionDto> findAll() {
        return questionRepository.findAllBy();
    }
}
