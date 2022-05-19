package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.BookVo;

public class BookDao {

	public boolean insert(BookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategory_no());

			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null)
					connection.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean update(BookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update book set title=? where no=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getNo());

			int count = pstmt.executeUpdate();

			result = count == 1;
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null)
					connection.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
//	
//	public void delete() {
//		Connection connection = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			connection = getConnection();
//
//			String sql = "delete from book";
//			pstmt = connection.prepareStatement(sql);
//
//			pstmt.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("드라이버 로딩 실패:" + e);
//		} finally {
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//				if (connection != null)
//					connection.close();
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}

	private Connection getConnection() throws SQLException {
		Connection connection = null;

		try {
			Class.forName("org.mariadb.jdbc.Driver");
			String url = "jdbc:mysql://192.168.10.51:3306/bookmall?charset=utf8";
			connection = DriverManager.getConnection(url, "bookmall", "bookmall");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return connection;
	}

	public List<BookVo> find() {
		List<BookVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select a.no, a.title, a.price, b.no, b.name from book a, category b "
					+ " where a.category_no = b.no";
			pstmt = connection.prepareStatement(sql);

			// 4. Parameter Mapping

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과 처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				Long price = rs.getLong(3);
				Long category_no = rs.getLong(4);
				String category = rs.getString(5);

				BookVo vo = new BookVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategory_no(category_no);
				vo.setCategory(category);

				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (connection != null)
					connection.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
}
