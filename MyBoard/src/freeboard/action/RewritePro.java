package freeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

public class RewritePro implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8"); 
		
		int no = Integer.parseInt(request.getParameter("no"));	// 부모글 글번호
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		Freeboard parent = new Freeboard();
		parent = fdao.getOne2(no);								// 부모글
		
		Freeboard son = new Freeboard();						// 자식글 // 답변글 넘긴 내용(파라미터)
		son.setWriter(request.getParameter("writer"));
		son.setSubject(request.getParameter("subject"));
		son.setPassword(request.getParameter("password"));
		son.setContent(request.getParameter("content"));
		
		fdao.rewriteBoard(parent, son);
		
		// jsp 답변글에 대한 내용을 담은 bean을 DAO에서 답변 추가 메소드에서 추가 처리 해줌.
		
		
		return "/user/free/10_boardrewritePro.jsp";
	}

}
