package noticeboard.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.bean.Noticeboard;
import board.bean.NoticeboardDAO;
import board.model.BoardCommand;

public class Info implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("noticeboard.actoin/Info");
		request.setCharacterEncoding("utf-8");
		
		int page2 = Integer.parseInt(request.getParameter("pageNum2"));
		HttpSession session = request.getSession();
		session.setAttribute("pageNum2", page2);
		
		String dbnum2 = request.getParameter("num2");
		int dbno2 = Integer.parseInt(dbnum2);
		
		NoticeboardDAO ndao = NoticeboardDAO.getInstance();
		Noticeboard bean2 = ndao.getOne(dbno2);
		System.out.println(bean2.getNo());
		System.out.println(bean2.getKind());
		System.out.println(bean2.getSubject());
		System.out.println(bean2.getContent());
		
		request.setAttribute("bean2", bean2);
		
		return "/mg/notice/04_noticeinfo_mg.jsp";
	}

}
