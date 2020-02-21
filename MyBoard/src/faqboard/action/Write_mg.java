package faqboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.FAQboard;
import board.bean.FAQboardDAO;
import board.model.BoardCommand;

public class Write_mg implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FAQboardDAO fdao = FAQboardDAO.getInstance();
		
		String subject = request.getParameter("subject");
		String answer = request.getParameter("answer");
		
		FAQboard bean = new FAQboard();
		bean.setSubject(subject);
		bean.setAnswer(answer);
		
		fdao.addFAQ(bean);
		
		return "mg/FAQ/03_faqwritePro.jsp";
	}

}
