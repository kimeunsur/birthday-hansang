package com.hansang.birthday.domain.birthday.domain;

import com.hansang.birthday.domain.card.domain.BirthdayCard;
import com.hansang.birthday.domain.quiz.domain.Quiz;
import com.hansang.birthday.domain.user.domain.User;
import com.hansang.birthday.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Birthday extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "birthday_id")
    private Long id;
    @Column(name = "birth_year", nullable = false)
    private Integer year;
    @Column(name = "brand_gift_entry")
    private Boolean brandGiftEntry;
    @Column(name = "quiz_entry")
    private Boolean quizEntry;
    @Column(name = "is_open")
    private Boolean isOpen;
    @OneToOne(mappedBy = "birthday", cascade = CascadeType.ALL, orphanRemoval = true)
    private Quiz quiz;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private String code;
    @OneToMany(mappedBy = "birthday", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BirthdayCard> birthdayCards = new ArrayList<>();
    @Column(name = "is_visible")
    private Boolean isVisible;

    @Builder
    public Birthday (Boolean brandGiftEntry, Integer year, Boolean quizEntry, Boolean isOpen, User user, String code, Boolean isVisible) {
        this.brandGiftEntry = brandGiftEntry == null ? false : brandGiftEntry;
        this.year = year;
        this.quizEntry = quizEntry ==null ? false : quizEntry;
        this.isOpen = isOpen == null ? false : isOpen;
        this.user = user;
        this.code = code;
        this.isVisible = isVisible == null ? true : isVisible;
    }
    public static Birthday of (Integer year, User user, String code) {
        return new Birthday(null, year, null, null, user, code, null);
    }
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
