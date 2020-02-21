package freeboard.action;

import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import board.bean.Freeboard;
import board.bean.FreeboardDAO;
import board.model.BoardCommand;

//BoardCommand를 상속받음 => @Override를 하기 위함(하나의 메소드로 각기 다른 클래스에서 값을 반환하기 위해)
//@Override => BoardCommand를 상속받은 모든 클래스에서는 requestPro() 메소드를 사용가능!
//BoardList == BoardCommand라고 봐도 무방
public class List implements BoardCommand{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("freeboard.action/List");
		FreeboardDAO fdao = FreeboardDAO.getInstance();
		// 전체 게시글 개수 가져오기
		int allcount = fdao.getallcount();
		
		//어떻게 나눌까? => 한 화면 당 3개 게시글 보여주기
		int rowSize = 3;									// 로우 사이즈 => 한 페이지 당 보여줄 게시글 개수
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";	
		}
		int currentPage = Integer.parseInt(pageNum);		// 웹에서 현재 페이지 번호
		
		System.out.println("currentPage : " + currentPage);
		int dbstart = (currentPage -1) * rowSize;			// DB에서 시작할 행 번호
		int dbend = currentPage * rowSize;					// DB에서 끝낼 행 번호	//dbend - dbstart => 꺼낼 개수
		
		int numInweb = allcount - (currentPage - 1) * rowSize;	//웹에서 num을 셀 변수 // DB의 num은 혼동됨.
		
		Vector<Freeboard> v = fdao.getAllBoard(dbstart, rowSize);	//db에서 가져온 게시글 벡터
		for(int i = 0; i < v.size(); i++) {
			System.out.print("작성자 : " + v.get(i).getWriter());
			System.out.print("/ 제목 : " + v.get(i).getSubject());
			System.out.println();
		}
		
		request.setAttribute("v", v);					//보여줄 게시글 담은 벡터
		request.setAttribute("number", numInweb);		//웹에서 보여줄 최대 번호
		request.setAttribute("rowSize", rowSize);		//보여줄 row 개수
		request.setAttribute("count", allcount);		//전체 게시글 개수
		request.setAttribute("currentPage", currentPage);// 현재 페이지 넘버
		
		return "/user/free/01_boardlist.jsp";
	}
	
}
