package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

public class Rewrite implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Rewrite.do");
		
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("no" + no);
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		Freeboard bean = new Freeboard();
		bean = fdao.getOne2(no); 	// 부모 글 
		
		request.setAttribute("bean2", bean);
		
		return "/user/free/09_boardrewrite.jsp";
	}

}
