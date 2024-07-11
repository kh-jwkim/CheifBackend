package com.cheif.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cheif.member.model.service.MemberService;
import com.cheif.member.model.vo.Member;

/**
 * Servlet implementation class MypageServlet
 */
@WebServlet("/member/mypage.kh")
public class MypageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
//			String memberId = request.getParameter("memberId");
			//세션에서 아이디 가져오기 -> 로그인한 사용자에 한해서 마이페이지 정보를 가져올 수 있음.
			HttpSession session = request.getSession();
			String memberId = (String)session.getAttribute("memberId");
			//session의 memberId값이 null 또는 "" 가 아닐 때에만 마이페이지를 출력!!
			if(memberId!=null && !"".equals(memberId)) {
				MemberService mService = new MemberService();
				Member member = mService.selectOnebyId(memberId);
				if(member != null) {
					request.setAttribute("member", member);
					request.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp").forward(request, response);
				}else {
					request.setAttribute("msg", "존재하지 않는 정보입니다.");
					request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
				}
			}else {
				request.setAttribute("msg", "로그인이 필요합니다.");
				request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
			}
		} catch (Exception e) {
			request.setAttribute("msg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/views/common/errorPage.jsp").forward(request, response);
		}
	}

}
