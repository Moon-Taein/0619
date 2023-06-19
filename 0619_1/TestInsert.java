public class TestInsert {
	public static void main(String[] args) {
		// driver load
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		// repository class create
		CarRepository repo = new CarRepository();

		// call insert table
		int result = repo.insert("9876", "그랜저");

		System.out.println("행 생성 결과: " + (result == 1));

	}
}