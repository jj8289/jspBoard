package board.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//jsp에서 보낸 request나 response를 받는 역할
//틀(인터페이스)로 만듦 ==> 각 기능 클래스(board.action)에서 인터페이스를 상속받아서 공통적인 틀(jsp에서 내용받을 때)로 사용하기 위해
public interface BoardCommand {
	
	// String 값으로 반환 하는 메소드
	// 이 인터페이스를 상속받는 모든 클래스에선 reqeust를 받아서 String으로 반환하는 함수를 쓸 수 있음
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

