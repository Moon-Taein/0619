public class TestSelectPk {
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
		Car car = repo.selectByPk("9876");

		System.out.println("행 조회 결과: " + car);

	}
}