package com.github.czechp.recruitmentapp.question.dto;

import com.github.czechp.recruitmentapp.question.Category;

import java.util.Set;

public interface QuestionQueryDto {
    long getId();

    String getContent();

    Category getCategory();

    Set<AnswerQueryDto> getAnswers();
}
