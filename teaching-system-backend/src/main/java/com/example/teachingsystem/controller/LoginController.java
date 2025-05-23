
package com.example.teachingsystem.controller;

import com.example.teachingsystem.common.JwtUtils;
import com.example.teachingsystem.common.Result;
import com.example.teachingsystem.entity.Teacher;
import com.example.teachingsystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody Map<String, String> loginForm) {
        String username = loginForm.get("username");
        String password = loginForm.get("password");
        Teacher teacher = teacherService.login(username, password);
        if (teacher != null) {
            String token = JwtUtils.generateToken(username);
            Map<String, Object> result = new HashMap<>();
            result.put("token", token);
            result.put("name", teacher.getName());
            return Result.success(result);
        } else {
            return Result.error("用户名或密码错误");
        }
    }
}
