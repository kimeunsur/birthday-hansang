package com.hansang.birthday.fetchTypeTest;


import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.birthday.domain.repository.BirthdayRepository;
import com.hansang.birthday.domain.quiz.domain.Question;
import com.hansang.birthday.domain.quiz.domain.Quiz;
import com.hansang.birthday.domain.quiz.domain.repository.QuestionRepository;
import com.hansang.birthday.domain.quiz.domain.repository.QuizRepository;
import com.hansang.birthday.domain.user.domain.User;
import com.hansang.birthday.domain.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;


@DataJpaTest
@ActiveProfiles("test")
public class FetchTypeTest {
    @Autowired
    QuizRepository quizRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BirthdayRepository birthdayRepository;
    @Autowired
    EntityManager em;
    @Autowired
    QuestionRepository questionRepository;

    @Test
    @DisplayName("즉시 로딩으로 모든 팀 조회 시 쿼리를 관찰한다.")
    public void fetchTypeTest() {
        // given: 테스트용 Birthday + Quiz 저장
        User user = userRepository.save(User.of(11L,"test","testUrl", LocalDate.of(1998, 7, 15)));
        Birthday birthday = birthdayRepository.save(Birthday.of(2025,user,"test"));
        Quiz quiz = Quiz.of(birthday);
        quiz.addQuestion("test1",1,true);
        quiz.addQuestion("test2",2,false);
        quizRepository.save(quiz);

        em.flush();
        em.clear(); // 캐시 비우기

        //when
        List<Question> questions = questionRepository.findAll();

        //then
        for(Question question : questions){
            System.out.println("question: " + question.getContent());
            System.out.println("question: " + question.getQuiz().getId());
        }
    }

    @Test
    @DisplayName("지연 로딩으로 모든 팀 조회 시 쿼리를 관찰한다.")
    public void fetchTypeTestLazy() {
        // given: 테스트용 Birthday + Quiz 저장
        User user = userRepository.save(User.of(11L,"test","testUrl", LocalDate.of(1998, 7, 15)));
        Birthday birthday = birthdayRepository.save(Birthday.of(2025,user,"test"));
        Quiz quiz = Quiz.of(birthday);
        quiz.addQuestion("test1",1,true);
        quiz.addQuestion("test2",2,false);
        quizRepository.save(quiz);

        em.flush();
        em.clear(); // 캐시 비우기

        //when
        Quiz result = quizRepository.findAll().get(0);

        //then
        result.getQuestions().forEach(question -> {
            System.out.println("question: "+question.getContent());
        });
    }


}
