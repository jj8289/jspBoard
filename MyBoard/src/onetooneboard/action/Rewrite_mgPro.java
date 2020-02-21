package onetooneboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Onetooneboard;
import board.bean.OnetooneboardDAO;
import board.model.BoardCommand;

public class Rewrite_mgPro implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int no = Integer.parseInt(request.getParameter("no"));
		String answer = request.getParameter("answer");
		
		OnetooneboardDAO odao = OnetooneboardDAO.getInstance();
		Onetooneboard bean = new Onetooneboard();
		bean.setNo(no);
		bean.setAnswer(answer);
		odao.MG_rewrite(bean);
		
		return "mg/oto/09_otorewritePro.jsp";
	}

}
