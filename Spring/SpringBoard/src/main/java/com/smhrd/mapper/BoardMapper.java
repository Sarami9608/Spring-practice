package com.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.smhrd.domain.Board;

@Mapper
public interface BoardMapper {
	// 전체 게시물 불러오기
	public List<Board> boardList();
	// 하나의 게시물 불러오기
	public Board boardContent(int idx);
	// 게시물 추가하기
	public int writeBoard(Board b);
	// 게시물 수정하기
	public int updateBoard(Board b);
	// 게시물 삭제하기
	//Mybatis - interface + annotation 활용 -> sql이 간단한 경우 효과적
	@Delete("delete from board where idx=#{idx}")
	public int deleteBoard(int idx);
}
