package king.jeong.hoon.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import king.jeong.hoon.board.dao.BoardDAO;
import king.jeong.hoon.board.dto.BoardDTO;

@Service
public class BoardService {
	@Autowired BoardDAO boardDao;
	Logger logger = LoggerFactory.getLogger(getClass());

	public Map<String, Object> list(int currPage, int pagePerCnt) {
		int start = (currPage-1) * pagePerCnt;
		
		
		 
		Map<String, Object> result = new HashMap<String, Object>();
		List<BoardDTO> list = boardDao.list(pagePerCnt,start);
		logger.info("list size:" + list.size());
		result.put("list", list);
		result.put("currPage", currPage);
		result.put("totalPages", boardDao.allCount(pagePerCnt));
		
		return result;
	}

}
