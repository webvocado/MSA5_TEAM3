package com.joeun.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.joeun.App;
import com.joeun.DTO.Board;
import com.joeun.Service.BoardService;
import com.joeun.Service.BoardServiceImpl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.input.MouseEvent;

public class ListController implements Initializable {
	@FXML
	public TableView<Board> boardTableView;

	@FXML
	public TableColumn<Board, Integer> colNo;
	@FXML
	public TableColumn<Board, String> colTitle;
	@FXML
	public TableColumn<Board, String> colWriter;
	@FXML
	public TableColumn<Board, String> colRegDate;
	@FXML
	public TableColumn<Board, String> colView;
	@FXML
	private TextField tSearch;

	static private BoardService boardService = new BoardServiceImpl();

	@FXML
	void moveToInsert(ActionEvent event) throws IOException {
		System.out.println("글쓰기 화면으로 이동합니다.");
		App.setRoot("board/insert"); /* Insert.fxml 화면으로 이동 */
	}

	@FXML
	void moveToSelect(ActionEvent event) throws IOException {
		System.out.println("조회 화면으로 이동합니다.");
		App.setRoot("board/select"); /* SELECT.fxml 화면으로 이동 */

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// 게시글 목록 조회
		List<Board> boardList = boardService.list();

		/* 제목 열 이름 세팅 */
		colNo.setCellValueFactory(new PropertyValueFactory<>("No"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
		colWriter.setCellValueFactory(new PropertyValueFactory<>("Writer"));
		colRegDate.setCellValueFactory(new PropertyValueFactory<>("RegDate"));
		colView.setCellValueFactory(new PropertyValueFactory<>("View"));

		// 테이블뷰에 데이터 추가하기
		ObservableList<Board> list = FXCollections.observableArrayList(
				boardList);

		boardTableView.setItems(list);

		// 테이블뷰 더블 클릭 이벤트
		boardTableView.setOnMouseClicked((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2 && boardTableView.getSelectionModel().getSelectedItem() != null) {
					System.out.println("더블 클릭");
					int boardNo = boardTableView.getSelectionModel().getSelectedItem().getNo();
					try {
						FXMLLoader loader = new FXMLLoader(App.class.getResource("board/select.fxml"));
						Parent selectRoot = loader.load();
						SelectController selectController = loader.getController();
						selectController.setBoard(boardService.select(boardNo)); // 선택된 게시글 정보 전달

						Scene currentScene = boardTableView.getScene();
						currentScene.setRoot(selectRoot);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

	}

}
