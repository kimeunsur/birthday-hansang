package com.hansang.birthday.domain.image.domain.repository;

import com.hansang.birthday.domain.image.domain.Image;
import com.hansang.birthday.domain.image.domain.ImageType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findImageByUrl(String url);

    List<Image> findAllByType(ImageType type);
}

