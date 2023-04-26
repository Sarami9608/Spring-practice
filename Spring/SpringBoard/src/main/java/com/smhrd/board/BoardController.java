package com.smhrd.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.domain.Board;
import com.smhrd.mapper.BoardMapper;

@Controller
public class BoardController {
	@Autowired
	private BoardMapper mapper;
	@RequestMapping(value="/content",method=RequestMethod.GET)
	public String boardContent(@RequestParam("idx") int idx,Model model) {
		//@RequestParam : 넘어오는 값의 하나의 데이터를 가져오는 경우 사용 - 주로 queryString에 실린 데이터를 가져오는 경우 사용
		Board board = mapper.boardContent(idx);
		model.addAttribute("board",board);
		System.out.println(idx);
		return "boardContent";
	}
	
	@RequestMapping(value="/writeboard",method=RequestMethod.POST)
	public String writeBoard(Board b) {
		// @ModelAttribute : 정의한 Model(VO) 형태로 묶어서 가져오는 방법 -> 생략 가능
		// @ModelAttibute 는 기본생성자와 setter만 사용한다.		
		System.out.println(b.getTitle() + "," + b.getContent()+ "," + b.getWriter());
		if(b.getTitle() != "") {
			int cnt = mapper.writeBoard(b);
			if(cnt > 0) { 
	//			"boardlist" : 이동은 가능 / list를 model에 저장한 다음에 이동하는게 아니므로 NULL 값을 가진다. -> 에러 발생
	//			redirect:/ 를 통해 list를 재요청...? 흐음~ 코드는 간단해지는데 아깝다.
				return "redirect:/";
			}
			else {
				return "redirect:/wirteform";
			}
		} else {
			return "redirect:/";
			
		}
	}
	@RequestMapping(value="/updateBoard",method=RequestMethod.POST)
	public String updateBoard(Board b) {
		// @ModelAttribute : 정의한 Model(VO) 형태로 묶어서 가져오는 방법 -> 생략 가능
		// @ModelAttibute 는 기본생성자와 setter만 사용한다.		
		System.out.println(b.getTitle() + "," + b.getContent()+ "," + b.getWriter());
		if(b.getTitle() != "") {
			int cnt = mapper.updateBoard(b);
			System.out.println(cnt);
			if(cnt > 0) { 
				//			"boardlist" : 이동은 가능 / list를 model에 저장한 다음에 이동하는게 아니므로 NULL 값을 가진다. -> 에러 발생
				//			redirect:/ 를 통해 list를 재요청...? 흐음~ 코드는 간단해지는데 아깝다.
				return "redirect:/";
			}
			else {
				return "redirect:/updateForm?idx="+b.getIdx();
			}
		} else {
			return "redirect:/";
			
		}
	}
	// RESTful 방식으로 값을 가져오는 방식 - 경로에 포함되어 있으므로 다루는 방법이 필ㅇ
	// {} 안에 변수처럼 가져온 값을 어떻게 활용할 것인지 다룸
	@RequestMapping(value="/deleteBoard/{idx}",method=RequestMethod.GET)
	public String deleteBoard(@PathVariable("idx") int idx) {
		//@PathVarialble : 값을 하나만 받을 수 있음, RESTful 방식에서 ㅅ용
		//@RequestParam : 여러개의 값을 받을 수 있다. query String 방식에서 사용
		//?key=value&key=value
		System.out.println(idx);
		int cnt = mapper.deleteBoard(idx);
		System.out.println(idx);
		if(cnt > 0) {
			return "redirect:/";
		} else {
			return "redirect:/content";
		}
	}

}
