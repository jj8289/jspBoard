package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

public class Update implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeboard.action/Update");
		
		// info.jsp 에서 hidden으로 넘긴 파라미터 받기
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no : " + no);
		
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		Freeboard board = new Freeboard();
		board = fdao.getOne(no);	
		
		// update.jsp로 request를 보냄
		request.setAttribute("bean", board);
		
		return "/user/free/05_boardupdate.jsp";
	}

}
