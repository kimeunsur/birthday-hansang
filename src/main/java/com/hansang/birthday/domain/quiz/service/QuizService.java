package com.hansang.birthday.domain.quiz.service;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.birthday.domain.repository.BirthdayRepository;
import com.hansang.birthday.domain.birthday.exception.BirthdayException;
import com.hansang.birthday.domain.quiz.domain.Quiz;
import com.hansang.birthday.domain.quiz.domain.repository.QuizRepository;
import com.hansang.birthday.domain.quiz.dto.request.QuizRequest;
import com.hansang.birthday.domain.quiz.dto.response.QuizResponse;
import com.hansang.birthday.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuizService {
    private final BirthdayRepository birthdayRepository;
    private final QuizRepository quizRepository;

    public QuizResponse createQuiz(QuizRequest request, Long userId) {
        Birthday birthday = birthdayRepository.findById(request.getBirthdayId())
                .orElseThrow(() -> new BirthdayException(ErrorCode.BIRTHDAY_NOT_FOUND));
        if (birthday.getUser().getUserId() != userId) {
            throw new BirthdayException(ErrorCode.UNAUTHORIZED_BIRTHDAY_ACCESS);
        }
        if (birthday.getQuiz() != null)
            throw new BirthdayException(ErrorCode.QUIZ_ALREADY_EXISTS);
        Quiz quiz = Quiz.of(birthday);

        request.getQuestions().forEach(ques -> {
            quiz.addQuestion(ques.getContent(), ques.getSequence(), ques.getAnswer());
        });
        return QuizResponse.of(quizRepository.save(quiz));
    }
}
