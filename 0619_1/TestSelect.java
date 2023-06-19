import java.util.List;

public class TestSelect {
	public static void main(String[] args) {
		System.out.println("프로그램 시작");

		// repository class create
		CarRepository repo = new CarRepository();
		System.out.println("저장소 객체 생성");

//		int result = repo.insert("4567", "아반떼");
//		System.out.println("행 생성 결과: " + result);
		// call insert table

		Car car = repo.selectByPk("4567");
		System.out.println(car);

		List<Car> list = repo.selectAll();
		System.out.println("행 조회 결과: " + list);

		int result2 = repo.delete("4567");
		System.out.println("행 삭제 결과: " + (result2 == 1));
	}
}