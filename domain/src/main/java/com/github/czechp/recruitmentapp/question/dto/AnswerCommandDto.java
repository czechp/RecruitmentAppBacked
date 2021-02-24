package com.github.czechp.recruitmentapp.question.dto;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Getter()
@Setter()
public class AnswerCommandDto {
    @NotBlank(message = "Content cannot be empty")
    @Length(min = 5, max = 300, message = "Answer must got between 5 and 300 characters")
    private String content;
    private boolean correct;

    public AnswerCommandDto(@NotBlank(message = "Content cannot be empty") @Length(min = 5, max = 300, message = "Answer must got between 5 and 300 characters") final String content, final boolean correct) {
        this.content = content;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "AnswerCommandDto{" +
                "content='" + content + '\'' +
                ", correct=" + correct +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AnswerCommandDto that = (AnswerCommandDto) o;

        return new EqualsBuilder().append(correct, that.correct).append(content, that.content).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(content).append(correct).toHashCode();
    }
}
