package faqboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.FAQboard;
import board.bean.FAQboardDAO;
import board.model.BoardCommand;

public class Delete_mg implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FAQboardDAO fdao = FAQboardDAO.getInstance();
		
		int no = Integer.parseInt(request.getParameter("no"));
		fdao.deleteFAQ(no);
		
		return "mg/FAQ/07_faqdelete.jsp";
	}

}
