package com.study.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.study.back.model.User;
import com.study.back.service.UserService;

import java.util.Map;
import java.util.HashMap;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<String> join(@RequestBody User user) {
        System.out.println("회원가입 컨트롤러 실행" + user);
        userService.joinUser(user);
        return ResponseEntity.ok("회원가입 완료");
    }

    @GetMapping("/loginOk")
    public ResponseEntity<Map<String, String>> loginOk() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        String authorities = authentication.getAuthorities().toString();

        System.out.println("로그인한 유저 이메일:" + email);
        System.out.println("유저 권한:" + authentication.getAuthorities());

        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("email", email);
        userInfo.put("authorities", authorities);

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping("/logoutOk")
    public ResponseEntity<Void> logoutOk() {
        System.out.println("로그아웃 성공");
        return ResponseEntity.ok().build();
    }
}
