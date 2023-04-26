package com.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.smhrd.domain.BoardVO;

@Mapper
public interface BoardMapper {
	@Select("select * from board")
	public List<BoardVO> boardlist();
	@Update("update board set content=#{content} where idx=#{idx}")
	public void boardContentUpdate(BoardVO vo);
	@Update("update board set title=#{title}, writer=#{writer} where idx=#{idx}")
	public void boardTWUpdate(BoardVO vo);
	@Delete("delete from board where idx=#{idx}")
	public void boardDelete(int idx);
	@Insert("insert into board (title,content,writer) values (#{title},#{content},#{writer})")
	public void boardInsert(BoardVO vo);
}
