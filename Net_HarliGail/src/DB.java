
import java.sql.*;
import java.util.Scanner;

public class DB {

	public static String fileName;
	public static String path;
	
	public DB(String fileName, String path) {
		this.fileName=fileName;
		this.path=path;
	}

	public static void runDB() throws SQLException {
		
		Connection con=null;//db접속
		PreparedStatement stmt1=null;
		Statement stmt2=null;//질의문 실행
		PreparedStatement stmt3=null;
		PreparedStatement stmt4=null;
		ResultSet result=null;//결과 클래스
		String get_id="Ji-young",get_pwd="1111";
		
		//먼저 mysql열어서 .sql파일 열기

		
		try{
			Class.forName("com.mysql.jdbc.Driver");//mysql driver 부름
			con=DriverManager.getConnection("jdbc:mysql://localhost/test","root","12345");
			//커넥션 정보 넘기며 커넥션 얻음
			
					
			stmt2=con.createStatement();		
			String query="INSERT INTO CLIENT(ID, PASSWORD, FILENAME, FILEPATH) VALUES('"+get_id+"', '"+get_pwd+"', '"+fileName+"', '"+path+"')";
			stmt2.executeUpdate(query);//쿼리 실행 (update,insert,delete)
			
			
			stmt3=con.prepareStatement("SELECT FILENAME, FILEPATH FROM CLIENT");
			result=stmt3.executeQuery();//쿼리 실행(select)
			
			
			//이미지 파일 클라이언트한테 다운받아와서 이미지 파일 경로 DB에 저장하기
			while(result.next()){
				String psd=result.getString("FILENAME");//테이블에서 값 받아오기
				String id=result.getString("FILEPATH");				
				System.out.println("filename: "+psd+" filepath: "+id);
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

