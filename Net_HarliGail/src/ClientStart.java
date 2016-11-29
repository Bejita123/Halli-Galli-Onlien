import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ClientStart extends JFrame implements ActionListener, Runnable{

	CardLayout card = new CardLayout();	// 창 전환을 위해 필요합니다!
	Loading load = new Loading();		// 로딩창
	Login login = new Login();			// 로그인창
	WaitRoom waitR = new WaitRoom();	// 대기실창
	GameWindow gameR = new GameWindow();// 게임창
	Join join = new Join();				// 회원가입창
	MakeRoom makeR = new MakeRoom();	// 방만들기창
	
	String id;
    Socket s;
    BufferedReader in;// 서버에서 값을 읽는다
    OutputStream out; // 서버로 요청값을 보낸다
	public ClientStart()
	{
		setLayout(card);				// BorderLayout
		
		add("LOGIN",login);				//로그인창
		
		setSize(800,600);				//윈도우창 크기 설정
		setLocation(270,170);			//창 위치
		setVisible(true);				//보여지게
		setResizable(false);			//윈도우 창 고정(늘릴 수 없음)
		
		add("WR",waitR);			//대기실창
		
		login.btJoin.addActionListener(this);//회원가입버튼 누르면
		login.btLogin.addActionListener(this);//로그인 버튼 누르면
		
		waitR.b1.addActionListener(this);//채팅 전송
		waitR.b2.addActionListener(this);// 도움말 버튼
		waitR.b3.addActionListener(this);//방만들기 창 팝업
		waitR.chatF.addActionListener(this);//사용자가 입력하면
		makeR.b1.addActionListener(this);//방만들기 확인
		gameR.b4.addActionListener(this);//게임방에서 나가기
		
		
		add("GW",gameR);	//게임 윈도우창
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login.btJoin)
		{
			join.setBounds(470,310,340,420);
			join.setVisible(true);
		}
		else if(e.getSource()==login.btLogin){
			//대기실 창으로 전환
			card.show(getContentPane(),"WR");
		}
		else if(e.getSource()==waitR.chatF || e.getSource()==waitR.b1){
			//채팅창이거나 전송을 누르면..?
			String data = waitR.chatF.getText();
			waitR.chat.append(data+"\n");
			waitR.chatF.setText("");
		}
		else if(e.getSource()==waitR.b3){
			//방만들기버튼을 누르면
			makeR.setBounds(500,300,260,290);
			makeR.setVisible(true);
		}
		else if(e.getSource()==makeR.b1){
			//게임창으로 전환
			makeR.dispose();
			card.show(getContentPane(),"GW");
		}
		else if(e.getSource()==gameR.b4)
		{
			card.show(getContentPane(), "WR");
		}
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClientStart start = new ClientStart();
	}
	
	public void connection()
    {
    	try
    	{
    		s=new Socket("localhost", 65535);		// s=>server
    		in=new BufferedReader(new InputStreamReader(s.getInputStream()));		//서버로 값을 읽어들임
			out=s.getOutputStream();												//서버로 값을 보냄
			/*out.write((Function.LOGIN+"|"+id+"|"
					+pass+"\n").getBytes());*/
    	}catch(Exception ex){}
    	
    	new Thread(this).start();	// run()으로 이동  // 서버로부터 응답값을 받아서 처리
    }


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
