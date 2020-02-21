package board.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FAQboardDAO {
	private static FAQboardDAO instance = null;
	private FAQboardDAO() {
		init();
	}
	public static FAQboardDAO getInstance() {
		if(instance == null) {
			instance = new FAQboardDAO();
		}
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
	
	public void init() {
		try {
			conn = getConnection();
			FAQboard[] bean = new FAQboard[5];
			for(int i = 0; i < 5; i++) {
				bean[i].setNo(i);
				bean[i].setRef(i);
			}
			bean[0].setSubject("FAQ1");
			bean[0].setAnswer("answer1");
			bean[1].setSubject("FAQ2");
			bean[1].setAnswer("answer2");
			bean[2].setSubject("FAQ3");
			bean[2].setAnswer("answer3");
			bean[3].setSubject("FAQ4");
			bean[3].setAnswer("answer4");
			bean[4].setSubject("FAQ5");
			bean[4].setAnswer("answer5");
			
			
			String sql = "INSERT INTO faqboard(no, subject, writeDate, ref, readCount, answer )"
						+ " VALUES(?, ?, now(), ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			for(int i = 0; i < 5; i++) {
				pstmt.setInt(1, bean[i].getNo());
				pstmt.setString(2, bean[i].getSubject());
				pstmt.setInt(3, bean[i].getRef());
				pstmt.setInt(4, 0);
				pstmt.setString(5, bean[i].getAnswer());
			}
			pstmt.executeUpdate();
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
	}
	
	public int getAllcount() {
		int count = 0;
		try {
			conn = getConnection();
			String sql = "SELECT count(*) FROM faqboard";
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
	
	public Vector<FAQboard> getAllBoard(int start, int count){
		Vector<FAQboard> vec = new Vector<>();
		try {
			conn = getConnection();
			String sql = "SELECT * FROM faqboard ORDER BY ref DESC LIMIT ?, ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				FAQboard bean = new FAQboard();
				bean.setNo(rs.getInt(1));
				bean.setSubject(rs.getString(2));
				bean.setWriteDate(rs.getDate(3).toString());
				bean.setRef(rs.getInt(4));
				bean.setReadCount(rs.getInt(5));
				bean.setAnswer(rs.getString(6));
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
	
	public FAQboard getOne(int no) {
		FAQboard bean = new FAQboard();
		try {
			conn = getConnection();
			String sql2 = "UPDATE faqboard SET readCount = readCount + 1 WHERE no = ?";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			String sql = "SELECT * FROM faqboard WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNo(rs.getInt(1));
				bean.setSubject(rs.getString(2));
				bean.setWriteDate(rs.getDate(3).toString());
				bean.setRef(rs.getInt(4));
				bean.setReadCount(rs.getInt(5));
				bean.setAnswer(rs.getString(6));
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return bean;
	}
	
	public FAQboard getOne2(int no) {
		FAQboard bean = new FAQboard();
		try {
			conn = getConnection();
			String sql = "SELECT * FROM faqboard WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				bean.setNo(rs.getInt(1));
				bean.setSubject(rs.getString(2));
				bean.setWriteDate(rs.getDate(3).toString());
				bean.setRef(rs.getInt(4));
				bean.setReadCount(rs.getInt(5));
				bean.setAnswer(rs.getString(6));
			}
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
		return bean;
	}
	
	
	public void addFAQ(FAQboard board) {
		// no + 1 // ref + 1
		// readCount = 0 // writeDate = now()
		int no = 0; int ref = 0;
		try {
			conn = getConnection();
			String sql = "SELECT MAX(ref) FROM faqboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ref = rs.getInt(1) + 1;
			}
			
			String sql2 = "SELECT MAX(no) FROM faqboard";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				no = rs.getInt(1) + 1;
			}
			
			String sql3 = "INSERT INTO faqboard(no, subject, writeDate, ref, readCount, answer) "
						+ "VALUES(?, ?, now(), ?, 0, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, no);
			pstmt.setString(2, board.getSubject());
			pstmt.setInt(3, ref);
			pstmt.setString(4, board.getAnswer());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
	}
	
	public void updateFAQ(FAQboard board) {
		try {
			conn = getConnection();
			String sql = "UPDATE faqboard SET subject = ?, answer = ? WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getSubject());
			pstmt.setString(2, board.getAnswer());
			pstmt.setInt(3, board.getNo());
			pstmt.executeUpdate();
			
		} catch (Exception e) {}
    	finally {
			if(conn != null) {try {conn.close();} catch (Exception e) {}}
			if(pstmt != null) {try {pstmt.close();} catch (Exception e) {}}
			if(rs != null) {try {rs.close();} catch (Exception e) {}}
		}
	}
	
	public void deleteFAQ(int no) {
		try {
			conn = getConnection();
			String sql = "DELETE FROM faqboard WHERE no = ?";
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
}
