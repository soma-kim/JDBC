package com.kh.model.vo;

import java.sql.Date;

public class Board {
	
	// 필드부
	private int bno;
	private String title;
	private String content;
	private Date createDate;
	private String writer;
	private String deleteYN;
	
	// 생성자부
	public Board() {
		super();
	}
	
	// 게시글 쓰기용 생성자
	public Board(String title, String content, String writer) {
		this.title = title;
		this.content = content;
		this.writer = writer;
	}
	
	public Board(int bno, String title, String content, Date createDate, String writer, String deleteYN) {
		super();
		this.bno = bno;
		this.title = title;
		this.content = content;
		this.createDate = createDate;
		this.writer = writer;
		this.deleteYN = deleteYN;
	}
	
	// 메소드부
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDeleteYN() {
		return deleteYN;
	}
	public void setDeleteYN(String deleteYN) {
		this.deleteYN = deleteYN;
	}
	
	@Override
	public String toString() {
		return "Board [bno=" + bno + ", title=" + title + ", content=" + content + ", createDate=" + createDate
				+ ", writer=" + writer + ", deleteYN=" + deleteYN + "]";
	}

}
