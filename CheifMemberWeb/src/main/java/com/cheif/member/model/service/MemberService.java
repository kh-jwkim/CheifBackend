package com.cheif.member.model.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.cheif.common.JDBCTemplate;
import com.cheif.member.model.dao.MemberDAO;
import com.cheif.member.model.vo.Member;

public class MemberService {
	
	MemberDAO mDao;

	public MemberService() {
		super();
		mDao = new MemberDAO();
	}

	public int insertMember(Member member) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = mDao.insertMember(conn, member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Member checkLogin(Member member) {
		Connection conn = null;
		Member mOne = null;;
		try {
			conn = JDBCTemplate.getConnection();
			mOne = mDao.checkLogin(conn, member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mOne;
	}

	public int deleteMember(String memberId) {
		Connection conn = null;
		int result = 0;
		try {
			conn=JDBCTemplate.getConnection();
			result = mDao.deleteMember(conn, memberId);
			//결과에 따른 커밋/롤백 필요
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	public Member selectOneById(String memberId) {
		Connection conn = null;
		Member member = null;
		try {
			conn = JDBCTemplate.getConnection();
			member = mDao.selectOneMemberById(conn, memberId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return member;
	}

	public int updateMember(Member member) {
		Connection conn = null;
		int result = 0;
		try {
			conn = JDBCTemplate.getConnection();
			result = mDao.updateMember(conn, member);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}

}
