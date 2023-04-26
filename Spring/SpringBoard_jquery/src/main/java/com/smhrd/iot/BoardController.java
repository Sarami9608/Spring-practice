package com.smhrd.iot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.smhrd.domain.BoardVO;
import com.smhrd.service.BoardService;

// 요청을 받고 응답(view -> controller 
@Controller
public class BoardController {
	@Autowired
	private BoardService service;
//	"/" 요청 -> basic.jsp 뷰 반환(서버) -> html 로드(클라이언트) -> 로드 완료시 "boardlist" 요청 (클라이언트
	@RequestMapping(value = "/")
	 public String basic() {
		return "basic";
	}
	@RequestMapping(value="/boardlist")
	public @ResponseBody List<BoardVO> boardlist() {
		//@ResponseBody : Controller에서 데이터를 반환할 때,
		//@RestController = @Controller + @ResponseBody(Spring 4 이상에서 사용 가능)
		List<BoardVO> list = service.boardlist();
		System.out.println("boardlist호출");
		System.out.println(list.size());
		
		return list;
	}
	
	@RequestMapping(value="/boardcontentupdate", method=RequestMethod.POST)
	public @ResponseBody void boardContentUpdate(BoardVO vo) { //@ModelAttribute 사용해서 idx newConetent각 위치에 저장ㄴ
		//@ResponseBody : Controller에서 데이터를 반환할 때,
		// void의 경우에도 작성 why 작성하지 않을 경우? controller view를 작성하고자 기능을 동작하고자 함 - 반환되는 것이 없기 때문에 404 에러가 발생
		service.boardContentUpdate(vo);
	}
	
	@RequestMapping(value="/boardtwupdate", method=RequestMethod.POST)
	public @ResponseBody void boardTWUpdate(BoardVO vo) {
		service.boardTWUpdate(vo);
	}
	@RequestMapping(value="/boarddelete", method=RequestMethod.POST)
	public @ResponseBody void boardDelete(int idx) {
		service.boardDelete(idx);
	}
	@RequestMapping(value="/boardinsert", method=RequestMethod.POST)
	public @ResponseBody void boardInsert(BoardVO vo) {
		service.boardInsert(vo);
		System.out.println(vo.getTitle());
		System.out.println(vo.getContent());
		System.out.println(vo.getWriter());
	}
}
