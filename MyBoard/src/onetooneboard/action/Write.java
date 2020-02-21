package onetooneboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Onetooneboard;
import board.bean.OnetooneboardDAO;
import board.model.BoardCommand;

public class Write implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("onetooneboard.action/Write.do");
		OnetooneboardDAO odao = OnetooneboardDAO.getInstance();
		
		// 유저가 문의한 내용(파라미터들) 받기
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		Onetooneboard bean = new Onetooneboard();
		bean.setWriter(writer);
		bean.setSubject(subject);
		bean.setContent(content);
		System.out.println("writer : " + writer);
		System.out.println("subject : " + subject);
		
		// DB에 사용자 문의 내용 저장하기
		odao.addOTO(bean);
		
		return "/user/oto/03_otowritePro.jsp";
	}

}
