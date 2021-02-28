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
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull(message = "Category cannot be null")
    private Category category;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();


    @PersistenceConstructor()
    Question() {
    }

    Question(@NotBlank(message = "Question content cannot be blank") @Length(min = 10, max = 1000, message = "Question has to got min. 5 and max. 1000 characters") final String content, final Category category) {
        this.content = content;
        this.category = category;
    }


    public Set<Answer> getAnswers() {
        return Collections.unmodifiableSet(answers);
    }

    void addAnswer(Answer answer) {
        if (answers.size() < 4) {
            this.answers.add(answer);
            answer.setQuestion(this);
        }
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

    void removeAnswer(Answer answer) {
        if (answers.contains(answer)) {
            answers.remove(answer);
            answer.setQuestion(null);
        }
    }
}
