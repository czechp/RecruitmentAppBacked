package com.github.czechp.recruitmentapp.question.dto;

import com.github.czechp.recruitmentapp.question.Category;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter()
@Setter()
public class QuestionCommandDto {

    @NotBlank(message = "Question content cannot be blank")
    @Length(min = 5, max = 1000, message = "Question has to got min. 5 and max. 1000 characters")
    private String content;

    @NotNull(message = "Category cannot be null")
    private Category category;

    public QuestionCommandDto(@NotBlank(message = "Question content cannot be blank") @Length(min = 5, max = 1000, message = "Question has to got min. 5 and max. 1000 characters") String content, @NotNull(message = "Category cannot be null") Category category) {
        this.content = content;
        this.category = category;
    }
}
