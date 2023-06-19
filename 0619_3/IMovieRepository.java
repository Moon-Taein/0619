import java.util.List;

public interface IMovieRepository {
	// 테이블 생성
	void createTable();

	// 행 추가 메소드
	int insert(String name, int date, String director, double rating);

	// 행 추가 메소드2
	int insert(Movie movie);

	// 모든 행 조회
	List<Movie> selectAll();

	// 제목일부값으로 검색
	List<Movie> selectByName(String partOfName);

	// 감독명 검색(일치)
	List<Movie> selectByDirector(String director);

	// 평점 범위로 검색
	List<Movie> selectByRating(double ratingTarget, double ratingTarget2);

	// pk값으로 행 삭제
	int delete(int number);
}
