package com.smhrd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.domain.BoardVO;
import com.smhrd.mapper.BoardMapper;



// Controller : 요청값, 응답값 자체에만 집중을 한다.
// Service :  요청과 응답 사이의 처리에 집중을 한다. 
// Controller -> Servie -> Mapper ->Serive -> Conrtoller
@Service
public class BoardService{
	@Autowired
	private BoardMapper mapper;
	
	
	public List<BoardVO> boardlist(){
		return mapper.boardlist();
	}
	
	
	public void boardContentUpdate(BoardVO vo) {
		mapper.boardContentUpdate(vo);
	}
	public void boardTWUpdate(BoardVO vo) {
		mapper.boardTWUpdate(vo);
	}
	public void boardDelete(int idx) {
		mapper.boardDelete(idx);
	}
	public void boardInsert(BoardVO vo) {
		mapper.boardInsert(vo);
	}

}


