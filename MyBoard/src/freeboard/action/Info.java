package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

public class Info implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		
		int page = Integer.parseInt(request.getParameter("pageNum"));
		HttpSession session = request.getSession();
		session.setAttribute("pageNum", page);
		
		String dbnum = request.getParameter("num");
		int dbno = Integer.parseInt(dbnum);
		Freeboard bean = fdao.getOne(dbno);
		request.setAttribute("bean", bean);
		
		return "/user/free/04_boardinfo.jsp";
	}
	
}
