package com.hansang.birthday.domain.user.domain;


import com.hansang.birthday.domain.birthday.domain.Birthday;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String profileImageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false, unique = true)
    private Long kakaoId;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(length = 512)
    private String refreshToken;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Birthday> birthdayCardList = new ArrayList<>();

    @Builder
    public User(String name, String profileImageUrl, Long kakaoId,LocalDate birthday) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = LocalDateTime.now();;
        this.kakaoId = kakaoId;
        this.birthday = birthday;
    }
    public static User of(Long kakaoId, String name, String profileImageUrl, LocalDate birthday) {
        return User.builder()
                .kakaoId(kakaoId)
                .name(name)
                .profileImageUrl(profileImageUrl)
                .birthday(birthday)
                .build();
    }
    public void updateUser(String profileImageUrl, LocalDate birthday) {
        this.profileImageUrl = profileImageUrl;
        this.birthday = birthday;
    }
    public void updateNickname(String nickname) {
        this.name = nickname;
    }

    public void deleteRefreshToken() {
        this.refreshToken = null;
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}
