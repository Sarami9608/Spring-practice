package com.smhrd.board;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.domain.Board;
import com.smhrd.mapper.BoardMapper;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
//	객체 생성을 연결하기 위한 annotation
	@Autowired
	private BoardMapper mapper;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String boardlist(Model model) {
		// Model : controller 에서 생성도니 데이터를 view에 전달하는 역할 ( request와 같은 역할)
//		HashMap (key-value) 형태로 이루어진 형태ㅓ
		List<Board> list = mapper.boardList();
		model.addAttribute("list",list); // 모델에 값을 저장 (키값(이름), 실제 저장할 값)
		
		return "boardlist";
	}
	@RequestMapping(value="/writeform",method=RequestMethod.GET)
	public String writeform() {
		//@RequestParam : 넘어오는 값의 하나의 데이터를 가져오는 경우 사용 - 주로 queryString에 실린 데이터를 가져오는 경우 사용
		return "boardForm";
	}
	@RequestMapping(value="/updateForm")
	public String updateForm(@RequestParam("idx") int idx, Model model) {
		
		System.out.println(idx);// 사용자가 수정하고 싶은 글의 인덱스
		// TODO : 1. 가지고 온 인덱스에 해당하는 글 정보를 가져오기
		Board board = mapper.boardContent(idx);
		// TODO : 2. 가지고 온 정보를 boardupdate에 출력할 수 있도록 저장
		model.addAttribute("board",board);
		// TODO : 3. boardUpdate로 이동
		return "boardUpdate";
	}
}
