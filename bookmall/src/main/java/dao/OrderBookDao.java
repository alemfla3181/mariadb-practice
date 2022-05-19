package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.OrderBookVo;

public class OrderBookDao {
	public boolean insert(OrderBookVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "insert into order_book values(?, ?, ?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
			pstmt.setLong(2, vo.getBook_no());
			pstmt.setString(3, vo.getAmount());

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

	public List<OrderBookVo> find() {
		List<OrderBookVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select b.no, b.title, b.price, a.amount" + " from order_book a, book b"
					+ " where a.book_no = b.no";
			pstmt = connection.prepareStatement(sql);

			// 4. Parameter Mapping

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과 처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String payment = rs.getString(3);
				String amount = rs.getString(4);

				OrderBookVo vo = new OrderBookVo();
				vo.setNo(no);
				vo.setBook_title(title);
				vo.setBook_payment(payment);
				vo.setAmount(amount);
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
