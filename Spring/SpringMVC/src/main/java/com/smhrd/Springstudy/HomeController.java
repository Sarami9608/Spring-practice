package com.smhrd.Springstudy;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
// @Controller(annotation) : Spring에서 해당 class가 contorller의 역할을 수행한다고 명시하기위해 사용하는 annotation
// 대부분 view 반환, 가끔(model(data)만 반환하는 경우도 존재
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping : 요청 들어온 경로(uri)와 value 값하고 일치하면 해당 메서드가 호출되도록 하는 annotation
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		// 반환된 view 문자열을 viewresolver를 통해 실제 view가 반환될 수 있또록 만들어야함!
		// servlet-context.xml 에 정의되어이 있음
		// prefix : /WEB-INF/views
		// suffix : .jsp
		// localhost : 8080/Springstudy(context-root)/WEB-INF/views/반환 문자열
		return "home";
	}
	// annotation RequestMapping을 사용한다는 명시가 필요하다.
	// value : 요청 경로, method : 요청 방식 ( get / post)
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {
		return "main";
	}
	@RequestMapping(value = "/join",method = RequestMethod.GET)
	public String join() {
		return "join";
	}
	@RequestMapping(value = "/login",method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "main";
	}
	
}
