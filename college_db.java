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

public class college_db {
   //벡터 사용이유 : 벡터는 값을 넣을때마다 자기 스스로 유동적으로 계속 늘어나기 때문 관리하기 편함
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
         String Query1 = "select * from "  + college + " where grade between "+(result+1.1)+" AND " + (result+3.0) + " order by department";
         String Query2 = "select * from "  + college + " where grade between "+(result-1.0)+" AND " + (result+1.0) + " order by department";
         String Query3 = "select * from "  + college + " where grade between "+(result-10.0)+" AND " + (result-1.1) + " order by department";
         
         //ResultSet이란 것을 이용하여 쿼리 전달하여 받아온값을 rs1에 저장 
         ResultSet rs1 = stmt.executeQuery(Query1);
         ResultSet rs2 = stmt2.executeQuery(Query2);
         ResultSet rs3 = stmt3.executeQuery(Query3);
         
         // beforeFirst() : rs의 위치를 시작위치로 이동
         rs1.beforeFirst();
         rs2.beforeFirst();
         rs3.beforeFirst();
         
         // next() : rs의 위치를 다음위치로 이동 자료의 끝이면 false로 만둠
            while (rs1.next()) {
               //쿼리문을 하여 뽑아 온 것을 벡터안에 넣어준다.
               up.add(rs1.getString("department") + "/" + rs1.getString("grade"));
            }
            while (rs2.next()) {
               safe.add(rs2.getString("department") + "/" + rs2.getString("grade"));
            }
            while (rs3.next()){
               down.add(rs3.getString("department") + "/" + rs3.getString("grade"));
            }
            //벡터에 한번씩 넣은 값들을 모두 리턴시키기 위하여 벡터를 배열로 만들어 그 속에 쿼리결과값을 넣은 벡터들을
            //넣었음
            vt_result[0]=up;
            vt_result[1]=safe;
            vt_result[2]=down;
            
            // while end
      } catch (ClassNotFoundException e) {
         System.out.println("드라이버가 존재하지 않습니다." + e);
      } catch (Exception e) {
         System.out.println("해당 테이블이 존재하지 않습니다.");
      }
      //값들 모두 리턴
      return vt_result;
   }
}