package com.github.czechp.recruitmentapp.question;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
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
    @Length(min = 10, max = 1000, message = "Question has to got min. 5 and max. 1000 characters")
    @Column(length = 1000)
    private String content;

    private boolean confirmed;

    private Category category;

    @PersistenceConstructor()
    Question() {
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

    //TODO: equal and hashcode
}
