package noticeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Noticeboard;
import board.bean.NoticeboardDAO;
import board.model.BoardCommand;

public class Update_mgPro implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("noticeboard.action/Update_mgPro.do");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		NoticeboardDAO ndao = NoticeboardDAO.getInstance();
		Noticeboard board = ndao.getOne2(no);
		board.setSubject(subject);
		board.setContent(content);
		ndao.updateNotice(board);
		
		return "mg/notice/06_noticeupdatePro.jsp";
	}

}
