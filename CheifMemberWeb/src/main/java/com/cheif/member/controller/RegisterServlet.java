package com.cheif.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cheif.member.model.service.MemberService;
import com.cheif.member.model.vo.Member;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/member/join.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	MemberService mService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        mService = new MemberService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.sendRedirect("/WEB-INF/views/member/register.jsp");	//이건 단순히 파라미터값을 주소입력창에 넣어서 엔터누르는것과 다를 바 없다
		request.getRequestDispatcher("/WEB-INF/views/member/register.jsp").forward(request, response);	//이래야만 WEB-INF에 숨겨놓은 페이지가 출력됨
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId 	= request.getParameter("member-id");
		String memberPw 	= request.getParameter("member-pw");
		String memberName 	= request.getParameter("member-name");
		String gender 		= request.getParameter("gender");
		int age 			= Integer.parseInt(request.getParameter("age"));
		String email		= request.getParameter("email");
		String phone 		= request.getParameter("phone");
		String address 		= request.getParameter("address");
		String hobby 		= request.getParameter("hobby");
		
		//회원가입 비즈니스 로직
		
		Member member = new Member(memberId, memberPw, memberName, gender, age, email, phone, address, hobby, null);
		
		int result = mService.insertMember(member);
		
		if(result > 0) {
			// 성공 메시지 출력
			// 1. redirect
			// response.sendRedirect("");
			// 2. requestDispatcher
			// requset.setAttribute("msg", "성공"); //이건 보낼 데이터의 예시
			// request.getRequestDispatcher("").forward(request, response);
			response.sendRedirect("/index.jsp");
		}else {
			// 실패 메시지 출력
//			response.sendRedirect("/common/error/errorPage.jsp"); //error페이지를 외부에 노출하기 싫어서 WEB-INF로 옮김
			request.getRequestDispatcher("/WEB-INF/views/common/error/errorPage.jsp").forward(request, response);
		}
	}
	
	

}
