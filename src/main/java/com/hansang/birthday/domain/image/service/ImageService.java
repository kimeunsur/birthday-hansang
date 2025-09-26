package com.hansang.birthday.domain.image.service;

import com.hansang.birthday.domain.image.domain.repository.ImageRepository;
import com.hansang.birthday.domain.image.dto.response.ImageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;

    public List<ImageResponse> getAllImage() {
        return imageRepository.findAll()
                .stream()
                .map(ImageResponse::of)
                .collect(Collectors.toList());
    }
}
