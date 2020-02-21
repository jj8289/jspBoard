package board.bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class FreeboardDAO {
		//싱글톤
		private static FreeboardDAO fdao = new FreeboardDAO();
		public static FreeboardDAO getInstance() {
			return fdao;
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
		
		public void addBoard(Freeboard board) {
			int ref = 0;
			int no = 0;
			
			try {
				conn = getConnection();
				String refSql = "SELECT MAX(ref) FROM freeboard";
				pstmt = conn.prepareStatement(refSql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					ref = rs.getInt(1) + 1;
				}
				String numSql = "SELECT MAX(no) FROM freeboard";
				pstmt = conn.prepareStatement(numSql);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					no = rs.getInt(1) + 1;
				}

				String sql = "INSERT INTO freeboard (no , writer, subject, "
						+ "password, writeDate, ref, reStep, reLevel, "
						+ "readCount, content) VALUES(?, ?, ?, ?, now(), ?, 1, 1, 0, ?)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setString(2, board.getWriter());
				pstmt.setString(3, board.getSubject());
				pstmt.setString(4, board.getPassword());
				pstmt.setInt(5, ref);
				pstmt.setString(6, board.getContent());
				pstmt.executeUpdate();
			} catch (Exception e) {e.printStackTrace();}
			finally {
				if (conn != null) {try {conn.close();} catch (SQLException e) {}}
				if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
				if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			}
		}
		
		public int getallcount() {
			int count = 0;
			try {
				conn = getConnection();
				String sql = "select count(*) from freeboard";
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
	    	System.out.println("=== [allCount()] : " + count + " ===");
	    	
	    	return count;
		}
		
		public Vector<Freeboard> getAllBoard(int start, int count) {
			//list => ref는 높은 숫자부터 출력, reLevel은 낮은 숫자부터 출력
			
			Vector<Freeboard> vec = new Vector<Freeboard>();
			
			try {
				conn = getConnection();
				String sql = "SELECT * FROM freeboard ORDER BY ref DESC , reLevel LIMIT ?, ?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, start);
				pstmt.setInt(2, count);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					Freeboard bean = new Freeboard();
					bean.setNo(rs.getInt(1));
					bean.setWriter(rs.getString(2));
					bean.setSubject(rs.getString(3));
					bean.setPassword(rs.getString(4));
					bean.setWriteDate(rs.getDate(5).toString());
					bean.setRef(rs.getInt(6));
					bean.setReStep(rs.getInt(7));
					bean.setReLevel(rs.getInt(8));
					bean.setReadCount(rs.getInt(9));
					bean.setContent(rs.getString(10));
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
		
		//조회수 +1하면서 게시글 가져오기 // list -> info 에서 해당 글 번호 받고 조회수 +1
		public Freeboard getOne(int no) {	
			Freeboard bean = new Freeboard();
			try {
				conn = getConnection();
				String sql2 = "update freeboard set readcount = readcount+1 where no=?";
	    		pstmt = conn.prepareStatement(sql2);
	    		pstmt.setInt(1, no);
	    		pstmt.executeUpdate();
				
				String sql = "SELECT * FROM freeboard WHERE no = ?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					bean.setNo(rs.getInt(1));
					bean.setWriter(rs.getString(2));
					bean.setSubject(rs.getString(3));
					bean.setPassword(rs.getString(4));
					bean.setWriteDate(rs.getDate(5).toString());
					bean.setRef(rs.getInt(6));
					bean.setReStep(rs.getInt(7));
					bean.setReLevel(rs.getInt(8));
					bean.setReadCount(rs.getInt(9));
					bean.setContent(rs.getString(10));
				}
			} catch (Exception e) {e.printStackTrace();}
			finally {
				if (conn != null) {try {conn.close();} catch (SQLException e) {}}
				if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
				if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			}
			return bean;
		}
		
		// 게시글 바로 가져오기
		public Freeboard getOne2(int no) {
			Freeboard bean = new Freeboard();
			try {
				conn = getConnection();
				String sql = "SELECT * FROM freeboard WHERE no = ?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					bean.setNo(rs.getInt(1));
					bean.setWriter(rs.getString(2));
					bean.setSubject(rs.getString(3));
					bean.setPassword(rs.getString(4));
					bean.setWriteDate(rs.getDate(5).toString());
					bean.setRef(rs.getInt(6));
					bean.setReStep(rs.getInt(7));
					bean.setReLevel(rs.getInt(8));
					bean.setReadCount(rs.getInt(9));
					bean.setContent(rs.getString(10));
				}
			} catch (Exception e) {e.printStackTrace();}
			finally {
				if (conn != null) {try {conn.close();} catch (SQLException e) {}}
				if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
				if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			}
			return bean;
		}
		
		public void updateBoard(Freeboard b) {
			Freeboard board = new Freeboard();
			try {
				conn = getConnection();
				String sql = "update freeboard set subject=?, content=? where no = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, b.getSubject());
				pstmt.setString(2, b.getContent());
				pstmt.setInt(3, b.getNo());
				pstmt.executeUpdate();	
				System.out.println("업데이트 완료!");
				
			} catch (Exception e) {e.printStackTrace();}
			finally {
				if (conn != null) {try {conn.close();} catch (SQLException e) {}}
				if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
				if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			}
		}
		
		public void deleteBoard(int no) {
			try {
				conn = getConnection();
				String sql = "DELETE FROM freeboard WHERE no = ?;";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.executeUpdate();
				
			} catch (Exception e) {e.printStackTrace();}
			finally {
				if (conn != null) {try {conn.close();} catch (SQLException e) {}}
				if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
				if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			}
			
		}
		
		public boolean checkPassword(int no, String pw) {
			boolean istrue = false;
			try {
				conn = getConnection();
				String sql = "SELECT * FROM freeboard WHERE no = ? and password = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, no);
				pstmt.setString(2, pw);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					istrue = true;
				}
				else {
					istrue = false;
				}
				
			} catch (Exception e) {e.printStackTrace();}
			finally {
				if (conn != null) {try {conn.close();} catch (SQLException e) {}}
				if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
				if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			}
			
			return istrue;
		}
		
		public void rewriteBoard(Freeboard parent, Freeboard son) {	// 부모글 받아와서 지지고 볶고
			int no = 0;
			int ref = parent.getRef();				// 부모글 ref
			int reStep = parent.getReStep();			// 부모글 reStep
			int reLevel = parent.getReLevel();		// 부모글 reLevel
			
			try {
				conn = getConnection();
				
				String sql = "SELECT max(no) FROM freeboard";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					no = rs.getInt(1) + 1;			// 답변글 no
				}
				
				String sql2 =  "UPDATE freeboard SET reLevel=reLevel+1 "
							+ "WHERE ref=? and reLevel > ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, reLevel);
				pstmt.executeUpdate();
				
				String sql3 = "INSERT INTO freeboard(no, writer, subject, password, writeDate, "
							+ "ref, reStep, reLevel, readCount, content)"
							+ "VALUES(?, ?, ?, ?, now(), ?, ?, ?, 0, ?)";
				pstmt = conn.prepareStatement(sql3);
				pstmt.setInt(1, no);
				pstmt.setString(2, son.getWriter());
				pstmt.setString(3, son.getSubject());
				pstmt.setString(4, son.getPassword());
				pstmt.setInt(5, ref);
				pstmt.setInt(6, reStep + 1);
				pstmt.setInt(7, reLevel + 1);
				pstmt.setString(8, son.getContent());
				pstmt.executeUpdate();
				System.out.println("답글 DB 저장 완료");
				
			} catch (Exception e) {e.printStackTrace();}
			finally {
				if (conn != null) {try {conn.close();} catch (SQLException e) {}}
				if (pstmt != null) {try {pstmt.close();} catch (SQLException e) {}}
				if (rs != null) {try {rs.close();} catch (SQLException e) {}}
			}
		}
}
