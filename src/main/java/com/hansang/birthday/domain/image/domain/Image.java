package com.hansang.birthday.domain.image.domain;

import com.hansang.birthday.domain.card.domain.BirthdayCard;
import com.hansang.birthday.domain.image.domain.ImageType;
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
public class Image extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    @Column(nullable = false)
    private String url;
    @OneToMany(mappedBy = "image", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BirthdayCard> birthdayCardList = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImageType type;
    @Builder
    public Image(String url, ImageType type) {
        this.url = url;
        this.type = type;
    }
}
