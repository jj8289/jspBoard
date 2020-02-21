package onetooneboard.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Onetooneboard;
import board.bean.OnetooneboardDAO;
import board.model.BoardCommand;

public class List implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OnetooneboardDAO odao = OnetooneboardDAO.getInstance();
			
		String id = "aa";
		
		// 페이징
		String pageNum5 = request.getParameter("pageNum5");
		if(pageNum5 == null) {
			pageNum5 = "1";
		}
		int currentPage5 = Integer.parseInt(pageNum5);		//현재 페이지 넘버
		int count5 = odao.User_getAllCount(id);					//답변안한 전체 게시물 개수
		int rowSize5 = 3;									//한페이지에 보여줄 게시물 개수
		int dbstart = (currentPage5 - 1) * rowSize5;	
		int dbend = dbstart + rowSize5 - 1;
		int webNum5 = count5 - (currentPage5 - 1) * rowSize5;
		Vector<Onetooneboard> vec = odao.User_getAllList(id, dbstart, rowSize5);
		
		request.setAttribute("currentPage5", currentPage5);
		request.setAttribute("count5", count5);
		request.setAttribute("rowSize5", rowSize5);
		request.setAttribute("number5", webNum5);
		request.setAttribute("v5", vec);
		
		System.out.println("게시물 총 개수 : " + count5);
		System.out.println("현재 페이지 : " + currentPage5);
		System.out.println(vec.get(0).getNo());
		System.out.println(vec.get(0).getSubject());
		
		return "user/oto/01_otolist.jsp";
	}

}
