package com.cheifweb.checkbox;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CheckboxServlet
 */
@WebServlet("/CheckboxServlet")
public class CheckboxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckboxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		그냥 request.getParameter()는 String 타입으로 값 1개만을 받아옴
//		String place = request.getParameter("place");
//		System.out.println(place);
		
//		request.getParameterValues()는 값을 String[] 타입으로 여러개를 받아옴
		String [] places = request.getParameterValues("place");
		String str = "";
		for(String place : places)
			str += place + ", ";
//			System.out.println(place);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<h1>관광지 선택 결과</h1>");
		out.println("<h2>"+str+"</h2>");
		for(String place : places)
			out.println("<h2>"+place+"</h2>");
//		response.sendRedirect("/checkbox/checkResult.html");
	}

	

}
