package king.jeong.hoon.board.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import king.jeong.hoon.board.service.BoardService;


@Controller
public class BoardController {
	@Autowired BoardService boardService;
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@RequestMapping(value="/list")
	public String list() {
		logger.info("list 요청");
		return "list";
	}
	
	@RequestMapping(value="/list.ajax")
	@ResponseBody
	public Map<String, Object> listCall(String page, String cnt){
		logger.info("list.ajax 요청");
		logger.info("페이지당 보여줄 갯수"+cnt);
		logger.info("요청 페이지"+page);
		
		int currPage = Integer.parseInt(page);
		int pagePerCnt = Integer.parseInt(cnt);
		
		Map<String, Object> map = boardService.list(currPage,pagePerCnt);

		return map;
	}
}
