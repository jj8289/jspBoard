package onetooneboard.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Onetooneboard;
import board.bean.OnetooneboardDAO;
import board.model.BoardCommand;

// 관리자에게 전체 문의글 출력
public class List_mg implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		OnetooneboardDAO odao = OnetooneboardDAO.getInstance();
		
		// 페이징
		String pageNum4 = request.getParameter("pageNum4");
		if(pageNum4 == null) {
			pageNum4 = "1";
		}
		int currentPage4 = Integer.parseInt(pageNum4);		//현재 페이지 넘버
		int count4 = odao.MG_getAllCount();					//답변안한 전체 게시물 개수
		int rowSize4 = 3;									//한페이지에 보여줄 게시물 개수
		int dbstart = (currentPage4 - 1) * rowSize4;	
		int dbend = dbstart + rowSize4 - 1;
		int webNum4 = count4 - (currentPage4 - 1) * rowSize4;
		Vector<Onetooneboard> vec = odao.MG_getAllList(dbstart, rowSize4);
		
		request.setAttribute("currentPage4", currentPage4);
		request.setAttribute("count4", count4);
		request.setAttribute("rowSize4", rowSize4);
		request.setAttribute("number4", webNum4);
		request.setAttribute("v4", vec);
		
		System.out.println("게시물 총 개수 : " + count4);
		
		return "mg/oto/01_otolist_mg.jsp";
	}
	
}
