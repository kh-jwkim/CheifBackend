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
 * Servlet implementation class RsgisterServlet
 */
@WebServlet("/member/join.kh")
public class RsgisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService mService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RsgisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        mService = new MemberService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//회원가입 버튼(링크)을 누르면 동작
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/member/register.jsp");
			view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("UTF-8");
			String memberId 	= request.getParameter("member-id");
			String memberPw 	= request.getParameter("member-pw");
			String memberName 	= request.getParameter("member-name");
			String gender 		= request.getParameter("gender");
	//		int age 			= Integer.parseInt(request.getParameter("age"));
			String age 			= request.getParameter("age");
			String email		= request.getParameter("email");
			String phone 		= request.getParameter("phone");
			String address 		= request.getParameter("address");
			String hobby 		= request.getParameter("hobby");
			Member member = new Member(memberId, memberPw, memberName, gender, Integer.parseInt(age), email, phone, address, hobby);
			int result = mService.insertMember(member);
			if(result > 0) {
				response.sendRedirect("/");
			}else {
				request.setAttribute("msg", "회원가입이 완료되지 않았습니다.");
				request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
