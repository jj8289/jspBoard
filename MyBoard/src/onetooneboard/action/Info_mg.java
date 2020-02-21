package onetooneboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.bean.Onetooneboard;
import board.bean.OnetooneboardDAO;
import board.model.BoardCommand;

public class Info_mg implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int page4 = Integer.parseInt(request.getParameter("pageNum4"));
		HttpSession session = request.getSession();
		session.setAttribute("pageNum4", page4);	
		
		String num4 = request.getParameter("num4");
		int no = Integer.parseInt(num4);			// DB에 해당글 no
		
		OnetooneboardDAO odao = OnetooneboardDAO.getInstance();
		Onetooneboard bean = new Onetooneboard();
		bean = odao.getOne(no);						// 해당 글 객체 가져옴
		
		request.setAttribute("bean4", bean);
		
		return "mg/oto/04_otoinfo_mg.jsp";
	}

}
