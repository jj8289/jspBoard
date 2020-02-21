package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

// BoardCommand를 상속받음 => @Override를 하기 위함(하나의 메소드로 각기 다른 클래스에서 값을 반환하기 위해)
// @Override => BoardCommand를 상속받은 모든 클래스에서는 requestPro() 메소드를 사용가능!
public class Write implements BoardCommand{
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		Freeboard bean = new Freeboard();
		bean.setWriter(request.getParameter("writer"));
		bean.setSubject(request.getParameter("subject"));
		bean.setPassword(request.getParameter("password"));
		bean.setContent(request.getParameter("content"));
		
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		fdao.addBoard(bean);
		
		return "/user/free/03_boardwritePro.jsp";
	}

}
