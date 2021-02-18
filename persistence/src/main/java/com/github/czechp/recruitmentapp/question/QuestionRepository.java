package com.github.czechp.recruitmentapp.question;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository()
interface QuestionRepository  extends JpaRepository<Question, Long> {

}
