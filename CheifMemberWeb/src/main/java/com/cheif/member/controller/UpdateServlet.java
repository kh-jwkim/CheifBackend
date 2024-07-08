package com.cheif.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cheif.member.model.service.MemberService;
import com.cheif.member.model.vo.Member;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/member/update.do")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// UPDATE MEMBER_TBL SET EMAIL = ?, PHONE = ?, ADDRESS = ?, HOBBY = ? WHERE MEMBER_ID = ?
			//한글이 깨지지 않아야 함
			request.setCharacterEncoding("utf-8");
			Member member = new Member();
			member.setEmail(request.getParameter("email"));
			member.setPhone(request.getParameter("phone"));
			member.setAddress(request.getParameter("address"));
			member.setHobby(request.getParameter("hobby"));
			member.setMemberId(request.getParameter("memberId"));
			System.out.println("1차"+member.toString());
			MemberService mService = new MemberService();
			int result = mService.updateMember(member);
			if(result > 0) {
				//마이페이지로 가서 수정 확인
				response.sendRedirect("/member/mypage.do?memberId="+member.getMemberId());
			}else {
				request.setAttribute("msg", "정보수정이 완료되지 않았습니다.");
				request.getRequestDispatcher("/WEB-INF/views/common/error/errorPage.jsp").forward(request, response);
			}
		} catch(Exception e) {
			request.setAttribute("msg", e.getMessage());
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/common/error/errorPage.jsp");
			view.forward(request, response);
		}
		
	}

}
