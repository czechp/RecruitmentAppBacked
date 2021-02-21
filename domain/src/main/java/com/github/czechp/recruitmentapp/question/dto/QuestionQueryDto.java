package com.github.czechp.recruitmentapp.question.dto;

import com.github.czechp.recruitmentapp.question.Category;

public interface QuestionQueryDto {
    long getId();
    String getContent();
    Category getCategory();
}
