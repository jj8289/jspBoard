package onetooneboard.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.bean.Onetooneboard;
import board.bean.OnetooneboardDAO;
import board.model.BoardCommand;

public class Info implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("onetooneboard.action/Info.do");
		request.setCharacterEncoding("utf-8");
		
		int page5 = Integer.parseInt(request.getParameter("pageNum5"));
		HttpSession session = request.getSession();
		session.setAttribute("pageNum5", page5);
		
		String num5 = request.getParameter("num5");
		int no = Integer.parseInt(num5);
		
		OnetooneboardDAO odao = OnetooneboardDAO.getInstance();
		Onetooneboard bean = odao.getOne(no);
		
		request.setAttribute("bean5", bean);
		
		return "user/oto/04_otoinfo_user.jsp";
	}
}
