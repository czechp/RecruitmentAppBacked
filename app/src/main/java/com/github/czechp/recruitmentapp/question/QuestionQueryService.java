package com.github.czechp.recruitmentapp.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class QuestionQueryService {
    private final QuestionQueryRepository questionQueryRepository;

    @Autowired()
    QuestionQueryService(QuestionQueryRepository questionQueryRepository) {
        this.questionQueryRepository = questionQueryRepository;
    }

    public List<QuestionDto> findAll() {
        return questionQueryRepository.findAll();
    }
}
