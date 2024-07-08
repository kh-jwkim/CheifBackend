package com.cheifweb.calculator;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorSuvlet
 */
@WebServlet("/CalculatorSuvlet")
public class CalculatorSuvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculatorSuvlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int lnum = Integer.parseInt(request.getParameter("left"));
		int rnum = Integer.parseInt(request.getParameter("right"));
		String oper = request.getParameter("operator");
		int result = 0;
		
		switch (oper) {
		case "+": result = lnum+rnum; break;
		case "-": result = lnum-rnum; break;
		case "x": result = lnum*rnum; break;
		case "/": result = lnum/rnum; break;
		case "%": result = lnum%rnum; break;
		}
		request.setAttribute("lnum", lnum);
		request.setAttribute("rnum", rnum);
		request.setAttribute("oper", oper);
		request.setAttribute("result", result);
		request.getRequestDispatcher("/calculator/resultCalc.jsp").forward(request, response);
	}


}
