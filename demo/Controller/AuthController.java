package com.example.demo.Controller;

import com.example.demo.Model.MyUser;
import com.example.demo.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    private ResponseEntity getAllMyUsers(){
        List<MyUser> allUsers=  authService.getAllUsers();
        return ResponseEntity.status(200).body(allUsers);
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody MyUser myUser){
        authService.register(myUser);
        return ResponseEntity.status(200).body("User Register");
    }
    @PostMapping("/admin")
    public ResponseEntity admin(){
        return ResponseEntity.status(200).body("Welcome Admin");
    }
    @PostMapping("/user")
    public ResponseEntity user(){
        return ResponseEntity.status(200).body("Welcome user");
    }
    @PostMapping("/login")
    public ResponseEntity login(){
        return ResponseEntity.status(200).body("login");
    }
    @PostMapping("/logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("logout");
    }
}


