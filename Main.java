package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


//이클립스 라인정렬 Ctrl + shift + F

public class Main {
	public void db_conn()
	{
		java.sql.Connection conn;
		try {
			// 해당 클래스를 메모리에 로드 해당클래스가 존재하는지 여부 확인
			Class.forName("com.mysql.jdbc.Driver");

			String dbinfo = "jdbc:mysql://localhost:3306/test";
			String dbID   = "root";
			String dbPW   = "1234";

			// "jdbc:mysql://호스트주소 :포트/데이터베이스명","id","password"
			conn = java.sql.DriverManager.getConnection(dbinfo, dbID, dbPW);

			// DB에 명령어를 전달할 수 있는 객체 생성
			java.sql.Statement stmt = conn.createStatement();

			// 쿼리문장이 실행된 결과를 REsultSet 객체의 참조변수에 담는다.
			java.sql.ResultSet rs = stmt.executeQuery("select * from yes where name=10");

			// 결과를 가져와서 자료의 처음부터 끝까지를 화면에 뿌린다.
			String aData[] = new String[4];
			String sLine;

			// beforFirst() : rs의 위치를 시작위치로 이동
			rs.beforeFirst();

			// next() : rs의 위치를 다음위치로 이동 자료의 끝이면 false로 만둠
			while (rs.next()) {
				System.out.println(rs.getString("name")+" "+
								   rs.getString("id")+" "+
								   rs.getString("password")+" "+
								   rs.getString("nun"));
			}// while end
			conn.close();
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버가 존재하지 않습니다." + e);
		} catch (Exception e) {
			System.out.println("오류:" + e);
		}
	}
	public static void main(String arge[])
	{
		new Main();
	}
}
