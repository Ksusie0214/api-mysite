package com.javaex.vo;

public class BoardVo {
	//필드
	private int no;
	private String title;
	private String name;
	private String date;
	private String content;
	
	//생성자
	public BoardVo() {
		
	}
	
	//메소드 g/s
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	//메소드 일반
	
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", name=" + name + ", date=" + date + ", content=" + content
				+ "]";
	}
	
}
