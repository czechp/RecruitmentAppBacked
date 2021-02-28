package com.github.czechp.recruitmentapp.question.dto;

public interface AnswerQueryDto {
    long getId();

    String getContent();

    boolean isCorrect();
}
