package com.lgy.member_mybatis_oracle.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lgy.board_mybatis_mysql.dao.BoardDAO;
import com.lgy.member_mybatis_oracle.dao.MemDAO;
import com.lgy.member_mybatis_oracle.dto.MemDTO;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemController {

	@Autowired
	private SqlSession sqlSession;
	
	
	@RequestMapping("/login")
	public String login() {
		log.info("@# login");
		
		return "login";
	}
	
	@RequestMapping("/login_yn")
	public String login_yn(HttpServletRequest request, Model model) {
		log.info("@# login_yn");
		
		model.addAttribute("request", request);
		String mPw = request.getParameter("mem_pwd");

		//		service = new MemLoginService();
//		int result = service.execute(model);
		
		MemDAO dao = sqlSession.getMapper(MemDAO.class);
		ArrayList<MemDTO> dtos = dao.loginYn(request.getParameter("mem_uid")
			      							, request.getParameter("mem_pwd"));

		
//		아이디와 비밀번호가 일치
		if (dtos.isEmpty()) {
			return "redirect:login";
		} else {
			if (mPw.equals(dtos.get(0).getMem_pwd())) {
				return "redirect:login_ok";
			} else {
				return "redirect:login";
			}
		}
	}
	@RequestMapping("/login_ok")
	public String login_ok() {
		log.info("@# login_ok");
		
		return "login_ok";
	}
	
	@RequestMapping("/register")
	public String register() {
		log.info("@# register");
		
		return "register";
	}
	
	@RequestMapping("/registerOk")
	public String registerOk(HttpServletRequest request, Model model) {
		log.info("@# registerOk");
		
		model.addAttribute("request", request);
		
//		service=new MWriteService();
//		service.execute(model);
		MemDAO dao = sqlSession.getMapper(MemDAO.class);
//		서비스단에서 처리했던 것을 컨트롤러 단에서 처리
		dao.write(request.getParameter("mem_uid")
				, request.getParameter("mem_pwd")
				, request.getParameter("mem_name"));
		return "redirect:login";
	}
	
}
