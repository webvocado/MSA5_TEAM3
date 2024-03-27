package com.joeun.Service;


import com.joeun.DAO.UserDAO;
import com.joeun.DTO.User;

public class UserServiceImpl implements UserService{

    private UserDAO userDAO = new UserDAO();

    @Override
    public int insert(User user) {
        // 1. 가입 정보를 전달하여 유저 데이터 등록 요청
        int result = userDAO.insert(user);

        // 2. 적용된 데이터 건수를 반환
        //      - 결과  0 -> 데이터 등록 실패
        //              1 -> 데이터 등록 실패
        // 비즈니스 로직 예시 - if (result > 0) 포인트 추가
         return result;
    }

    @Override
    public boolean checkAccount(String id, String pw) {
        boolean result = false;
        // 1. DAO 객체에 유저 목록 요청
        result = userDAO.checkAccount(id, pw);

        // 2. 유저 목록 중, 입력된 데이터와 일치 여부 체크

        // 3. 로그인 유효성 결과 반환
        

        return result;
    }

    
    
}
