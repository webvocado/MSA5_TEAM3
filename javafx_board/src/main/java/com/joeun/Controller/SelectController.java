package com.joeun.Controller;

import java.io.IOException;

import com.joeun.App;
import com.joeun.DTO.Board;
import com.joeun.Service.BoardService;
import com.joeun.Service.BoardServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SelectController {

    @FXML
    private TextArea tContent;

    @FXML
    private TextField tSelect;

    @FXML
    private TextField tTitle;

    @FXML
    private TextField tWriter;

    static private BoardService boardService =  new BoardServiceImpl();

    @FXML
    void delete(ActionEvent event) throws IOException {
        //글삭제
        int no = Integer.parseInt(tSelect.getText());
        int ret = boardService.delete(no);
        if (ret != 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("삭제");
            alert.setHeaderText(null);
            alert.setContentText("게시글을 삭제했어요");
            alert.showAndWait(); // 대화상자를 표시하고 사용자 응답을 기다림
            App.setRoot("board/list");
        }

    }

    @FXML
    void update(ActionEvent event) throws IOException {
        //글수정
        int result = 0;

        Board board = new Board(tTitle.getText(), tWriter.getText(),tContent.getText());
        board.setNo( Integer.parseInt(tSelect.getText()) );
        result = boardService.update(board);

        if (result > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("수정");
            alert.setHeaderText(null);
            alert.setContentText("게시글 수정이 완료되었습니다.");
            alert.showAndWait();

            System.out.println("수정 반영완료");
            App.setRoot("board/list");
        }
    }

    @FXML
    void moveToList(ActionEvent event) throws IOException {
        // 목록보기
        App.setRoot("board/list");
    }

    @FXML
    void select(ActionEvent event) {
        Board board = new Board();
        // //조회버튼 눌렀을 때 작동할 함수
        board = boardService.select( Integer.parseInt(tSelect.getText()) );

        // 원하는 게시글 잘 찾았는지 콘솔창에 체크하는 출력문 (지워도됌)
        System.out.println(board.getTitle());
        System.out.println(board.getWriter());
        System.out.println(board.getContent());
      
        //fxml화면에 조회된 타이틀, 작성자, 내용 뿌림
        tTitle.setText(board.getTitle());
        tWriter.setText(board.getWriter());
        tContent.setText(board.getContent());
    }

    // 선택된 게시글 정보를 받아서 화면에 표시하는 메서드
    public void setBoard(Board board) {
        tSelect.setText(String.valueOf(board.getNo())); // 게시글 번호를 텍스트 필드에 설정
        tTitle.setText(board.getTitle()); // 제목 설정
        tWriter.setText(board.getWriter()); // 작성자 설정
        tContent.setText(board.getContent()); // 내용 설정
    }

    // public void message (String str) {
    //     Alert alert = new Alert(Alert.AlertType.ERROR);
    //     alert.setContentText(str);
    // }

}