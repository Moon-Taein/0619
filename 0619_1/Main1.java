
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// 소통의 방식을 정하는거 - interface
// 추상메소드 - 니가 만들어서 써라
// jdbc interface class 들의 모음 -> driver

public class Main1 {
	public static void main(String[] args) {
		// jdbc:mysql jdbc와 mysql 프로토콜
		// 포트번호
		String url = "jdbc:mysql://localhost:3306/world";
		String username = "root";
		String password = "root";

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// mysql - jdbc 드라이버 적재
			// 클래스를 로딩하는 메소드 (패키지이름 클래스이름)
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 적재 성공");

			// jdbc driver를 통해서 연결을 해야함
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("데이터베이스 연결 성공");

			//
			String query = "select * from city";
			// SQL 명령어를 수행할 Statement 객체 생성
			stmt = conn.createStatement();
			// 명령 수행 및 결과 반환 객체 ResultSet
			rs = stmt.executeQuery(query);
			// 테이블의 한 레코드를 가르키는 커서를 이동시키는 메소드
			// 다음행 있으면 true / 없으면 false

			while (rs.next()) {
				int value = rs.getInt(1);
				String name = rs.getString(2);
				String code = rs.getString(3);
				String district = rs.getString(4);
				int population = rs.getInt(5);

				System.out.print("no: " + value);
				System.out.print(" name: " + name);
				System.out.print(" code: " + code);
				System.out.print(" district: " + district);
				System.out.println(" population: " + population);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("드라이버를 찾을 수 없습니다");
		} catch (SQLException e) { // checked exception / unchecked exception
			e.printStackTrace();
			System.out.println("데이터베이스 연결에 실패하였습니다");
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					System.out.println("연결을 해제하였습니다");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
