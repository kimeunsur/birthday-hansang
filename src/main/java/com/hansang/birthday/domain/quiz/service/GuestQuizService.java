package com.hansang.birthday.domain.quiz.service;


import com.hansang.birthday.domain.quiz.domain.GuestQuiz;
import com.hansang.birthday.domain.quiz.domain.Question;
import com.hansang.birthday.domain.quiz.domain.Quiz;
import com.hansang.birthday.domain.quiz.domain.repository.QuestionRepository;
import com.hansang.birthday.domain.quiz.domain.repository.QuizRepository;
import com.hansang.birthday.domain.quiz.domain.repository.QuizResponseContentRepository;
import com.hansang.birthday.domain.quiz.domain.repository.QuizResponseRepository;
import com.hansang.birthday.domain.quiz.dto.request.GuestQuestionRequest;
import com.hansang.birthday.domain.quiz.dto.response.GuestQuizResultResponse;
import com.hansang.birthday.domain.quiz.dto.response.QuizRankingResponse;
import com.hansang.birthday.domain.quiz.exception.QuizException;
import com.hansang.birthday.global.error.exception.ErrorCode;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuestQuizService {
    private final QuizResponseRepository quizResponseRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuizService quizService;

    @Transactional
    public GuestQuizResultResponse registerQuiz(String nickname, Long quizId, List<GuestQuestionRequest> reqs) {

        //결과 저장하기
        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizException(ErrorCode.QUIZ_NOT_FOUND));
        int score=0;
        GuestQuiz guestQuiz = GuestQuiz.of(nickname, quiz);
        Map<Long, Question> questionMap = questionRepository.findAllById(
                reqs.stream().map(GuestQuestionRequest::getQuestionId).toList())
                .stream()
                .collect(Collectors.toMap(Question::getId, q -> q));
        for (GuestQuestionRequest req : reqs) {
            Question question = Optional.ofNullable(questionMap.get(req.getQuestionId()))
                    .orElseThrow(() -> new QuizException(ErrorCode.QUESTION_NOT_FOUND));
            if(!question.getQuiz().getId().equals(quizId)) throw new QuizException(ErrorCode.QUESTION_NOT_IN_QUIZ);
            boolean correct= (question.getAnswer()==req.isAnswer());
            if(correct)score++;
            guestQuiz.addGuestQuizContent(question,req.isAnswer(),correct);
        }
        guestQuiz.updateScore(score);
        quizResponseRepository.save(guestQuiz);

        //랭킹 조회하기
        List<QuizRankingResponse> ranking = quizService.getRanking(quizId);

        return GuestQuizResultResponse.of(guestQuiz.getGuestQuizId(), score, ranking);

    }

}
