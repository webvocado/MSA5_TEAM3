package com.joeun.Service;


import com.joeun.DTO.User;

public interface UserService {

    // 유저 계정유효여부 체크 (유저목록)
    boolean checkAccount(String id, String pw);

    // 회원가입 (유저등록)
    int insert(User user);

    // 회원가입 시 아이디 중복체크
    boolean doubleCheck(String inputId);

    
    
} 
