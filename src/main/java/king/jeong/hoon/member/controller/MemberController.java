package king.jeong.hoon.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import king.jeong.hoon.member.service.MemberService;

@Controller
public class MemberController {
	@Autowired MemberService memberService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		return "login";
	}
	
	@RequestMapping(value="/joinForm")
	public String joinForm() {
		logger.info("joinForm 요청");
		return "joinForm";
	}
	
	@RequestMapping(value="/overlay")
	@ResponseBody
	public Map<String, Object> overlay(String id){
		logger.info("overlay 요청, id : "+id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use", memberService.overlay(id));
		
		return map;
	}
	
	@RequestMapping(value="/join")
	public String join(@RequestParam Map<String, String> param, Model model){
		logger.info("join 요청");
		String page = "joinForm";
		String msg = "회원가입에 실패 하였습니다. 다시 시도 해 주세요.";
		if (memberService.join(param)>0) {
			page = "login";
			msg="회원가입에 성공하였습니다.";
		}
		model.addAttribute("msg",msg);
		
		return page;
	}
	
	@RequestMapping(value="/login")
	public String login(String id, String pw,Model model,HttpSession session) {
		logger.info("login 요청 = id,pw = {}/{}",id,pw);
		String page = "login";
		if (memberService.login(id,pw) != null) {
			page="redirect:/list";
			session.setAttribute("loginId", id);
		}else {
			model.addAttribute("msg","아이디 비밀번호를 확인해 주세요");
		}
		
		return page;
	}
	
}
