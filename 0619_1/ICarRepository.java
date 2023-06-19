import java.util.List;

public interface ICarRepository {

	// 테이블 생성
	void createTable();

	// 행 추가 메소드
	int insert(String number, String type);

	// 모든 행 조회
	List<Car> selectAll();

	// pk값으로 1행 조회
	Car selectByPk(String number);

	// pk값으로 행 삭제
	int delete(String number);

}