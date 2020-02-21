package noticeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Noticeboard;
import board.bean.NoticeboardDAO;
import board.model.BoardCommand;

public class Delete_mg implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("noticeboard.action/Delete_mg.do");
		int no = Integer.parseInt(request.getParameter("no"));
		
		NoticeboardDAO ndao = NoticeboardDAO.getInstance();
		ndao.deleteNotice(no);
		
		return "mg/notice/07_noticedelete.jsp";
	}

}
