
import java.net.*;
import java.sql.SQLException;
import java.io.*;

public class GetProfile {

	public static void main(String[] args) throws IOException, SQLException {
		
		GameServer getImage=new GameServer();//서버 소켓 가져옴
		// soket 수정해야됨
		

		
		InputStream input=getImage.s.getInputStream();
		BufferedReader bf=new BufferedReader(new InputStreamReader(input));
		
		String fileName=bf.readLine();
		File f=new File("C:/test",fileName);
		
		FileOutputStream out=new FileOutputStream(f);
		
		int i=0;
		while((i=input.read())!=-1){
			out.write((char)i);
		}
		
		System.out.println("받은 파일 C:/test 경로에 저장됨!");
		
		bf.close();
		input.close();
		out.close();
		
		DB test=new DB(fileName,"C:/test");
		test.runDB();
	}

}
