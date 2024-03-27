package com.joeun.Controller;

import com.joeun.App;
import com.joeun.DTO.Board;
import com.joeun.Service.BoardService;
import com.joeun.Service.BoardServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InsertController {

    @FXML
    private TextArea tContent;

    @FXML
    private TextField tTtile;

    @FXML
    private TextField tWriter;

    private BoardService boardService = new BoardServiceImpl();

    // @FXML
    // void moveToList(ActionEvent event) throws IOException {
    //     App.setRoot("board/List");
    // }

    // 글 등록 처리
    public void insert(ActionEvent event) throws Exception {
        // insert.fxml에 입력된 데이터를 가져와서 새로운 board 객체 생성
        Board board = new Board(tTtile.getText(), tWriter.getText(), tContent.getText());

        // insert.fxml에 입력된 데이터 더블체크 (지워도 됨)
        System.out.println("title: " +  tTtile.getText());
        System.out.println("writer: " +  tWriter.getText());
        System.out.println("content: " +  tContent.getText());

        // 새롭게 생성된 board 객체를 boardList에 추가
        int result = boardService.insert(board);

        // insert() 메서드
        // 결과 : 0 --> 데이터 등록 실패
		//       1 --> 데이터 등록 성공
        if ( result > 0 ) {
            System.out.println("글쓰기 처리 성공!");
            // 게시글 목록 화면(메인)으로 이동
            App.setRoot("board/List");
        }
    }
}
