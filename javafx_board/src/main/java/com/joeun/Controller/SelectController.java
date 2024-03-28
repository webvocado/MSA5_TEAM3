package com.joeun.Controller;

import java.io.IOException;

import com.joeun.App;
import com.joeun.DTO.Board;
import com.joeun.Service.BoardService;
import com.joeun.Service.BoardServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    void delete(ActionEvent event) {
        //글삭제
    }

    @FXML
    void update(ActionEvent event) {

        Board board = new Board(tTitle.getText(), tWriter.getText(), tContent.getText());
        board.setNo( Integer.parseInt(tSelect.getText()) );

        boardService.update(board);
        
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

        // 원하는 게시글 잘 찾았는지 체크하는 출력문 (지워도됌)
        System.out.println(board.getTitle());
        System.out.println(board.getWriter());
        System.out.println(board.getContent());

        tTitle.setText(board.getTitle());
        tWriter.setText(board.getWriter());
        tContent.setText(board.getContent());


    }

}
