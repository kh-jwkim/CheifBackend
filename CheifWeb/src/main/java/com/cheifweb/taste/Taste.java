package com.cheifweb.taste;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Taste
 */
@WebServlet("/Taste")
public class Taste extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Taste() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String color = request.getParameter("color");
		String animal = request.getParameter("animal");
		String[] foodArr = request.getParameterValues("menu");
		String foodstr = "";
		for(int i=0; i<foodArr.length; i++) {
			if(i!=foodArr.length-1)
				foodstr += foodGet(foodArr[i])+", ";
			else
				foodstr += foodGet(foodArr[i]);
		}
//		for(String food:foodArr)
//			foods += foodGet(food);
		
//		response.sendRedirect("/taste/result.html");
		request.setAttribute("userName", name);
		request.setAttribute("color", colorGet(color));
		request.setAttribute("pet", petGet(animal));
		request.setAttribute("foods", foodstr);
		request.getRequestDispatcher("/taste/tasteResult.jsp").forward(request, response);
		
//		response.setContentType("text/html;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		out.println("<html><head><title>개인 취향 테스트</title>");
//		out.println("</head><body>");
//		out.println("<h1>개인 취향 테스트 결과</h1>");
//		out.println("<p>이름? "+name+"<br>");
//		out.println("좋아하는 색? <span style='color:"+color+";'><b>"+colorGet(color)+"</b></span><br>");
//		out.println("좋아하는 동물은? "+petGet(animal)+"<br>");
//		out.println("좋아하는 음식은? ");
//		for(int i=0; i<foods.length; i++) {
//			if(i!=foods.length-1)
//				out.println(foodGet(foods[i])+", ");
//			else
//				out.println(foodGet(foods[i])+"<br>");
//		}
//		out.println("</p>");
//		out.println("</body></html>");
//		
//		System.out.println(name);
//		System.out.println(color);
//		System.out.println(animal);
//		for(String menu:foods)
//			System.out.println(menu);
	}

	private String petGet(String pet) {
		Map<String, String> petMap = new HashMap<String, String>();
		petMap.put("cat", "고양이");
		petMap.put("dog", "개");
		petMap.put("duck", "오리");
		petMap.put("rabbit", "토끼");
		return petMap.get(pet);
	}
	
	private String colorGet(String color) {
		Map<String, String> colorMap = new HashMap<String, String>();
		colorMap.put("red", "빨강");
		colorMap.put("blue", "파랑");
		colorMap.put("yellow", "노랑");
		colorMap.put("green", "초록");
		return colorMap.get(color);
	}
	
	private String foodGet(String food) {
		Map<String, String> foodMap = new HashMap<String, String>();
		foodMap.put("jjm", "짜장면");
		foodMap.put("jb", "짬뽕");
		foodMap.put("tsy", "탕수육");
		foodMap.put("mpdb", "마파두부");
		foodMap.put("pbc", "팔보채");
		return foodMap.get(food);
	}

}
