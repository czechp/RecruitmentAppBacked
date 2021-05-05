package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.AnswerCommandDto;

public class AnswerFactory {

    private AnswerFactory() {
    }

    public static Answer commandDtoToPojo(AnswerCommandDto answerCommandDto) {
        return new Answer(answerCommandDto.getContent(), answerCommandDto.isCorrect());
    }
}
