package kr.co.saramin.mysite.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.saramin.mvc.MVCUtil;
import kr.co.saramin.mysite.dao.GuestbookDao;
import kr.co.saramin.mysite.vo.GuestbookVo;

/**
 * Servlet implementation class GuestbookServlet
 */
@WebServlet("/guestbook")
public class GuestbookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8"); // POST 방식
		
		String actionName = request.getParameter("a");
		
			
		System.out.println(actionName);
		if ("deleteform".equals(actionName)) {
			// request.setAttribute("list", list);
			MVCUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteform.jsp");
			
		} else {
			// if ("list".equals(actionName)) {
			GuestbookDao dao = new GuestbookDao();
			List<GuestbookVo> list = dao.getList();
			
			request.setAttribute("list", list);
			MVCUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
		}
		
		// TODO Auto-generated method stub
		System.out.println("Served at: " + request.getContextPath() + "/" + actionName);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String actionName = request.getParameter("a");
		if (false == "delete".equals(actionName)) {
			return;
		}
		
		String no = request.getParameter("no");
		String password = request.getParameter("password");
		
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(Long.parseLong(no));
		vo.setPassword(password);
		
		new GuestbookDao().delete(vo);
	}

	

}
