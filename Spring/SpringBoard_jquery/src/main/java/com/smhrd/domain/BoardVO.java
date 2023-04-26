package com.smhrd.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardVO {
	private int idx; // 게시물 번호
	private String title; // 게시물 제목
	private String content; // 게시물 내용
	private String writer; // 게시물 작성자
	private String indate; // 작성일
}
