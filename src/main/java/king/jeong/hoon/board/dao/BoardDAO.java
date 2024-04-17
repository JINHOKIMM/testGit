package king.jeong.hoon.board.dao;

import java.util.List;

import king.jeong.hoon.board.dto.BoardDTO;

public interface BoardDAO {

	List<BoardDTO> list(int pagePerCnt, int start);

	Object allCount(int pagePerCnt);

}
