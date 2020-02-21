package noticeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Noticeboard;
import board.bean.NoticeboardDAO;
import board.model.BoardCommand;

public class Write_mg implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		Noticeboard board = new Noticeboard();
		board.setKind(Integer.parseInt(request.getParameter("kind")));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		
		NoticeboardDAO ndao = NoticeboardDAO.getInstance();
		ndao.addNotice(board);
		
		return "/mg/notice/03_noticewritePro.jsp";
	}

}
