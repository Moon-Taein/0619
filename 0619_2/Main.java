import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.DBUtil;

public class Main {
	public static void main(String[] args) {
		Connection conn = null;
		// 동적 파라미터 표현가능, SQL Injection 보호
		PreparedStatement stmt = null;
		ResultSet rs = null;
		// 문자열 결합으로 sql문을 조작하면 sql-injection에 취약함
		String param = "그랜저";
		try {
			conn = DBUtil.getConnection();
			// PreparedStatement 동적 파라미터 ? 로 표시
			String query = "select * from cars where type = ?";
			stmt = conn.prepareStatement(query);
			// setxxxx(몇번째물음표, 설정할값)
			stmt.setString(1, param);

			rs = stmt.executeQuery();

			// ResultSet approach
			if (rs.next()) {
				String number = rs.getString("number");
				String type = rs.getString("type");

				System.out.println("번호: " + number + ", 차종: " + type);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs);
			DBUtil.close(stmt);
			DBUtil.close(conn);
		}
	}
}
