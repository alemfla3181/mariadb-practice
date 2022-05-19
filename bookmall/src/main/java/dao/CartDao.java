package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.CartVo;

public class CartDao {

	public boolean insert(CartVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "insert into cart values(?, ?, ?)";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getAmount());
			pstmt.setLong(2, vo.getNo());
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

	public boolean update(CartVo vo) {
		boolean result = false;
		Connection connection = null;
		PreparedStatement pstmt = null;

		try {
			connection = getConnection();

			String sql = "update cart set amount=? where book_no=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setString(1, vo.getAmount());
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

	public void delete(long no) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		try {
			connection = getConnection();

			String sql = "delete from cart where book_no=?";
			pstmt = connection.prepareStatement(sql);

			pstmt.setLong(1, no);

			pstmt.executeQuery();
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
	}

	public List<CartVo> find() {
		List<CartVo> result = new ArrayList<>();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			connection = getConnection();

			// 3. SQL 준비
			String sql = "select a.name, b.title, c.amount" + " from member a, book b, cart c"
					+ " where a.no = c.member_no" + " and b.no = c.book_no;";
			pstmt = connection.prepareStatement(sql);

			// 4. Parameter Mapping

			// 5. SQL 실행
			rs = pstmt.executeQuery();

			// 6. 결과 처리
			while (rs.next()) {
				String name = rs.getString(1);
				String title = rs.getString(2);
				String amount = rs.getString(3);

				CartVo vo = new CartVo();
				vo.setMember_name(name);
				vo.setTitle(title);
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
}
