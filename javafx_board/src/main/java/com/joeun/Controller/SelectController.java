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

    // @FXML
    // void update(ActionEvent event) {
    //     Board board = new Board(tTitle.getText(), tWriter.getText(), tContent.getText());
    //     // 조회해서 입력해서 조회한 게시글 그대로...
    //     board = boardService.select( Integer.parseInt(tSelect.getText()) );
        
    //     //수정 전
    //     System.out.println("수정 전------");
    //     System.out.println(board.getTitle());
    //     System.out.println(board.getWriter());
    //     System.out.println(board.getContent());

    //     // 입력창에 입력된 대로 수정하기
    //     board.setTitle(tTitle.getText());
    //     board.setWriter(tWriter.getText());
    //     board.setContent(tContent.getText());
    //     /* upd_date는 BoardDAO의 update 함수 내 쿼리문에서 sysdate로 알아서 변경됨 */

    //     boardService.update(board); /*수정하는 함수 */
    //     System.out.println("수정 후---");
    //     System.out.println(board.getTitle());
    //     System.out.println(board.getWriter());
    //     System.out.println(board.getContent());
    // }

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
