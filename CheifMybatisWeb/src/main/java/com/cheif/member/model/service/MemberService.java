package com.cheif.member.model.service;

import org.apache.ibatis.session.SqlSession;

import com.cheif.common.SqlSessionTemplate;
import com.cheif.member.model.dao.MemberDAO;
import com.cheif.member.model.vo.Member;

public class MemberService {
	
	private SqlSession conn;
	private MemberDAO mDao;
	
	public MemberService() {
		conn = SqlSessionTemplate.getSqlSession();
		mDao = new MemberDAO();
	}



	public int insertMember(Member member) {
		int result = mDao.insertMember(conn, member);
		return result;
	}



	public Member checkLogin(Member member) {
		Member mOne = mDao.checkLogin(conn, member);
		return mOne;
	}



	public Member selectOnebyId(String memberId) {
		Member member = mDao.selectOneById(conn, memberId);
		return member;
	}

}
