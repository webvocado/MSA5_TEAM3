 package com.joeun.DTO;

import java.util.Date;

/* (DTO)
  * 유저 정보
  *  - ID
  *  - PW
  *  - 이름
  *  - 계정생성일자
  */

public class User {
    
    private String id;
    private String pw;
    private String name;
    private Date regDate;

    public User() {
        this("이름없음", "id없음", "비번없음" );
    }

    public User(String name, String id, String pw) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.regDate = new Date();
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public String getPw() {
        return pw;
    }


    public void setPw(String pw) {
        this.pw = pw;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Date getRegDate() {
        return regDate;
    }


    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", pw=" + pw + ", name=" + name + ", regDate=" + regDate + "]";
    }
    
}
