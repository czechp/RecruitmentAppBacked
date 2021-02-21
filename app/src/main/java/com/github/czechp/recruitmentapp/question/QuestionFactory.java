package com.github.czechp.recruitmentapp.question;

import com.github.czechp.recruitmentapp.question.dto.AnswerQueryDto;
import com.github.czechp.recruitmentapp.question.dto.QuestionCommandDto;
import com.github.czechp.recruitmentapp.question.dto.QuestionQueryDto;

import java.util.HashSet;
import java.util.Set;

public class QuestionFactory {

    static Question dtoToPoJo(QuestionCommandDto questionCommandDto) {
        return new Question(questionCommandDto.getContent(), questionCommandDto.getCategory());
    }

    static Question commandDtoToPojo(QuestionCommandDto questionCommandDto) {
        return new Question(questionCommandDto.getContent(), questionCommandDto.getCategory());
    }

    static QuestionQueryDto poJoToQueryDto(Question question) {
        return new QuestionQueryDto() {
            @Override
            public long getId() {
                return question.getId();
            }

            @Override
            public String getContent() {
                return question.getContent();
            }

            @Override
            public Category getCategory() {
                return question.getCategory();
            }

            @Override
            public Set<AnswerQueryDto> getAnswers() {
                return new HashSet<>();
            }
        };
    }

}
