package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;

import java.util.List;
import java.util.Set;

interface QuestionQueryRepository {
    List<QuestionQueryDto> findAll();

    long countByCategoryAndConfirmed(final Category category, final boolean confirmed);

    Set<QuestionQueryDto> findByCategoryWithLimit(Category category, int minQuestionAmount);
}
