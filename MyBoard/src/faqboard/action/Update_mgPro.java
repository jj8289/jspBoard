package faqboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.FAQboard;
import board.bean.FAQboardDAO;
import board.model.BoardCommand;

public class Update_mgPro implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FAQboardDAO fdao = FAQboardDAO.getInstance();
		
		int no = Integer.parseInt(request.getParameter("no"));
		String subject = request.getParameter("subject");
		String answer = request.getParameter("answer");
		
		FAQboard board = new FAQboard();
		board.setNo(no);
		board.setSubject(subject);
		board.setAnswer(answer);
		
		fdao.updateFAQ(board);
		
		return "mg/FAQ/06_faqupdatePro.jsp";
	}

}
