package com.joeun.Controller;

import com.joeun.App;
import com.joeun.DTO.User;
import com.joeun.Service.UserService;
import com.joeun.Service.UserServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class RegController {
    
    @FXML
    private TextField tName;

    @FXML
    private TextField tId;
    
    @FXML
    private PasswordField tPw;

    static private boolean IdCheckComplete = false;

    static private UserService userService = new UserServiceImpl();

    // @FXML
    // void moveToLogin(ActionEvent event) throws IOException {
	// 	System.out.println("로그인 화면으로 이동합니다.");
    //     App.setRoot("user/login"); /* login.fxml 화면으로 이동 */
    // }

    // 유저 등록 처리
    @FXML
    void insert(ActionEvent event) throws Exception {
        if (IdCheckComplete) {
            // register.fxml에 입력된 데이터를 가져와서 새로운 board 객체 생성
            User user = new User(tName.getText(), tId.getText(), tPw.getText());
    
            // register.fxml에 입력된 데이터 더블체크 (지워도 됨)
            System.out.println("name: " + tName.getText());
            System.out.println("id: " + tId.getText());
            System.out.println("pw: " + tPw.getText());
    
            // 새로 생성된 user 객체를 userList에 추가
            int result = userService.insert(user);
    
            // insert()메서드의 결과 -> 0 실패, 1 성공
            if (result > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("가입 완료");
                alert.setHeaderText(null);
                alert.setContentText("가입이 완료되었습니다.");
                alert.showAndWait();

                System.out.println("회원가입 성공!");
                IdCheckComplete = false;
                App.setRoot("user/login");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("아이디 중복 체크");
            alert.setHeaderText(null);
            alert.setContentText("아이디 중복체크가 완료되지 않았습니다.");
            alert.showAndWait();
        }
    }

    @FXML
    void doubleCheck(ActionEvent event) {
        if (userService.doubleCheck(tId.getText())) { // 중복이면 doublecheck가 true 반환함
            System.out.println("아이디 중복입니다.");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("아이디 중복 체크");
            alert.setHeaderText(null);
            alert.setContentText("중복된 아이디입니다.");
            alert.showAndWait();

            tId.clear();
            tId.requestFocus();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("아이디 중복 체크");
            alert.setHeaderText(null);
            alert.setContentText("사용가능한 아이디 입니다.");
            alert.showAndWait();

            System.out.println("사용가능한 아이디 입니다.");
            tPw.requestFocus();
            IdCheckComplete = true;
        }
    }
}
