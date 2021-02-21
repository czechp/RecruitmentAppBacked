package com.github.czechp.recruitmentapp.question;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.PersistenceConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity()
@Table(name = "answers")
@Getter()
@Setter(AccessLevel.PACKAGE)
class Answer {
    @Id()
    @GeneratedValue()
    private long id;

    @NotBlank(message = "Answer cannot be blank")
    @Length(min = 5, max = 300, message = "Number of characters in the answer should be between 5 and 300")
    private String content;

    private boolean correct;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @PersistenceConstructor()
    public Answer() {
    }

    public Answer(@NotBlank(message = "Answer cannot be blank") @Length(min = 5, max = 300, message = "Number of characters in the answer should be between 5 and 300") String content, boolean correct) {
        this.content = content;
        this.correct = correct;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", correct=" + correct +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        return new EqualsBuilder().append(id, answer.id).append(correct, answer.correct).append(content, answer.content).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(content).append(correct).toHashCode();
    }
}
