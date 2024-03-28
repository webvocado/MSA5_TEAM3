package com.joeun.DAO;

import com.joeun.DTO.User;

/**
 *  데이터 접근 객체
 *  - 유저 데이터 접근
 */

public class UserDAO extends JDBConnection {
    // 회원가입
    public int insert(User userList) {
        int result = 0;

        // 유저 테이블에 담을 수 있도록 SQL문 작성
        String sql = " INSERT INTO users (name, id, pw) "
                    + " VALUES ( ?, ?, ? ) ";
        
        try {
            psmt = con.prepareStatement(sql);
            psmt.setString(1, userList.getName());
            psmt.setString(2, userList.getId());
            psmt.setString(3, userList.getPw());

            result = psmt.executeUpdate();


        } catch (Exception e) {
            System.err.println("회원 가입 시, 예외 발생");
			e.printStackTrace();
        }

        return result;
    }

    // 유저 체크
    public boolean checkAccount(String id, String pw) {

        boolean IsExist = false;

        // SQL 작성
        String sql = " SELECT * "
				   + " FROM users "
                   + " WHERE id = ? "
                   + "   and pw = ? ";

        try {
            // 쿼리(SQL) 실행 객체 생성 - PreparedStatement (psmt)
			psmt = con.prepareStatement(sql);

            // psmt.setXXX( 순서번호, 매핑할 값 );
			psmt.setString( 1, id );
			psmt.setString( 2, pw );
			
			// 쿼리(SQL) 실행 -> 결과  - ResultSet (rs)
			rs = psmt.executeQuery();

            if (rs.next()) {
                IsExist = true;
            }
            else 
                IsExist = false;

        } catch (Exception e) {
            System.err.println("계정 조회 시, 예외 발생");
			e.printStackTrace();
        }
        return IsExist;
    }

    // 회원가입 시, 아이디 중복체크
	// 매개변수로 받은 아이디가 데이터베이스에 존재하는지 체크
	public boolean doubleCheck(String inputId) {
		boolean IsExist = false;

		String sql = " SELECT * "
				   + " FROM USERS "
				   + " WHERE id = ? ";
		
		try {
			psmt =  con.prepareStatement(sql);
			psmt.setString(1, inputId);

			rs = psmt.executeQuery();

			if (rs.next()) { //조회 결과가 있으면 true로 실행
				IsExist =  true;
			} else {
                IsExist = false;
            }

		} catch (Exception e) {
            System.err.println("아이디 중복체크 시, 예외 발생");
			e.printStackTrace();
		}
		return IsExist;
	}
}
