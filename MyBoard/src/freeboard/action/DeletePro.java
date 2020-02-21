package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

public class DeletePro implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeboard.action/DeletePro");
		
		// delete.jsp에서 보낸 파라미터들을 받음
		int no = Integer.parseInt(request.getParameter("no"));
		String pw = request.getParameter("password");
		
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		boolean istrue = fdao.checkPassword(no, pw);
		
		if(istrue) {
			fdao.deleteBoard(no);
		}
		System.out.println("istrue : " + istrue);
		
		request.setAttribute("istrue", istrue);
		
		return "/user/free/08_boarddeletePro.jsp";
	}

}
