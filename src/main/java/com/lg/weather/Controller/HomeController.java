package com.lg.weather.Controller;

import com.lg.weather.Dto.AdminDto;
import com.lg.weather.Dto.BoardDto;
import com.lg.weather.Dto.WeatherDto;
import com.lg.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.System.out;

@Controller
public class HomeController {

	@Autowired
	WeatherService weatherService;

	// 메인페이지 로딩
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showList(Model model,  WeatherDto weatherDto) throws Exception {
		if (weatherService.checkinfo() == 0) {
			weatherService.insertinfo(weatherDto);
		}
		weatherService.firstContent(model);
		weatherService.getNotice(model);
		return "home";
	}

	@RequestMapping(value = "/createBoard", method = RequestMethod.POST)
	public String createBoard (HttpServletRequest request, BoardDto boardDto) throws Exception {
		boardDto.setTitle(request.getParameter("title"));
		boardDto.setContent(request.getParameter("text"));
		boardDto.setAdminname(request.getParameter("id"));
		System.out.println(boardDto.getTitle() + " : " + boardDto.getContent() + " : "+boardDto.getAdminname());
		weatherService.insertNotice(boardDto);
		return "home";
	}


	// 로그인 처리
	@RequestMapping(value = "/loginProcessing", method = RequestMethod.POST)
	public String login(HttpServletRequest request, HttpSession session, AdminDto adminDto) throws Exception {
		adminDto.setAdmin_id(request.getParameter("username"));
		adminDto.setAdmin_pass(request.getParameter("password"));

		if(weatherService.getLogin(session, adminDto) == 1) {
			session.setAttribute("id",request.getParameter("username"));
			return "home";
		}else {
			return "fail";
		}
	}
	// 로그아웃 처리
	@RequestMapping(value = "/Logout", method = RequestMethod.GET)
	public void logout(HttpServletResponse response,HttpSession session, Model model) throws Exception {
		session.invalidate();
		weatherService.firstContent(model);
		response.sendRedirect("/");
	}
}