package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.FreeboardDAO;
import board.model.BoardCommand;

// 관리자가 삭제
public class Delete_mg implements BoardCommand{
	// 관리자 아이디일 때
		//HttpSession session = request.getSession();
		//session.setAttribute("세션명", 변수);
		// 세션을 만들어서 distpatcher로 보내면 ==> jsp 파일에서 ${sessionScope.세션명}으로 받음
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeboard.action/Delete_mg.do");
		int no = Integer.parseInt(request.getParameter("no"));
		
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		fdao.deleteBoard(no);
		
		return "/mg/free/07_boarddelete_mg.jsp";
	}
	
}
