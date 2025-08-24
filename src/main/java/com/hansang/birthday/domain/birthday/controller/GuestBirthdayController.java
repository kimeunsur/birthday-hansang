package com.hansang.birthday.domain.birthday.controller;


import com.hansang.birthday.domain.birthday.service.BirthdayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GuestBirthdayController {
    private final BirthdayService birthdayService;
}
