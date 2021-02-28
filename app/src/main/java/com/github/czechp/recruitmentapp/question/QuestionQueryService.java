package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;
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

    public List<QuestionQueryDto> findAll() {
        return questionQueryRepository.findAll();
    }
}
