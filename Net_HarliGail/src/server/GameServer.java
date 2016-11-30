package server;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.StringTokenizer;


public class GameServer implements Runnable {
	
	Socket  s;
	ServerSocket server;
	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		
		 GameServer server=new GameServer();		        // 서버 가동
		 
		new Thread(server).start();
	}
	public GameServer() throws IOException, SQLException{
		try
		{ 
			InetAddress addr = InetAddress.getByName("localhost");
       
			server=new ServerSocket(1111, 100 ,addr);		// port number , max_server connection, IP address	
			System.out.println("소켓"+s+"에 연결됨");
			System.out.println("NEW Server Start...");
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
		}
		
		
		/*
		InputStream input=s.getInputStream();
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
		s.close();
		server.close();
		
		DB test=new DB(fileName,"C:/test");
		test.runDB();
		*/
		
	}
	public void run()							// 1. 접속을 처리
    {
    	while(true){
	    	try{
				s=server.accept();
	    		ClientThread ct=new ClientThread(s);
	    		ct.start();					// 통신 시작 	
	    	}catch(Exception ex){}
    	}	
    }

}
class ClientThread extends Thread
{
	String id,posUser;		//id,이름,성별,상태
	int myIndex; //0부터 시작 첫번째 유저는 0번...
	Socket s;
	BufferedReader in;	// client요청값을 읽어온다
	OutputStream out;	//client로 결과값을 응답할때 
	int myRoomIndex=-1;		//client가 있는 방 번호, 0부터 시작
	
	
	public ClientThread(Socket s)
	{
		try{
			this.s=s;			//각 클라이언트의 소켓 장착
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=s.getOutputStream();
			System.out.println("connect by user");
		}catch(Exception ex){}
	}
	
	public void run()			//2.client와 server간의 통신을 처리  //Client의 요청을 받음
	{
		while(true)			
		{
			
			try
			{
				String msg=in.readLine();
				StringTokenizer st=new StringTokenizer(msg, "|");
				int protocol=Integer.parseInt(st.nextToken());
				switch(protocol)
				{
				case Protocol.SENDINFORMATION:{
					
					String id=st.nextToken();
					String pwd=st.nextToken();
					String fileName=st.nextToken();
					File f = new File("C:/profile", fileName);

					FileOutputStream out = new FileOutputStream(f);

					int i = 0;
					while ((i = in.read()) != -1) {
						out.write((char) i);
					}

					System.out.println("받은 파일 C:/profile 경로에 저장됨!");
					
					DB insertDB = new DB(id,pwd,fileName);					
				}
					
				}
			}catch(Exception ex)
			{
				/*접속되어있던 Client 접속 종료시*/
				interrupt();
			}
		}
	}
}
