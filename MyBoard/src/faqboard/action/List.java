package faqboard.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.FAQboard;
import board.bean.FAQboardDAO;
import board.model.BoardCommand;

public class List implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("faqboard.action/List.do");
		FAQboardDAO fdao = FAQboardDAO.getInstance();
		
		int count3 = fdao.getAllcount();
		System.out.println("전체 게시물 개수 : " + count3);
		int rowSize3 = 3;
		String pageNum3 = request.getParameter("pageNum3");
		if(pageNum3 == null) {
			pageNum3 = "1";
		}
		int currentPage3 = Integer.parseInt(pageNum3);
		System.out.println("현재 페이지 넘버 : " + currentPage3);
		int dbstart = (currentPage3 - 1) * rowSize3;
		int dbend = dbstart + rowSize3 - 1;
		int webNum3 = count3 - (currentPage3 - 1) * rowSize3;
		Vector<FAQboard> v3 = fdao.getAllBoard(dbstart, rowSize3);
		
		request.setAttribute("count3", count3);
		request.setAttribute("rowSize3", rowSize3);
		request.setAttribute("number3", webNum3);
		request.setAttribute("currentPage3", currentPage3);
		request.setAttribute("v3", v3);
		System.out.println("number3 : " + webNum3);
		
		
		// 유저가 사용자면 ==> return "user/FAQ/01_faqlist.jsp";
		// 유저가 관리자면 ==> return "mg/FAQ/01_faqlist_mg.jsp";
		return "mg/FAQ/01_faqlist_mg.jsp";
	}

}
