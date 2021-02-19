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
@Table(name = "questions")
@Getter()
@Setter(AccessLevel.PACKAGE)
class Question {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Question content cannot be blank")
    @Length(min = 5, max = 1000, message = "Question has to got min. 5 and max. 1000 characters")
    @Column(length = 1000)
    private String content;

    private boolean confirmed;

    private Category category;

    @PersistenceConstructor()
    Question() {
    }

    Question(@NotBlank(message = "Question content cannot be blank") @Length(min = 10, max = 1000, message = "Question has to got min. 5 and max. 1000 characters") final String content, final Category category) {
        this.content = content;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", confirmed=" + confirmed +
                ", category=" + category +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        return new EqualsBuilder().append(id, question.id).append(confirmed, question.confirmed).append(content, question.content).append(category, question.category).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(content).append(confirmed).append(category).toHashCode();
    }
}
