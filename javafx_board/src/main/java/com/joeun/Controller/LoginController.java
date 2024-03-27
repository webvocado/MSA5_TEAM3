package com.joeun.Controller;

import java.io.IOException;

import com.joeun.App;
import com.joeun.Service.UserService;
import com.joeun.Service.UserServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class LoginController {

    @FXML
    private TextField tId;

    @FXML
    private PasswordField tPw;


    static private UserService userService = new UserServiceImpl();

    @FXML
    public void checkAccount (ActionEvent event) throws Exception {
        boolean result = false;
        System.out.println("checkAccount 함수 발동");
        System.out.println(tId.getText());
        System.out.println(tPw.getText());
        // login.fxml에 입력된 데이터를 가져와서 유저리스트와 비교
        result = userService.checkAccount(tId.getText(), tPw.getText());

        if (result) {
            moveToMain(event);
        } else {
            System.out.println("유효하지 않은 계정이거나, 비밀번호가 올바르지 않습니다.");
            tPw.clear();
            tPw.requestFocus();
        }
    }


    @FXML
    void moveToMain(ActionEvent event) throws IOException {
        App.setRoot("board/list");
    }

    @FXML
    void moveToReg(ActionEvent event) throws IOException {
        App.setRoot("user/reg");
    }


    public void message(String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(content);
        alert.show();
    }
}
