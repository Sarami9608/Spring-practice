package com.smhrd.Springstudy;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.smhrd.domain.Member;
import com.smhrd.mapper.Mapper;
@Controller
public class MemberController {
	//@Autowired : 의존성을 주입해주는 
	// 필요한 객체의 타입에 해당하는 빈(객체)를 찾아 주입
	@Autowired
	private Mapper mapper;
	
	@RequestMapping(value= "/member/join",method = RequestMethod.POST)
	public String memberJoin(@RequestParam("id") String id,@RequestParam("pw") String pw,@RequestParam("nick") String nick) {
//		@RequestParam(지정한 name) : 요청 파라미터 가져오는 기능
		System.out.println(id + " , " + pw + " , " + nick);
		Member m = new Member(id,pw,nick);
		
		//HikariCP+MyBatis
		int cnt = mapper.memberJoin(m);
		System.out.println(cnt);
		
		if(cnt > 0) { 
			// 회원가입 성공
			// redirect 없을 시 그전 동작 페이지 유지 why 사용자가 요청한 것이 아니기 때문 
			// redirect 사용자측 주소에도 main으로 나오도록
			return "redirect:/main"; // main으로 재요청
		} else {
			// 회원가입 실패
			return "redirect:/join"; // join으로 재요청
		}
	}
	
	@RequestMapping(value="/member/login", method=RequestMethod.POST)
//	annotation 을 사용 but 한번에?? ModelAttribute
	public String memberLogin(@ModelAttribute Member input , HttpSession session) {
		// 요청 파라미터 가져오는 방법
		// @RequestParam : 파라미터를 각각 지정해서 가져오는 방법
		// @ModelAttribute : 정의한 Model(VO) 형태로 묶어서 가져오는 방법 -> 생략 가능
		// @ModelAttibute 는 기본생성자와 setter만 사용한다.		
		System.out.println(input.getId()+ "," + input.getPw()+"," + input.getNick());
		Member loginMember = mapper.memberLogin(input);
		if(loginMember != null) {			
			session.setAttribute("loginMember", loginMember);
			// 로그인 성공
			return "redirect:/main";
		} else {
			// 로그인 싪패
			return "redirect:/login";
		}
	}
}
