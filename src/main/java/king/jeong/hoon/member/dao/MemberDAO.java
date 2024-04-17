package king.jeong.hoon.member.dao;

import java.util.Map;

public interface MemberDAO {

	Object overlay(String id);

	int join(Map<String, String> param);

	String login(String id, String pw);

}
