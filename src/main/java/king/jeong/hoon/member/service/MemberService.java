package king.jeong.hoon.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import king.jeong.hoon.member.dao.MemberDAO;

@Service
public class MemberService {
	@Autowired MemberDAO memberDao;
	
	public Object overlay(String id) {
		
		return memberDao.overlay(id);
	}

	public int join(Map<String, String> param) {
		
		return memberDao.join(param);
	}

	public String login(String id, String pw) {
		
		return memberDao.login(id,pw);
	}

}
