package board.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class OnetooneboardDAO {
	private static OnetooneboardDAO instance = new OnetooneboardDAO();
	public static OnetooneboardDAO getInstance() {
		return instance;
	}
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public Connection getConnection() {
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource ds = (DataSource) envctx.lookup("jdbc/pool");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void addOTO(Onetooneboard board) {
		System.out.println("addOTO()");
		int no = 0; int ref = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MAX(no) FROM onetooneboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			}
			
			String sql2 = "SELECT MAX(ref) FROM onetooneboard";
			pstmt = conn.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt(1) + 1;
			}

			String sql3 = "INSERT INTO onetooneboard(no, writer, subject, writeDate, ref, reStep, content, complete, answer)"
						+ " VALUES(?, ?, ?, now(), ?, 1, ?, false, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, no);
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getSubject());
			pstmt.setInt(4, ref);
			pstmt.setString(5, board.getContent());
			pstmt.setString(6, "");
			pstmt.executeUpdate();
			System.out.println("추가 성공!");
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
	}
	
	public Vector<Onetooneboard> getOTO(String id) {
		// 한 유저의 문의글이 여러개일 수 있어서 벡터에 담기
		// 한 유저의 모든 문의글
		Vector<Onetooneboard> otolist = new Vector<>();
		Onetooneboard bean = null;
		try {
			conn = getConnection();
			String sql = "SELECT * FROM onetooneboard WHERE writer = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean = new Onetooneboard();
				bean.setNo(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setWriteDate(rs.getDate(4).toString());
				bean.setRef(rs.getInt(5));
				bean.setReStep(rs.getInt(6));
				bean.setContent(rs.getString(7));
				bean.setComplete(rs.getBoolean(8));
				bean.setAnswer(rs.getString(9));
				otolist.add(bean);
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return otolist;
	}
	
	public int getAllCount() {
		int count = 0;
		try {
			conn = getConnection();
			String sql = "SELECT COUNT(*) FROM onetooneboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return count;
	}
	
	public Vector<Onetooneboard> getPageOTO(int start, int count, String id){
		Vector<Onetooneboard> vec = new Vector<>();
		try {
			conn = getConnection();
			String sql = "SELECT * FROM onetooneboard ORDER BY ref DESC LIMIT ?, ? WHERE writer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, count);
			pstmt.setString(3, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Onetooneboard bean = new Onetooneboard();
				bean.setNo(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setWriteDate(rs.getDate(4).toString());
				bean.setRef(rs.getInt(5));
				bean.setReStep(rs.getInt(6));
				bean.setContent(rs.getString(7));
				bean.setComplete(rs.getBoolean(8));
				bean.setAnswer(rs.getString(9));
				vec.add(bean);
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return vec;
	}
	
	//==================================================
	// 관리자용 답변안한 전체 글 개수 가져오기
	public int MG_getAllCount() {
		int count = 0;
		try {
			conn = getConnection();
			String sql = "SELECT COUNT(*) FROM onetooneboard WHERE complete = false";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return count;
	}
	// 관리자용 전체 문의 글 가져오기(start : 시작 인덱스 / count : 자를 개수)
	public Vector<Onetooneboard> MG_getAllList(int start, int count) {
		Vector<Onetooneboard> list = new Vector<>();
		try {
			conn = getConnection();
			String sql = "SELECT * FROM onetooneboard WHERE complete = false ORDER BY ref LIMIT ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Onetooneboard bean = new Onetooneboard();
				bean.setNo(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setWriteDate(rs.getDate(4).toString());
				bean.setRef(rs.getInt(5));
				bean.setReStep(rs.getInt(6));
				bean.setContent(rs.getString(7));
				bean.setComplete(rs.getBoolean(8));
				bean.setAnswer(rs.getString(9));
				list.add(bean);
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return list;
	}
	
	public Onetooneboard getOne(int no) {
		Onetooneboard bean = new Onetooneboard();
		try {
			conn = getConnection();
			String sql = "SELECT * FROM onetooneboard WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				bean.setNo(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setWriteDate(rs.getDate(4).toString());
				bean.setRef(rs.getInt(5));
				bean.setReStep(rs.getInt(6));
				bean.setContent(rs.getString(7));
				bean.setComplete(rs.getBoolean(8));
				bean.setAnswer(rs.getString(9));
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return bean;
	}
	
	// 관리자 답변한 내용 DB에 업데이트
	public void MG_rewrite(Onetooneboard bean) {
		try {
			conn = getConnection();
			String sql = "UPDATE onetooneboard SET complete = true, answer = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getAnswer());
			pstmt.setInt(2, bean.getNo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
	}
	//해당 유저 문의글 총 개수
	public int User_getAllCount(String id) {
		int count = 0;
		try {
			conn = getConnection();
			String sql = "SELECT COUNT(*) FROM onetooneboard WHERE writer=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return count;
	}
	//현재 페이지에 해당 유저 문의글 모두 가져오기
	public Vector<Onetooneboard> User_getAllList(String id, int start, int count){
		Vector<Onetooneboard> list = new Vector<>();
		try {
			conn = getConnection();
			// boolean ==> 0(false) / 1(true)
			String sql = "SELECT * FROM onetooneboard WHERE writer = ? ORDER BY complete ASC, writeDate DESC LIMIT ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, start);
			pstmt.setInt(3, count);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Onetooneboard bean = new Onetooneboard();
				bean.setNo(rs.getInt(1));
				bean.setWriter(rs.getString(2));
				bean.setSubject(rs.getString(3));
				bean.setWriteDate(rs.getDate(4).toString());
				bean.setRef(rs.getInt(5));
				bean.setReStep(rs.getInt(6));
				bean.setContent(rs.getString(7));
				bean.setComplete(rs.getBoolean(8));
				bean.setAnswer(rs.getString(9));
				list.add(bean);
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return list;
	}
}
