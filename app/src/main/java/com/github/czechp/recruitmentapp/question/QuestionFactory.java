package com.github.czechp.recruitmentapp.question;

public class QuestionFactory {

    static Question dtoToPojo(QuestionDto questionDto){
        return new Question(questionDto.getContent(), questionDto.getCategory());
    }

    static QuestionDto pojoToDto(Question question){
        return new QuestionDto() {
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
        };
    }
}
