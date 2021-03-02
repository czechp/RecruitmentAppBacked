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

@Entity()
@Table(name = "images")
@Getter()
@Setter(AccessLevel.PACKAGE)
class Image {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "File name cannot by empty")
    @Length(min = 3, max = 20, message = "File name has to have between 3 and 20 characters")
    private String fileName;

    @NotBlank(message = "URL address cannot be empty")
    @Length(min = 5, max = 100, message = "File name has to have between 5 and 100 characters")
    private String url;

    @NotNull(message = "Question cannot be null")
    @OneToOne(fetch = FetchType.LAZY)
    private Question question;

    @PersistenceConstructor()
    Image() {
    }

    Image(@NotBlank(message = "File name cannot by empty")
          @Length(min = 3, max = 20, message = "File name has to have between 3 and 20 characters")
          final String fileName,
          @NotBlank(message = "URL address cannot be empty")
          @Length(min = 5, max = 100, message = "File name has to have between 5 and 100 characters")
          final String url) {
        this.fileName = fileName;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        return new EqualsBuilder().append(id, image.id).append(fileName, image.fileName).append(url, image.url).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(id).append(fileName).append(url).toHashCode();
    }
}
