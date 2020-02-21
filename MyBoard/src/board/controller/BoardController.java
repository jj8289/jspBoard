package board.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 중요!
import freeboard.action.*;
import noticeboard.action.*;
import onetooneboard.action.*;
import faqboard.action.*;
import board.model.BoardCommand;

@WebServlet(
		urlPatterns = { "*.do"}, 
		initParams = { 	// 한번만 실행이 됨.
				// 초기화 파라미터
				@WebInitParam(name = "propertyConfig", value = "mapping.properties")
		})
public class BoardController extends HttpServlet{
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	
	public void init(ServletConfig config) throws ServletException {
		
		//config 객체 => 서블릿(JSP)마다 하나씩 생성된다. 초기화 파라미터를 갖고있다.
		//config 객체 얻기
		String props = config.getInitParameter("propertyConfig");
		//context 객체 => 다른 내장객체를 얻을 수 있는 다양한 메서드를 제공한다.
		//context 객체 얻기
		ServletContext context = config.getServletContext();
		
		String realFolder = "";
		
		// realPath => 실제 파일이 있는 위치
		// mapping.properties에서 = 뒤에 주소를 의미!
		String realPath = context.getRealPath(realFolder) + "\\" + props;
		System.out.println(realPath);
		
		Properties pr = new Properties();
		FileInputStream f = null;
		
		// 매핑
		try {
			f = new FileInputStream(realPath);
			// mapping.properties를 f에 담아서 pr에 로드시킨다(저장) => 즉, mapping.properties의 내용을 인식한다.
			pr.load(f);	// 앞주소=뒷주소 를 정의하는 코드
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(f != null) {try {f.close();} catch (IOException e) {}}
		}
		
		// Map형태로 pr에 담는다. => mapping.properties의 = 앞부분은 key, 뒷부분은 value
		// Map에 key와 value로 넣기
		// pr은 맵임(key는 매핑의 앞주소, value는 뒷주소)
		Iterator<?> keyIter = pr.keySet().iterator();
		while(keyIter.hasNext()) {
			
			// command => key
			String command = (String)keyIter.next();	//pr의 키(key)// KeyIter.next() ==> 맵에서 key 가져오기
			String className = pr.getProperty(command);	//pr의 값(value)// pr.gteProperty(key) ==> pr 맵에서 key로 value 가져오기
			try {
				// 클래스(틀) 지정
				Class<?> commandClass = Class.forName(className);		// new를 한 것.
				//클래스(객체) 지정 ==> 실제 클래스 // 오브젝트
				Object commandInstance = commandClass.newInstance();	// value를 클래스로 정함
				
				commandMap.put(command, commandInstance); 	// 맵에 key, value 넣기 => 맵.put(key, value)
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		requestPro(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		requestPro(request, response);
	}

	// jsp 파일에서 request를 보내면 여기로 들어옴
	private void requestPro(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String view = "";
		BoardCommand com = null;
		try {
			// 만약 main.jsp 에서 /list.do를 보낸다 
			// 1. command = /list.do
			// 2. com = board.action.BoardList
			// 3. view = command 인터페이스로 보냄.
			// 4. dispatcher로 view(board.action.BoardList)에 jsp 내용 그대로 전달
			String command = request.getRequestURI();
			if(command.indexOf(request.getContextPath()) == 0) {
				//command에 mapping.properties에서 작성한 앞부분을 저장한다.
				command = command.substring(request.getContextPath().length());
			}
			com = (BoardCommand)commandMap.get(command);	// command(key)로 꺼내서 value를 com 변수에 저장
			
			// command(맵의 key)			=> /list.do
			// com(key(command)의 value)	=> BoardList.java(BoardCommand를 상속받은 클래스) => BoardList == BoardCommand라도 봐도 됌
			// view 					=> BoardList의 return 값(String 값)
			view = com.requestPro(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		// view ==> action java 파일에서 코딩한 것.
		// action(java)에서 코딩한 것(view)을 jsp 파일로 보냄 
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
		
	}


}
