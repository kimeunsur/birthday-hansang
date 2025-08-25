package com.hansang.birthday.domain.card.domain;

import com.hansang.birthday.domain.birthday.domain.Birthday;
import com.hansang.birthday.domain.image.domain.Image;
import com.hansang.birthday.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BirthdayCard extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String message;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_image")
    private Image image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "birthday_id")
    private Birthday birthday;

    @Builder
    public BirthdayCard(String nickName, String message, Image image, Birthday birthday) {
        this.nickName = nickName;
        this.message = message;
        this.image = image;
        this.birthday = birthday;
    }
    public static BirthdayCard of(String nickName, String message, Image image, Birthday birthday) {
        return new BirthdayCard(nickName, message, image, birthday);
    }
}
