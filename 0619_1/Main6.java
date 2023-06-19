import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main6 {
	private static final String URL = "jdbc:mysql://localhost:3306/my_db";
	private static final String USER = "root";
	private static final String PASSWORD = "root";

	// 커피 테이블의 행을 추가하는 메소드
	public static int insertCoffee(String name, int price) {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "insert into coffee (name, price) values (?, ?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setInt(2, price);
			return stmt.executeUpdate();
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

	// 커피 테이블의 행을 수정하는 메소드
	// 커피의 번호와 , 새 가격을 parameter 로
	public static int updateCoffee(int no, int price) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "update coffee set price = ? where no = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, price);
			stmt.setInt(2, no);
			return stmt.executeUpdate();

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

	// 메소드 오버라이딩
	// 커피 테이블의 행을 수정하는 메소드 ( 1행(name)의 가격을 수정 )
	public static int updateCoffee(String name, int price) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "update coffee set price = ? where name = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, price);
			stmt.setString(2, name);
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

	// 커피 테이블의 행을 삭제하는 메소드 ( 1행(pk) 삭제 )
	public static int dropRecordCoffee(int no) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "delete from coffee where no = no;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, no);
			System.out.println("작성된 명령문: " + sql);
			return stmt.executeUpdate();

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

	// 이름값으로 조회하여 반환( 행의 개수 집계 )
	public static int existRecord(String name) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "select count(*) as `cnt` from coffee where name = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			System.out.println("작성된 명령문: " + sql);
			rs = stmt.executeQuery();
			if (rs.next()) {
				// 없는애들이라도 여기까지는 들어온다
				// 결과에 0이라고 되어있는 테이블
				return rs.getInt("cnt");
			}

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
		// 아예 못읽었을 경우
		return -1;
	}

	// 모든 행 컬럼을 콘솔 출력하는 메소드
	public static List<Coffee> showTables() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		List<Coffee> list = new ArrayList<>();
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.createStatement();

			String sql = "select * from coffee;";
			System.out.println("작성된 명령문: " + sql);

			rs = stmt.executeQuery(sql);
//			rsmd = rs.getMetaData();
//			while (rs.next()) {
//				for (int i = 1; i < rsmd.getColumnCount() + 1; i++) {
//					System.out.print(rs.getObject(i).toString() + " ");
//				}
//				System.out.print("\n");
//			}
			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				int price = rs.getInt("price");

				list.add(new Coffee(no, name, price));
//				System.out.printf("no: %d, name: %s, price: %d\n", no, name, price);
			}

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
		// 아예 못읽었을 경우
		return list;
	}

	public static void main(String[] args) {
		// 드라이버 로드
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

//		int result1 = insertCoffee("카라멜마끼아또", 3000);
//		int result2 = insertCoffee("바닐라라떼", 2900);
//
//		System.out.println(result1);
//		System.out.println(result2);

//		int result3 = updateCoffee(1, 2000);
//		System.out.println(result3);

//		int result4 = updateCoffee("아메리카노", 5000);
//		System.out.println(result4);

//		int result1 = insertCoffee("자몽허니블랙티", 6000);
//		System.out.println(result1);

//		int result5 = dropRecordCoffee(9);
//		System.out.println(result5);

//		int result6 = existRecord("곡물라떼");
//		System.out.println(result6);

//		String name = "바닐라초코라떼";
//		int cnt = existRecord(name);
//		if (cnt == 0) {
//			insertCoffee(name, 9000);
//		}
		List<Coffee> list = showTables();
		System.out.println(list);
	}
}