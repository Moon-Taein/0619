import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbutil.DBUtil;

public class CarRepository implements ICarRepository {

	@Override
	public void createTable() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// 최초로 스태틱 클래스를 사용하면 클래스 로드 작업이 일어남
			conn = DBUtil.getConnection();
			String sql = "create table if not exists cars(\r\n" + "	number char(4) not null unique\r\n"
					+ "    , type varchar(15) not null\r\n" + "    \r\n" + "    , primary key (number)\r\n" + ");";
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}

	@Override
	public int insert(String number, String type) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into cars (number, type) values (?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, number);
			stmt.setString(2, type);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

	@Override
	public List<Car> selectAll() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from cars";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String number = rs.getString("number");
				String type = rs.getString("type");
				list.add(new Car(number, type));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return list;
	}

	@Override
	public Car selectByPk(String number) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from cars where number = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, number);
			rs = stmt.executeQuery();
			while (rs.next()) {
				String numberParsed = rs.getString("number");
				String type = rs.getString("type");
				return new Car(numberParsed, type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public int delete(String number) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Car> list = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			String sql = "delete from cars where number = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, number);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
		return 0;
	}

}