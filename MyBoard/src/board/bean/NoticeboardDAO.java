package board.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class NoticeboardDAO {
	private static NoticeboardDAO instance = new NoticeboardDAO();
	public static NoticeboardDAO getInstance() {
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
	
	public int getAllcount() {
		int count = 0;
		try {
			conn = getConnection();
			String sql = "select count(*) from noticeboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			System.out.println("count : " + count);
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
    	System.out.println("=== [allCount()] : " + count + " ===");
    	
    	return count;
	}
	
	public Vector<Noticeboard> getAllBoard(int start, int count) {
		//list => ref는 높은 숫자부터 출력, reLevel은 낮은 숫자부터 출력
		
		Vector<Noticeboard> vec = new Vector<Noticeboard>();
		
		try {
			conn = getConnection();
			String sql = "SELECT * FROM noticeboard ORDER BY ref DESC LIMIT ?, ?;";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Noticeboard bean = new Noticeboard();
				bean.setNo(rs.getInt(1));
				bean.setKind(rs.getInt(2));
				bean.setSubject(rs.getString(3));
				bean.setWriteDate(rs.getDate(4).toString());
				bean.setRef(rs.getInt(5));
				bean.setReadCount(rs.getInt(6));
				bean.setContent(rs.getString(7));
				vec.add(bean);
			}
		} catch (Exception e) {e.printStackTrace();}
		finally {
			if (conn != null) {try {conn.close();} catch (SQLException e) {}}
			if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
			if (rs != null) {try {rs.close();} catch (SQLException e) {}}
		}
		return vec;
	}
	
	public void addNotice(Noticeboard board) {
		int no = 0; int ref = no;
		try {
			conn = getConnection();
			String sql = "SELECT MAX(no) from noticeboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				no = rs.getInt(1) + 1;
				ref = no;
			}
			
			String sql2 = "INSERT INTO noticeboard(no, kind, subject, writeDate, ref, readcount, content) "
						+ "VALUES(?, ?, ?, now(), ?, 0, ?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, no);
			pstmt.setInt(2, board.getKind());
			pstmt.setString(3, board.getSubject());
			pstmt.setInt(4, ref);
			pstmt.setString(5, board.getContent());
			pstmt.executeUpdate();
			System.out.println("추가 완료!");
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
	}
	
	public Noticeboard getOne(int no) {	// 조회수 +1 하고 글 가져오기
		Noticeboard board = new Noticeboard();
		try {
			conn = getConnection();
			String sql2 = "UPDATE noticeboard SET readCount = readCount + 1 WHERE no = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			String sql = "SELECT * FROM noticeboard WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setNo(no);
				board.setKind(rs.getInt(2));
				board.setSubject(rs.getString(3));
				board.setWriteDate(rs.getDate(4).toString());
				board.setRef(rs.getInt(5));
				board.setReadCount(rs.getInt(6));
				board.setContent(rs.getString(7));
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return board;
	}
	
	public Noticeboard getOne2(int no) {
		Noticeboard board = new Noticeboard();
		try {
			conn = getConnection();
			String sql = "SELECT * FROM noticeboard WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				board.setNo(no);
				board.setKind(rs.getInt(2));
				board.setSubject(rs.getString(3));
				board.setWriteDate(rs.getDate(4).toString());
				board.setRef(rs.getInt(5));
				board.setReadCount(rs.getInt(6));
				board.setContent(rs.getString(7));
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return board;
	}
	
	public void deleteNotice(int no) {
		try {
			conn = getConnection();
			String sql = "DELETE FROM noticeboard WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
	}
	
	public void updateNotice(Noticeboard board) {
		try {
			conn = getConnection();
			String sql = "UPDATE noticeboard SET subject = ?, content = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getContent());
			pstmt.setInt(3, board.getNo());
			pstmt.executeUpdate();
			System.out.println("업데이트 완료!");
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
	}
	
}
