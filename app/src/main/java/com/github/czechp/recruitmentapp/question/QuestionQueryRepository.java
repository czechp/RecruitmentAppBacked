package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.QuestionCommandDto;
import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;

import java.util.List;

public interface QuestionQueryRepository {
    List<QuestionQueryDto> findAll();
}
