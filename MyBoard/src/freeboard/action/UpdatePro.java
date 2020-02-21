package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

public class UpdatePro implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeboard.action/UpdatePro");
		request.setCharacterEncoding("UTF-8");
		
		// 05_boardupdate.jsp에서 보낸 글 번호로 게시글 객체 가져옴
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		Freeboard board = new Freeboard();
		int no = Integer.parseInt(request.getParameter("no"));
		board = fdao.getOne2(no);
		
		String pw = request.getParameter("password");
		boolean istrue = fdao.checkPassword(no, pw);
		request.setAttribute("istrue", istrue);
		if(istrue) {
			// 05_boardupdate.jsp에서 제목, 내용을 update시킴
			board.setContent(request.getParameter("content"));
			board.setSubject(request.getParameter("subject"));
			fdao.updateBoard(board);
		}
		
		return "/user/free/06_boardupdatePro.jsp";
	}

}
