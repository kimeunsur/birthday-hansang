package com.hansang.birthday.domain.image.domain;

import com.hansang.birthday.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImageType type;
    @Builder
    public Image(String url, ImageType type) {

        this.url = url;
        this.type = type;
    }
}
