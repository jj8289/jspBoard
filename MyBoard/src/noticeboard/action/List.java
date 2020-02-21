package noticeboard.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.bean.Noticeboard;
import board.bean.NoticeboardDAO;
import board.model.BoardCommand;

public class List implements BoardCommand{

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("noticeboard.bean/List.do");
		NoticeboardDAO ndao = NoticeboardDAO.getInstance();
		Noticeboard board = new Noticeboard();
		// 순서
		//1. 전체 게시글 개수 가져오기
		//2. 로우 사이즈 정하기 => 한 페이지 당 보여줄 게시글 개수
		//3. 웹에서 현재 페이지 번호
		//4. DB에서 찾을 시작 행
		//5. DB에서 찾을 마지막 행
		//6. 웹에서 num을 셀 변수
		//7. DB에서 해당 게시글 담는 벡터 정의
		//8. 1,2,3,6,7 jsp파일로 보내기
		
		int allcount = ndao.getAllcount();
		
		int rowSize2 = 3;
		
		String pageNum2 = request.getParameter("pageNum2");
		if(pageNum2 == null) {
			pageNum2 = "1";
		}
		int currentPage2 = Integer.parseInt(pageNum2);
		System.out.println("currentPage2 : " + currentPage2);
		
		int dbstart = (currentPage2 - 1) * (rowSize2);
		
		int dbend = dbstart + rowSize2 - 1;
		
		int webNum = allcount - ((currentPage2 - 1) * rowSize2);
		System.out.println("webNum : " + webNum);
		
		Vector<Noticeboard> vec = new Vector<>();
		vec = ndao.getAllBoard(dbstart, rowSize2);
		
		request.setAttribute("v2", vec);					//보여줄 게시글 담은 벡터
		request.setAttribute("number2", webNum);			//웹에서 보여줄 최대 번호
		request.setAttribute("rowSize2", rowSize2);			//보여줄 row 개수
		request.setAttribute("count2", allcount);			//전체 게시글 개수
		request.setAttribute("currentPage2", currentPage2);	// 현재 페이지 넘버
		
		return "/mg/notice/01_noticelist_mg.jsp";
		
	}
}
