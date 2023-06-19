public class TestCreateTable {
	public static void main(String[] args) {
		// driver load
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// repository class create
		CarRepository repo = new CarRepository();

		// call create table
		repo.createTable();

		System.out.println("테이블 생성 테스트 완료");
	}
}