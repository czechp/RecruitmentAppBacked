package com.github.czechp.recruitmentapp.question;

import java.util.List;

public interface QuestionQueryRepository {
    List<QuestionDto> findAll();
}
