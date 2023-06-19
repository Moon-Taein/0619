public class TestDelete {
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
		int result = repo.delete("9876");

		System.out.println("행 조회 결과: " + (result == 1));

	}
}