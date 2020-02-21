package faqboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.bean.FAQboard;
import board.bean.FAQboardDAO;
import board.model.BoardCommand;

public class Info implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FAQboardDAO fdao = FAQboardDAO.getInstance();
		
		int page3 = Integer.parseInt(request.getParameter("pageNum3"));
		HttpSession session = request.getSession();
		session.setAttribute("pageNum3", page3);
		
		String dbnum3 = request.getParameter("num3");
		int dbno3 = Integer.parseInt(dbnum3);
		
		FAQboard bean3 = fdao.getOne(dbno3);
		
		request.setAttribute("bean3", bean3);
		
		// 유저가 사용자면 ==> return "user/FAQ/04_faqinfo.jsp";
		// 유저가 관리자면 ==> return "mg/FAQ/04_faqinfo_mg.jsp";
		
		return "user/FAQ/04_faqinfo.jsp";
	}

}
