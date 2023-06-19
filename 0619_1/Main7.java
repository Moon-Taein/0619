import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class Main7 {
	private static final String URL = "jdbc:mysql://localhost:3306/my_db";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	// 테이블 생성
	public static int createTable() {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String sql = "create table IF NOT EXISTS parkinglot (" + "no int auto_increment"
					+ ", cartype varchar(20) not null" + ", carnumber varchar(4) not null" + ", primary key (no)"
					+ ", unique (carnumber) );";
			System.out.println("실행한 sql 문: " + sql);
			return stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	// 행 추가
	public static int insertParkinglot(String cartype, String carnumber) {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();

			// unique 하다고 에러 뜨는데 굳이 이렇게 할 필요가 있나?
			String sql = "INSERT INTO parkinglot (cartype, carnumber)\r\n" + "SELECT '" + cartype + "', '" + carnumber
					+ "' FROM DUAL\r\n" + "WHERE NOT EXISTS\r\n" + "(SELECT cartype, carnumber FROM parkinglot\r\n"
					+ " WHERE cartype = '" + cartype + "' AND carnumber = '" + carnumber + "');";
			System.out.println("실행한 sql 문: " + sql);
			return stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	// pk값으로 행 삭제
	public static int deleteRecordWithNo(int no) {
		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String sql = "delete from parkinglot where no = '" + no + "';";
			System.out.println("실행한 sql 문: " + sql);
			return stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	// 모든 행 조회
	public static int readEntireRow() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String sql = "select * from parkinglot";
			System.out.println("실행한 sql 문: " + sql);
			rs = stmt.executeQuery(sql);
			// column 의 수를 가져오기 위한 metadata
			rsmd = rs.getMetaData();

			while (rs.next()) {
				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
					System.out.print(rs.getObject(i).toString() + " ");
				}
				System.out.println();
			}

//			while (rs.next()) {
//				int no = rs.getInt("no");
//				String cartype = rs.getString("cartype");
//				String carnumber = rs.getString("carnumber");
//
//				System.out.printf("no:%d type:%s number:%S\n", no, cartype, carnumber);
//			}

			return 1;

		} catch (SQLException e) {
			e.printStackTrace();
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	// pk값으로 1행 조회
	public static int readRowWithNo(int no) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String sql = "select * from parkinglot where no = '" + no + "'";
			System.out.println("실행한 sql 문: " + sql);
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int number = rs.getInt("no");
				String cartype = rs.getString("cartype");
				String carnumber = rs.getString("carnumber");

				System.out.printf("no:%d type:%s number:%S\n", number, cartype, carnumber);
			}
			return 1;

		} catch (SQLException e) {
			e.printStackTrace();
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
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}

	public static void main(String[] args) {
		// 드라이버 로드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println(createTable());
		insertParkinglot("중형차", "가455");
		insertParkinglot("대형차", "나455");
		insertParkinglot("소형차", "다455");
		deleteRecordWithNo(5);
		readEntireRow();
		readRowWithNo(1);

	}
}
