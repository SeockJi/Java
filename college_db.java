package Project;

import java.awt.Choice;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.fabric.xmlrpc.base.Array;

//이클립스 라인정렬 Ctrl + shift + F

public class college_db {
	Vector[] db_conn(double result, String college) {
		Vector[] vt_result = new Vector[3];
		Vector up = new Vector();
		Vector safe = new Vector();
		Vector down = new Vector();
		java.sql.Connection conn;
		try {
			// 해당 클래스를 메모리에 로드 해당클래스가 존재하는지 여부 확인
			Class.forName("com.mysql.jdbc.Driver");

			String dbinfo = "jdbc:mysql://localhost:3306/college";
			String dbID = "root";
			String dbPW = "1234";

			// "jdbc:mysql://호스트주소 :포트/데이터베이스명","id","password"
			conn = java.sql.DriverManager.getConnection(dbinfo, dbID, dbPW);

			// DB에 명령어를 전달할 수 있는 객체 생성
			Statement stmt = conn.createStatement();
			Statement stmt2 = conn.createStatement();
			Statement stmt3 = conn.createStatement();
			// 쿼리문장이 실행된 결과를 ResultSet 객체의 참조변수에 담는다.
			String Query1 = "select * from "  + college + " where grade between "+(result+1.1)+" AND " + (result+3.0) + " order by grade";
			String Query2 = "select * from "  + college + " where grade between "+(result-1.0)+" AND " + (result+1.0) + " order by grade";
			String Query3 = "select * from "  + college + " where grade between "+(result-3.0)+" AND " + (result-1.1) + " order by grade";
			
			System.out.println(Query1);
			System.out.println(Query2);
			System.out.println(Query3);
			
			ResultSet rs1 = stmt.executeQuery(Query1);
			ResultSet rs2 = stmt2.executeQuery(Query2);
			ResultSet rs3 = stmt3.executeQuery(Query3);
			
			// beforeFirst() : rs의 위치를 시작위치로 이동
			rs1.beforeFirst();
			rs2.beforeFirst();
			rs3.beforeFirst();

			// next() : rs의 위치를 다음위치로 이동 자료의 끝이면 false로 만둠
				while (rs1.next()) {
					System.out.println(rs1.getString("department") + " " + rs1.getString("grade"));
					up.add(rs1.getString("department") + "/" + rs1.getString("grade"));
					//one.add(rs1.getString("department") + "/" + rs1.getString("grade"));
				}
				while (rs2.next()) {
					safe.add(rs2.getString("department") + "/" + rs2.getString("grade"));
				}
				
				int i=0;
				while (rs3.next()){
					//System.out.println(rs3.getString("department") + " " + rs3.getString("grade"));
					down.add(rs3.getString("department") + "/" + rs3.getString("grade"));
				}
				vt_result[0]=up;
				vt_result[1]=safe;
				vt_result[2]=down;

				System.out.println(vt_result[0]);
				System.out.println(vt_result[1]);
				System.out.println(vt_result[2]);
				
				// while end
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버가 존재하지 않습니다." + e);
		} catch (Exception e) {
			System.out.println("해당 테이블이 존재하지 않습니다.");
		}
		return vt_result;
	}
}
