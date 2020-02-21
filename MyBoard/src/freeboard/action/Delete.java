package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

// 사용자가 삭제
public class Delete implements BoardCommand {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeboard.action/Delete");
		
		//info.jsp에서 hidden으로 넘긴 파라미터 받기
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("11");
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		System.out.println("22");
		Freeboard board = new Freeboard();
		board = fdao.getOne2(no);
		System.out.println("33");
		request.setAttribute("board", board);
		System.out.println("44");
		System.out.println(board.getNo());
		System.out.println(board.getWriter());
		
		return "/user/free/07_boarddelete.jsp";
	}

}
