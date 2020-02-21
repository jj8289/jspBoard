package faqboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.FAQboard;
import board.bean.FAQboardDAO;
import board.model.BoardCommand;

public class Update_mg implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FAQboardDAO fdao = FAQboardDAO.getInstance();
		
		int no = Integer.parseInt(request.getParameter("no"));
		FAQboard bean = fdao.getOne2(no);
		
		request.setAttribute("bean", bean);
		
		return "mg/FAQ/05_faqupdate.jsp";
	}

}
