package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.OrderVo;

public class OrderDao {
	public boolean insert(OrderVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "insert into orders(no, payment, address, member_no) values(null, ?, ?, ?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getPayment());
			pstmt.setString(2, vo.getAddress());
			pstmt.setLong(3, vo.getMember_no());

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

	public boolean update(OrderVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update orders set address=? where no=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getAddress());
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

	public List<OrderVo> find() {
		List<OrderVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select a.no, date_format(a.date, \"%Y년 %m월 %d일\"), a.payment, a.address, b.no, b.name, b.email from orders a, member b where a.member_no = b.no";
			pstmt = connection.prepareStatement(sql);

			// 4. Parameter Mapping

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과 처리
			while (rs.next()) {
				Long no = rs.getLong(1);
				String date = rs.getString(2);
				String payment = rs.getString(3);
				String address = rs.getString(4);
				Long member_no = rs.getLong(5);
				String member = rs.getString(6);
				String email = rs.getString(7);

				OrderVo vo = new OrderVo();
				vo.setNo(no);
				vo.setDate(date);
				vo.setPayment(payment);
				vo.setAddress(address);
				vo.setMember_no(member_no);
				vo.setMember(member);
				vo.setEmail(email);
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
