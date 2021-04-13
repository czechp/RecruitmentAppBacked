package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;

import java.util.List;

interface QuestionQueryRepository {
    List<QuestionQueryDto> findAll();

    long countByCategoryAndConfirmed(final Category category, final boolean confirmed);

    List<QuestionQueryDto> findByCategoryWithLimit(Category category, int minQuestionAmount);
}
