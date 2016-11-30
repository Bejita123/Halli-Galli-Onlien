package client;
import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.StringTokenizer;

public class ClientStart extends JFrame implements ActionListener, Runnable{

	String serverIp = "localhost"; // server Ip addr
	CardLayout card = new CardLayout();	// 창 전환을 위해 필요합니다!
	Loading load = new Loading();		// 로딩창
	Login login = new Login();			// 로그인창
	WaitRoom waitR = new WaitRoom();	// 대기실창
	GameWindow gameR = new GameWindow();// 게임창
	Join join = new Join();				// 회원가입창
	
	MakeRoom makeR = new MakeRoom();	// 방만들기창
	
	String id;
    static Socket s;
    BufferedReader in;// 서버에서 값을 읽는다
    OutputStream out; // 서버로 요청값을 보낸다
    
    
	public ClientStart()
	{
		connection();
		setLayout(card);				// BorderLayout
		
		add("LOGIN",login);				//로그인창
		
		setSize(1000,800);				//윈도우창 크기 설정
		setLocation(170,50);			//창 위치
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
    		s=new Socket(serverIp, 1111);		// s=>server
    		in=new BufferedReader(new InputStreamReader(s.getInputStream()));		//서버로 값을 읽어들임
			out=s.getOutputStream();												//서버로 값을 보냄
			/*out.write((Protocol.LOGIN+"|"+id+"|"
					+pass+"\n").getBytes());*/
    	}catch(Exception ex){}
    	
    	new Thread(this).start();	// run()으로 이동  // 서버로부터 응답값을 받아서 처리
    }


	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			String msg;
			try {
				msg = in.readLine();
				System.out.println("Server=>"+msg);
				StringTokenizer st=new StringTokenizer(msg, "|");//  shift + \
				int protocol=Integer.parseInt(st.nextToken());
				switch(protocol){
				case Protocol.YOURTURN:				//0.자기차례일 때 카드뒤집기 버튼활성화
				{
					//gameR.cardOpen.setBorderPainted(false);     	
				//	gameR.cardOpen.setContentAreaFilled(false);
					//gameR.cardOpen.setEnabled(true);
				}
				break;
				case Protocol.DELROW: 		//1.게임종료한 client 정보 접속자 List 에서 삭제
				{
					int rowIndex=(Integer.parseInt(st.nextToken()));		//rowIndex=delIndex
					System.out.println("삭제 줄: "+rowIndex);
			//		waitR.model2.removeRow(rowIndex);							//접속자리스트에서 삭제
				}
				break;
				case Protocol.CLIENTEXIT:	//2.waitRoom 채팅방에 00님이 나가셨습니다 전송
				{
					waitR.chat.append(st.nextToken()+"\n");
				//	waitR.bar.setValue(waitR.bar.getMaximum());
				}
				 break;
				  case Protocol.MYLOG:				//1.window타이틀에 사용자이름 업데이트
				  {
					  String id=st.nextToken();
					  setTitle(id);
					  card.show(getContentPane(), "WR");	//waitingroom으로 창 전환
					  
				  }
				  break;
				  
				  case Protocol.LOGIN:				//2.접속자테이블에 사용자 업데이트
				  {
					  String[] data={
						st.nextToken(),	 
						st.nextToken()
						
					  };
					//  waitR.model2.addRow(data);	
				  }
				  break;
				  
				  case Protocol.ROOMUSER:				//2.게임룸 유저테이블에 유저업데이트
				  {
					  System.out.println("In-ROOMUSER");
					  String[] data={
						st.nextToken()
					  };
					//  gameR.model1.addRow(data);	
				  }
				  break;
				  case Protocol.OUTUSER:
				  {
					  int rowIndex=(Integer.parseInt(st.nextToken()));		//rowIndex=delIndex
					  System.out.println("삭제 줄: "+rowIndex);
					//  gameR.model1.removeRow(rowIndex);
				  }
				  
				  case Protocol.WAITCHAT1:			//3.채팅할 때(waitroom)
				  {
					  waitR.chat.append(st.nextToken()+"\n");
					//  waitR.bar.setValue(waitR.bar.getMaximum());
				  }
				  break;
				  
				  case Protocol.ROOMCHAT:			//3.채팅할 때(gameWindow)
				  {
					  gameR.chat.append(st.nextToken()+"\n");
					//  gameR.setValue(gameR.bar.getMaximum());
					  validate();
				  }
				  break;
				  
				  case Protocol.NOTOVERLAP:			//4.ID가 중복되지 않을 때
				  {
					  JOptionPane.showMessageDialog(this,"ID가 중복되지 않습니다");
					//  join.ck=true;
					  join.pwdF.requestFocus();
				  }
				  break;
				  
				  case Protocol.OVERLAP:			//4.ID가 중복될 때
				  {
					  JOptionPane.showMessageDialog(this,
								"ID가 중복됩니다. 다시 입력하세요.");
					  //join.ck=false;
					  join.nameF.requestFocus();
				  }
				  break;
				  
				  case Protocol.MAKEROOM:			//5.client가 방만들기 확인 버튼을 눌렀을 때(게임창 전환)
				  {	
					  String roomId=st.nextToken();		//게임룸 만든 사람 	id
					  String roomName=st.nextToken();	//새로 만든 게임룸의 	이름
					  String humanNum=st.nextToken();		//현재인원수	//아직 안쓰임
					  String capaNum=st.nextToken();		//최대인원수	//아직 안쓰임
					  setTitle("방장_"+roomId+"    "+"방제_"+roomName);	
					//  gameR.b5.setEnabled(false); 	//시작버튼 비활성화
					  gameR.chat.setText("");
					  card.show(getContentPane(), "GW"); 		//게임창으로 전환
					  
				  }
				  break;

				  case Protocol.ROOMINFORM:			//5.client가 방만들기 확인 버튼을 눌렀을 때(waitRoom의 리스트에 방 추가)
				  {	
					  String roomType=st.nextToken();	//공개비공개
					  String roomName=st.nextToken();	//게임룸의 이름
					  String nnum=st.nextToken();		//현재인원
					  String num=st.nextToken();		//최대인원
					  String pos=st.nextToken();		//방상태(게임대기중)
					  String[] data={roomType, roomName, nnum, num, pos};	
					 // waitR.model1.addRow(data);			//waitRoom의 리스트에 방 추가						  
					  waitR.repaint();
				  }
				  break;
				  
				  
				  case Protocol.JOINROOM:			//6.방에 들어가기 했을 때(인원 수에따라 입장 가능 여부)
				  {
					  String result=st.nextToken();
					  if(result.equals("TRUE"))
					  {
						  String roomMaker=st.nextToken();
						  String roomName=st.nextToken();
						  setTitle("방장_"+roomMaker+"    "+"방제_"+roomName);	
						//  gameR.b5.setEnabled(false); 	//시작버튼 비활성화
						  gameR.chatF.setText("");
						  card.show(getContentPane(), "GW");
						  //준비버튼 활성화
						  gameR.b4.setEnabled(true); 
						  validate();
					  }
					  else
					  {
						  JOptionPane.showMessageDialog(this,"방이 꽉찼습니다.");
					  }
				  }
				  break;
				  
				  case Protocol.ROOMREADY:			//6.준비버튼 눌렀을 때 버튼 비활성화
				  {
					  System.out.println("최종적으로 준비전달받음");
					  gameR.b4.setEnabled(false); 							//준비버튼비활성화
				  }
				  break;
				  
				  case Protocol.ROOMREADYBUTTON:			//7.모두준비했을 때 방장만 시작 활성화
				  {
					  System.out.println("방장의 권한으로 시작버튼 활성화");
					//  gameR.b5.setEnabled(true); 							//준비버튼비활성화
					  
				  }
				  break;
//				  case Protocol.GAMESTART:			//7.모두준비했을 때 방장만 시작 활성화
//				  {
//					  System.out.println("방장의 권한으로 시작버튼 활성화");
//					  gw.cardOpen.setBorderPainted(false);      
//						gw.cardOpen.setContentAreaFilled(false);
//						gw.cardOpen.setEnabled(false);
//					  
//				  }
//				  break;
				  /*[방인원변경 ] ->*/
				  case Protocol.CHGROOMUSER:
				  {
					  //대기실 방 List table 의 특정 Row 의 방인원이 변경됨
					  int row=Integer.parseInt(st.nextToken());
					  String userNum=st.nextToken();
				//	  waitR.model1.setValueAt(userNum, row, 2);
					  waitR.repaint();
				  }
				  break;

				  /*[유저상태변경] ->*/
				  case Protocol.CHGUSERPOS:
				  {
					  int row=Integer.parseInt(st.nextToken());	//방번호
					  System.out.println("\\\\\\--->"+row);
					  String pos=st.nextToken();		//현재인원수
					//  waitR.model2.setValueAt(pos, row, 1);
					  waitR.repaint();
				  }
				  break;
				  
				  /*[방상태변경 ] ->*/
				  case Protocol.CHGROOMSTATE:
				  {
					  //대기실 방 List table 의 특정 Row 의 방인원이 변경됨
					  int row=Integer.parseInt(st.nextToken());		//방번호
					  String roomState=st.nextToken();				//방상태
					//  waitR.model1.setValueAt(roomState, row, 4);
					  waitR.repaint();
				  }
				  break;
				  
				  /*[방나가기] ->*/
				  case Protocol.DELROOM: //방에 사용자가 없에 방삭제 메시지 받음
				  {
					  gameR.chatF.setText("");
					  int roomRow=Integer.parseInt(st.nextToken());
					  System.out.println(roomRow+"방 삭제");
					//  waitR.model1.removeRow(roomRow);
					  waitR.repaint();
				  }
				  break;
				  case Protocol.REPAINT:
				  {
					  String tmpName=st.nextToken();
					  int b=Integer.parseInt(st.nextToken());
					  System.out.println("InREPAIT-ID:"+tmpName+"Number:"+b);
				//	  gameR.UpdateDraw(tmpName, b);						  

				  }
				  break;
				  case Protocol.CARDNUM:
				  {
					  String tmpName=st.nextToken();			//id
					  int b=Integer.parseInt(st.nextToken());	//카드수
					  System.out.println("InCARDNUM-ID:"+tmpName+"Number:"+b);
				//	  gameR.UpdateCardNum(tmpName, b);
				  }
				  break;
				  case Protocol.DEAD:
				  {
					  gameR.chat.append("당신은 죽었습니다.\n");
					//  gameR.bell.setEnabled(false);
					//  gameR.cardOpen.setEnabled(false);
				  }
				  break;
				  case Protocol.UPDATEDEAD:
				  {
					  String tmpName=st.nextToken();
					  gameR.chat.append(tmpName+" 님이 죽었습니다.\n");
					//  gameR.UpdateDead(tmpName);
					  validate();
				  }
				  break;
				  case Protocol.BELLSUCCESS:
				  {
					  String tmpName=st.nextToken();
					  gameR.chat.append(tmpName+" 님이 종치기 성공했습니다.\n");
				//	  gameR.bell.setEnabled(true);
					//  gameR.CardInit();
				  }
				  break;
				  
				  case Protocol.BELLFAIL:
	                 {
	                    String tmpName=st.nextToken();
	                    gameR.chat.append(tmpName+"님이 종치기 실패하였습니다.\n");
	                 //   gameR.bell.setEnabled(true);
	                    validate();
	                 }
	              break;
	             
				  case Protocol.BELL:
				  {
					//  gameR.bell.setEnabled(false);
				  }
				  break;
				  
				  case Protocol.TURNINFO:
				  {
					//  gameR.userName[0]=st.nextToken();
					//  gameR.userName[1]=st.nextToken();
					//  gameR.userName[2]=st.nextToken();
					//  gameR.userName[3]=st.nextToken();
				  }
				  break;
				  case Protocol.EXITFALSE:			//게임시작시 나가기비활성화
				  {
				//	  gameR.b6.setEnabled(false);
				  }
				  break;
				  
				  case Protocol.IDLABEL:			//게임시작시 id라벨 입력
				  {
					  String ID=st.nextToken();	//id
					  for(int i=0; i<4; i++){
					//	  if(ID.equals(gameR.userName[i])){
					//		  gameR.laPlayer[i].setText("Player"+(i+1)+": "+ID);
						  }
					  }
				  }
				  break;/* -> 추후 수정
				  case Protocol.GAMEEXIT:
				  {
					  System.out.println("zzzzz");
					  String tmpId=st.nextToken();
					  String tmpMsg=st.nextToken();
					  gameR.chat.append("게임종료=====>"+tmpId+tmpMsg);
					  gameR.b4.setEnabled(true);
					  gameR.b6.setEnabled(true);
					  gameR.CardInit();
				  }
				  break;
				}*/
			}catch(Exception ex){validate();}
			validate();
		}
	}

}
