package server;

import java.sql.*;
import java.util.Scanner;

public class DB {

	public static String fileName;
	public static String path;
	public static String id,pwd;
	
	public DB(String id,String pwd,String fileName) throws SQLException {
		this.id=id;
		this.pwd=pwd;
		this.fileName=fileName;
		
		runDB();
	}

	public static void runDB() throws SQLException {
		
		Connection con=null;//db접속
		PreparedStatement stmt1=null;
		Statement stmt2=null;//질의문 실행
		PreparedStatement stmt3=null;
		PreparedStatement stmt4=null;
		ResultSet result=null;//결과 클래스
		
		//먼저 mysql열어서 .sql파일 열기

		
		try{
			Class.forName("com.mysql.jdbc.Driver");//mysql driver 부름
			con=DriverManager.getConnection("jdbc:mysql://localhost/HalliGalli","root","12345");
			//커넥션 정보 넘기며 커넥션 얻음
			
					
			stmt2=con.createStatement();		
			String query="INSERT INTO CLIENT(ID, PASSWORD, FILENAME) VALUES('"+id+"', '"+pwd+"', '"+fileName+"')";
			stmt2.executeUpdate(query);//쿼리 실행 (update,insert,delete)
			
			
			stmt3=con.prepareStatement("SELECT ID,PASSWORD, FILENAME FROM CLIENT");
			result=stmt3.executeQuery();//쿼리 실행(select)
			
			
			//이미지 파일 클라이언트한테 다운받아와서 이미지 파일 경로 DB에 저장하기
			while(result.next()){
				String g_id=result.getString("ID");//테이블에서 값 받아오기
				int g_pwd=result.getInt("PASSWORD");	
				String g_fileName=result.getString("FILENAME");
				System.out.println("ID: "+g_id+" password: "+g_pwd+" FILENAME: "+g_fileName);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(stmt1!=null)
				stmt1.close();
			if(con!=null)
				con.close();
			if(stmt2!=null)
				stmt2.close();
			if(result!=null)
				result.close();
		}		
		
		
	}


}

