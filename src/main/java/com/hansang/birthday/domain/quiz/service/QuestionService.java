package com.hansang.birthday.domain.quiz.service;

import com.hansang.birthday.domain.quiz.domain.Question;
import com.hansang.birthday.domain.quiz.domain.repository.QuestionRepository;
import com.hansang.birthday.domain.quiz.exception.QuestionException;
import com.hansang.birthday.global.error.exception.ErrorCode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    public void deleteQuestion(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionException(ErrorCode.QUESTION_NOT_FOUND));
        questionRepository.deleteById(questionId);
    }
}
