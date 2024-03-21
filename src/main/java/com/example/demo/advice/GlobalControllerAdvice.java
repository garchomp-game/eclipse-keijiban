package com.example.demo.advice;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ModelAttribute("userData")
    public Map<String, Object> addUsernameToModel(Principal principal) {
        Map<String, Object> userData = new HashMap<>();
        // principalがnullでない場合のみ、名前を取得
        userData.put("username", principal != null ? principal.getName() : "Guest");
        // ログイン状態を表す論理値をMapに追加
        userData.put("hasLogin", principal != null);
        return userData;
    }
}
