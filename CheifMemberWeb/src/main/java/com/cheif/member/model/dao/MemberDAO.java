package com.cheif.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.cheif.member.model.vo.Member;

public class MemberDAO {
	
	private final String FILE_NAME = "query.properties";
	private Properties prop;
	private String selectOne_query;
	private String selectList_query;
	private String checkLogin_query;
	private String insertMember_query;
	private String updateMember_query;
	private String deleteMember_query;
	
	public MemberDAO() {
		try {
			prop = new Properties();
//			Reader reader = new FileReader(FILE_NAME);
			Reader reader = new FileReader(this.getClass().getResource(FILE_NAME).getPath());
			prop.load(reader);
			selectOne_query		= prop.getProperty("selectOne");
			selectList_query	= prop.getProperty("selectList");
			checkLogin_query	= prop.getProperty("checkLogin");
			insertMember_query	= prop.getProperty("insertMember");
			updateMember_query	= prop.getProperty("updateMember");
			deleteMember_query	= prop.getProperty("deleteMember");
			
			
//			insertMember_query = "INSERT INTO MEMBER_TBL(MEMBER_ID, MEMBER_PW, MEMBER_NAME, GENDER, AGE, EMAIL, PHONE, ADDRESS, HOBBY, REG_DATE) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int insertMember(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		
		pstmt = conn.prepareStatement(insertMember_query);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPw());
		pstmt.setString(3, member.getMemberName());
		pstmt.setString(4, member.getGender());
		pstmt.setInt(5, member.getAge());
		pstmt.setString(6, member.getEmail());
		pstmt.setString(7, member.getPhone());
		pstmt.setString(8, member.getAddress());
		pstmt.setString(9, member.getHobby());
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	public Member checkLogin(Connection conn, Member member) throws SQLException {
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member mOne = null;
//		String query = "SELECT * FROM MEMBER_TBL WHERE MEMBER_ID = ? AND MEMBER_PW = ?";
		
		pstmt = conn.prepareStatement(checkLogin_query);
		pstmt.setString(1, member.getMemberId());
		pstmt.setString(2, member.getMemberPw());
		rset = pstmt.executeQuery();
		
		if(rset.next()) {
			mOne = new Member();
			mOne.setMemberId(rset.getString("MEMBER_ID"));
			mOne.setMemberName(rset.getString("MEMBER_NAME"));
		}
		pstmt.close();
		return mOne;
	}

	public int deleteMember(Connection conn, String memberId) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		pstmt = conn.prepareStatement(deleteMember_query);
		pstmt.setString(1, memberId);
		result = pstmt.executeUpdate();
		pstmt.close();
		return result;
	}

	public Member selectOneMemberById(Connection conn, String memberId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member member = null;
		pstmt = conn.prepareStatement(selectOne_query);
		pstmt.setString(1, memberId);
		rset = pstmt.executeQuery();
		if(rset.next())
			member = this.rsetToMember(rset);
		return member;
	}
	
	public int updateMember(Connection conn, Member member) throws SQLException {
		PreparedStatement pstmt = null;
		int result = 0;
		System.out.println(member.toString());
		pstmt = conn.prepareStatement(updateMember_query);
		pstmt.setString(1, member.getEmail());
		pstmt.setString(2, member.getPhone());
		pstmt.setString(3, member.getAddress());
		pstmt.setString(4, member.getHobby());
		pstmt.setString(5, member.getMemberId());
		result = pstmt.executeUpdate();
		return result;
	}

	private Member rsetToMember(ResultSet rset) throws SQLException {
		Member member = new Member();
		member.setMemberId(rset.getString("MEMBER_ID"));
		member.setMemberPw(rset.getString("MEMBER_PW"));
		member.setMemberName(rset.getString("MEMBER_NAME"));
		member.setGender(rset.getString("GENDER"));
		member.setAge(rset.getInt("AGE"));
		member.setEmail(rset.getString("EMAIL"));
		member.setPhone(rset.getString("PHONE"));
		member.setAddress(rset.getString("ADDRESS"));
		member.setHobby(rset.getString("HOBBY"));
		member.setRegDate(rset.getDate("REG_DATE"));
		return member;
	}

}
