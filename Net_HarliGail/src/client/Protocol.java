package client;

public class Protocol {
	/**/
	public static final int LOGIN=100;		//WaitRoom의 접속자 리스트에 업데이트
	public static final int MYLOG=110;		//로그인 후 WaitRoom으로 창 생성
	//public static final int
	
	//게임관련
	
	/* 회원가입 */
	//ID 중복 체크
	public static final int IDCHECK=200;
	public static final int NOTOVERLAP=201;
	public static final int OVERLAP=202;
	public static final int SUCCESSJOIN=203;
	public static final int SENDINFORMATION=204;//ID와 PWD와 프로필 사진 보냄
	//ID 중복
	//ID 중복 X
	//회원가입 완료
	
	/*대기실*/
	//방관련
	public static final int ROOMREADY=300;			//방에서 준비버튼 눌렀을 때
	public static final int ROOMREADYBUTTON=301;	//모두가 준비눌렀을 때
	public static final int ROOMSTART=302;			//시작버튼 눌렀을 때
	
	//방만들기
	public static final int MAKEROOM=400;		//방만들기 확인버튼 눌렀을 때//400|1|1|2명|게임룸
	public static final int ROOMINFORM=401;		//WaitRoom방목록에 새로 생성된 방 업데이트//401|1|2명|게임대기중
	public static final int JOINROOM=402;		//방에 들어 갈 때
	public static final int ROOMCHAT=403;		//게임룸에서 채팅할 때

	/*[방인원변경] 된것 출력하기 위한 Protocol*/
	public static final int CHGROOMUSER=404; 	//방에 들어와있는 사람수가 변경됨.
	public static final int CHGUSERPOS=405; 	//[유저상태변경] 유저가 방만들거나 입장으로 상태 변경시 접속된 나머지 유저에 메시지 전달
	public static final int EXITROOM=406;		//[방나가기] 게임룸에 있던 유저가 게임룸을 나갈때
	public static final int DELROOM=407;		//[방나가기] 방에 있는 사람이 없을때 방 삭제위해
	public static final int CHGROOMSTATE=408;	//방상태 변경
	
	public static final int ROOMUSER=410;	//게임방 유저리스트 업데이트
	public static final int OUTUSER=411;    //게임방 유저리스트 삭제
	
	//채팅관련
	public static final int WAITCHAT1=500;
	public static final int WAITCHAT2=501;	
	/*게임*/
	public static final int BELL=600; 		//클라이언트 벨 눌렀을때
	public static final int CARDOPEN=601; 	//클라이언트 카드 뒤집었을때
	public static final int BELLFAIL=602; 	//클라이언트 벨누름 실패
	public static final int BELLSUCCESS=603;//클라이언트 벨누름 성공
	public static final int YOURTURN=604; 	//차례 알려줌 (클라이언트에서 받았을때 뒤집기 활성)
	public static final int CARDNUM=605;
	public static final int TURNINFO=606;
	public static final int UPDATEDEAD=607;
	public static final int GAMEEXIT=608;
	public static final int REPAINT=609;
	public static final int DEAD=610;
	public static final int GAMESTART=611;
	public static final int EXITFALSE=612;		//게임시작시 방나가기 비활성화
	public static final int IDLABEL=613;		//게임시작시 id라벨 입력
	// Client 종료 시
	public static final int CLIENTEXIT=900;
	public static final int DELROW=901;}
