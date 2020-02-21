package onetooneboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Onetooneboard;
import board.bean.OnetooneboardDAO;
import board.model.BoardCommand;

public class Rewrite_mg implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int no = Integer.parseInt(request.getParameter("no"));
		
		OnetooneboardDAO odao = OnetooneboardDAO.getInstance();
		Onetooneboard bean = odao.getOne(no);
		
		request.setAttribute("bean4", bean);
		
		return "mg/oto/08_otorewrite.jsp";
		
	}

}
