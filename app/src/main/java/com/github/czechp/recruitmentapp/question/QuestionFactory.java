package com.github.czechp.recruitmentapp.question;

public class QuestionFactory {

    static Question dtoToPoJo(QuestionDto questionDto) {
        return new Question(questionDto.getContent(), questionDto.getCategory());
    }

    static QuestionDto poJoToDto(Question question) {
        return new QuestionDto(question.getId(), question.getContent(), question.getCategory());
    }

}
