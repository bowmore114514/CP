package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import model.Event;
public class EventDAO {

	//----------初期情報----------
	private final String HOST ="localhost";//ホスト名
	private final String PORT ="5432";//ポート番号
	private final String DBNAME ="CarolinaReaper";//データベース名
	private String ROLENAME = "postgres";//ロール名
	private final String PASSWORD = "0000";//パスワード
	private final String URL = "jdbc:postgresql://" +HOST+":"+ PORT + "/" + DBNAME;//サーバーURL

	//-------------SELECT文----------------
	String selectEvent2 = "SELECT EVENTID, EVENTNAME, ORGANIZARNAME, REGISTDAY, "
						+ "AUTHERNAME, AUTHERPASS, DEADLINEDAY, DETERMINEDDAY, "
						+ "DETERMINEDFLAG, EVENTOPENFLAG, NUMBEROFEVENT, "
						+ "EVENTURL, EVENTPAGEFILENAME FROM EVENT";
	//----------------INSERT文----------
	String insertEvent2 = "INSERT INTO EVENT ( EVENTID, EVENTNAME, ORGANIZARNAME, REGISTDAY, "
			+ "AUTHERNAME, AUTHERPASS, DEADLINEDAY, DETERMINEDDAY, "
			+ "DETERMINEDFLAG, EVENTOPENFLAG, NUMBEROFEVENT, "
			+ "EVENTURL, EVENTPAGEFILENAME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	//--------------UPDATE文--------------
	String updateEvent2 = "UPDATE  EVENT SET , EVENTNAME, ORGANIZARNAME=?, REGISTDAY=?, "
			+ "AUTHERNAME=?, AUTHERPASS=?, DEADLINEDAY=?, DETERMINEDDAY=?, "
			+ "DETERMINEDFLAG=?, EVENTOPENFLAG=?, NUMBEROFEVENT=?, "
			+ "EVENTURL=?, EVENTPAGEFILENAME=? WHERE EVENTID =?";


	//---------------- Methods----------------------------------------------------------------------------
	//----------------INSERT文生成----------
	public  static String insertEventVenue(int index){
		return "INSERT INTO eventvenuetb"+ String.valueOf(index) +" (EVENTID , EVENTVENUE) VALUES(?,?)" ;
	}

	public static String insertAutherRemark (int index ){
		return "INSERT INTO autherremarktb"+ String.valueOf(index) +  "(EVENTID, AUTHERREMARK) VALUES(?,?)"  ;
	}

	public static String insertPricePerPerson (int index){
		return "INSERT INTO priceperpersontb"+ String.valueOf(index) +"(EVENTID , PRICEPERPERSON) VALUES(?,?,)"  ;
	}

	public static String insertCandidate(int index){
		return "INSERT INTO candidatetb"+ String.valueOf(index) +"(EVENTID , CANDIDATE) VALUES(?,?)" ;
	}
	public static String insertBordItems(int index){
		return  "INSERT INTO borditems"+String.valueOf(index)+ "(EVENTID ,USERNAME , USERPASS , USERREMARK ,USERREGISTDAY) VALUES(?,?,?,?,?)" ;
	}

	public static String insertPreferredFlag(int index,int itemIndex){
		return "INSERT INTO preferredflagtb"+
				String.valueOf(index) + "t"+
				String.valueOf(itemIndex) +" (EVENTID ,ITEMID ,PREFERREDFLAG ) VALUES(?,?,?)" ;
	}

	//----------------UPDATE文生成--------------------------------------------------------------------------------------
	public  static String updateEventVenue(int index){
		return "UPDATE  eventvenuetb"+ String.valueOf(index)
		+" SET EVENTVENUE=?  WHERE EVENTID =? AND EVENTVENUEID=?" ;
	}

	public static String updateAutherRemark (int index ){
		return "UPDATE  autherremarktb"+ String.valueOf(index)
		+  "SET AUTHERREMARK=? WHERE EVENTID =? AND AUTHERREMARKID=?"  ;
	}

	public static String updatePricePerPerson (int index){
		return "UPDATE  priceperpersontb"+ String.valueOf(index)
		+"SET PRICEPERPERSON=? WHERE EVENTID =?  AND PRICEPERPERSONID=?"  ;
	}

	public static String updateCandidate(int index){
		return "UPDATE  candidatetb"+ String.valueOf(index)
		+"SET CANDIDATE=? WHERE EVENTID =? AND CANDIDATEID=?" ;
	}
	public static String updateBordItems(int index){
		return  "UPDATE  borditems"+String.valueOf(index)
		+ "SET  , USERNAME=? , USERPASS=? , USERREMARK=? ,USERREGISTDAY=? WHERE EVENTID =? AND ITEMID=?" ;
	}

	public static String updatePreferredFlag(int index,int itemIndex){
		return "UPDATE  preferredflagtb"+
				String.valueOf(index) + "t"+
				String.valueOf(itemIndex)
				+" SET ,PREFERREDFLAG=?  WHERE EVENTID =? AND ITEMID=? AND PREFERREDFLAGID=?" ;
	}

	//----------------SELECT文生成----------
		public  static String selectEventVenue(int index){
			return "SELECT EVENTID , EVENTVENUE, EVENTVENUEID FROM eventvenuetb"+ String.valueOf(index) ;
		}

		public static String selectAutherRemark (int index ){
			return "SELECT EVENTID , AUTHERREMARK, AUTHERREMARKID FROM autherremarktb"+ String.valueOf(index) ;
		}

		public static String selectPricePerPerson (int index){
			return "SELECT EVENTID , PRICEPERPERSON, PRICEPERPERSONID FROM priceperpersontb"+ String.valueOf(index) ;
		}

		public static String selectCandidate(int index){
			return "SELECT EVENTID , CANDIDATE, CANDIDATEID FROM candidatetb"+ String.valueOf(index) ;
		}
		public static String selectBordItems(int index){
			return  "SELECT EVENTID ,ITEMID ,USERNAME , USERPASS , USERREMARK ,USERREGISTDAY FROM borditems"+String.valueOf(index);
		}

		public static String selectPreferredFlag(int index,int itemIndex){
			return "SELECT EVENTID ,ITEMID ,PREFERREDFLAG ,PREFERREDFLAGID FROM preferredflagtb"+
					String.valueOf(index) + "t"+String.valueOf(itemIndex);
		}

	//------------CREATE TABLE文生成----------

	public  static String createEventVenue(int index){
		return "CREATE TABLE eventvenuetb" + String.valueOf(index)+" ( EVENTID integer, EVENTVENUE text, EVENTVENUEID serial)";
	}

	public static String createAutherRemark (int index ){
		 return "CREATE TABLE autherremarktb" + String.valueOf(index)+" (EVENTID integer, AUTHERREMARK text, AUTHERREMARKID serial)";
	}

	public static String createPricePerPerson (int index){
		return "CREATE TABLE priceperpersontb" + String.valueOf(index)+" (EVENTID integer, PRICEPERPERSONID integer , PRICEPERPERSONID serial)";
	}

	public static String createCandidate(int index){
		return "CREATE TABLE candidatetb" + String.valueOf(index)+" (EVENTID integer , CANDIDATE text, CANDIDATEID serial)";
	}

	public static String createBordItems(int index){
		return  "CREATE TABLE borditems" + String.valueOf(index)+" ( EVENTID integer ,ITEMID serial , USERNAME text , USERPASS text, USERREMARK text , USERREGISTDAY text)";
	}

	public static String createPreferredFlag(int index, int iid){
		return "CREATE TABLE preferredflagtb" + String.valueOf(index) + "t"+String.valueOf(iid)
		+"( EVENTID integer ,ITEMID integer ,PREFERREDFLAG integer ,PREFERREDFLAGID serial)";
	}

	//--------------DELETE文生成---------------
	public static String deleteEvent(int eid){
		return "DELETE FROM event WHERE id="+String.valueOf(eid);
	}

	public static String deleteAutherRemark(int eid,int id){
		return "DELETE FROM autherremarktb"+String.valueOf(eid)+" WHERE id="+String.valueOf(id);
	}

	public static String deleteEventVenue(int eid,int id){
		return "DELETE FROM eventvenuetb"+String.valueOf(eid)+" WHERE id="+String.valueOf(id);
	}

	public static String deletePricePerPerson(int eid,int id){
		return "DELETE FROM priceperpersontb"+String.valueOf(eid)+" WHERE id="+String.valueOf(id);
	}

	public static String deleteCandidate(int eid,int id){
		return "DELETE FROM candidatetb"+String.valueOf(eid)+" WHERE id="+String.valueOf(id);
	}

	public static String deleteBordItems(int eid,int iid){
		return "DELETE FROM borditems"+String.valueOf(eid)+" WHERE id="+String.valueOf(iid);
	}
	public static String deletePreferredFlag(int eid,int iid,int id){
		return "DELETE FROM preferredflagtbpreferredflagtb" + String.valueOf(eid) +
				"t"+String.valueOf(iid)
				+"WHERE id = "+String.valueOf(id);
	}

	//-------------------DROP TABLE文生成-----------------------------
	public static String dropAutherRemark(int eid){
		return "DROP TABLE autherremarktb"+String.valueOf(eid);
	}

	public static String dropEventVenue(int eid){
		return " DROP TABLE eventvenuetb"+String.valueOf(eid);
	}

	public static String dropPricePerPerson(int eid){
		return "DROP TABLE priceperpersontb"+String.valueOf(eid);
	}

	public static String dropCandidate(int eid){
		return "DROP TABLE candidatetb"+String.valueOf(eid);
	}

	public static String dropBordItems(int eid){
		return "DROP TABLE borditems"+String.valueOf(eid);
	}
	public static String dropPreferredFlag(int eid,int iid){
		return "DROP TABLE preferredflagtb" + String.valueOf(eid) + "t"+String.valueOf(iid);
	}


	public static Calendar getCalendarByStr(String strCal){//フォーマットされた日時のStrをCalendarに変換
		Calendar cal = Calendar.getInstance();
		int yyyy = Integer.parseInt(strCal.substring(0,4));
		int mM = Integer.parseInt(strCal.substring(4,6));
		int dd = Integer.parseInt(strCal.substring(6,8));
		int hh = Integer.parseInt(strCal.substring(8,10));
		int mm = Integer.parseInt(strCal.substring(10,12));
		int ss = Integer.parseInt(strCal.substring(12,14));
		Event.setYear(cal, yyyy);
		Event.setMonth(cal, mM);
		Event.setDate(cal, dd);
		Event.setHour(cal, hh);
		Event.setMinute(cal, mm);
		Event.setSecond(cal, ss);
		return cal;
	}
	public static String getStrByCalendar(Calendar cal){//CalendarクラスをフォーマットしてStrに変換
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(cal.getTime());
	}
	//-------------------------
	public  ArrayList <String> getEventVenueList(int eid){//eventIDを入力すると、そのeventIDのeventVenueを返す
		ArrayList<String> eventVenueList = new ArrayList<String>();//取り出したデータの格納先
		//----------接続パラメータ設定----------
			Connection conn = null;
			PreparedStatement  pStmt = null; // SQL文オブジェクト
		    ResultSet  resultSet = null; // 問合せ結果オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//SELECT文準備
			 pStmt = conn.prepareStatement(selectEventVenue(eid));
			//----------SQL文実行----------
			 resultSet = pStmt.executeQuery();
			while(resultSet.next()){
				int eventId = resultSet.getInt("EVENTID");
				String eventVenue = resultSet.getString("EVENTVENUE");
				if(eventId == eid){
					eventVenueList.add(eventVenue);
				}
			}




		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return eventVenueList;//------returnしたいものを書け------

	}


	public  ArrayList <String> getAutherRemarkList(int eid){//eventIDを入力すると、そのeventIDのAutherRemarkを返す
		ArrayList<String> autherRemarkList = new ArrayList<String>();//取り出したデータの格納先
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
	    ResultSet  resultSet = null; // 問合せ結果オブジェクト

		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);




			//SELECT文準備
			pStmt = conn.prepareStatement(selectAutherRemark(eid));
			//----------SQL文実行----------
			 resultSet = pStmt.executeQuery();
			while(resultSet.next()){
				int eventId = resultSet.getInt("EVENTID");
				String autherRemark = resultSet.getString("AUTHERREMARK");
				if(eventId == eid){
					autherRemarkList.add(autherRemark);
				}
			}




		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return autherRemarkList;//------returnしたいものを書け------

	}



	public  ArrayList <String> getPricePerPersonList(int index){//indexにeventIDを入力すると、そのeventIDのPricePerPersonを返す
		ArrayList<String> pricePerPersonList = new ArrayList<String>();//取り出したデータの格納先
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
	    ResultSet  resultSet = null; // 問合せ結果オブジェクト

		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);




			//SELECT文準備
			pStmt = conn.prepareStatement(selectPricePerPerson(index));
			//----------SQL文実行----------
			 resultSet = pStmt.executeQuery();
			while(resultSet.next()){
				int eventId = resultSet.getInt("EVENTID");
				int pricePerPerson = resultSet.getInt("PRICEPERPERSON");
				if(eventId == index){
					pricePerPersonList.add(String.valueOf(pricePerPerson));
				}
			}




		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return pricePerPersonList;//------returnしたいものを書け------

	}



	public  ArrayList <Calendar> getCandidateList(int index){//indexにeventIDを入力すると、そのeventIDのCandidateを返す
		ArrayList<Calendar> eventCandidate = new ArrayList<Calendar>();//取り出したデータの格納先
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
	    ResultSet  resultSet = null; // 問合せ結果オブジェクト

		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//SELECT文準備
			pStmt = conn.prepareStatement(selectCandidate(index));
			//----------SQL文実行----------
			 resultSet = pStmt.executeQuery();
			while(resultSet.next()){
				int eventId = resultSet.getInt("EVENTID");
				Calendar candidate = getCalendarByStr( resultSet.getString("CANDIDATE"));
				if(eventId == index){
					eventCandidate.add(candidate);
				}
			}




		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return eventCandidate;//------returnしたいものを書け------

	}


	public  ArrayList <BordItems2> getBordItemList(int index){//indexにeventIDを入力すると、そのeventIDのPricePerPersonを返す
		ArrayList<BordItems2> bordItemList = new ArrayList<BordItems2>();//取り出したデータの格納先
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
	    ResultSet  resultSet = null; // 問合せ結果オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);




			//SELECT文準備
			pStmt = conn.prepareStatement(selectBordItems(index));

			//----------SQL文実行----------
			 resultSet = pStmt.executeQuery();


			while(resultSet.next()){
				int eventId = resultSet.getInt("EVENTID");
				int itemId = resultSet.getInt("ITEMID");
				String userName = resultSet.getString("USERNAME");
				String userPass = resultSet.getString("USERPASS");
				String userRemark = resultSet.getString("USERREMARK");
				Calendar userRegistDay = getCalendarByStr(resultSet.getString("USERREGISTDAY"));

				if(eventId == index){
					BordItems2 bordItems = new BordItems2(eventId,itemId,userName,userPass,userRemark,userRegistDay);
				bordItemList.add(bordItems);
				}
			}

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return bordItemList;//------returnしたいものを書け------

	}



	public  ArrayList<Integer> getPreferredFlagSet(int index ,int itemIndex){//indexにeventIDを入力すると、そのeventIDのCandidateを返す
		ArrayList<Integer> preferredFlagList = new ArrayList<Integer>();//取り出したデータの格納先
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
	    ResultSet  resultSet = null; // 問合せ結果オブジェクト


		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//SELECT文準備
			pStmt = conn.prepareStatement(selectPreferredFlag(index,itemIndex));
			//----------SQL文実行----------
			 resultSet = pStmt.executeQuery();
			while(resultSet.next()){
				int eventId = resultSet.getInt("EVENTID");
				int itemId = resultSet.getInt("ITEMID");
				int preferredFlag = resultSet.getInt("PREFERREDFLAG");
				if(eventId == index && itemId == itemIndex){
					preferredFlagList.add(preferredFlag );
				}
			}




		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return preferredFlagList;//------returnしたいものを書け------

	}



	public  ArrayList <Event2> getEvent2List(){
		ArrayList<Event2> EventList2 = new ArrayList<Event2>();//取り出したデータの格納先
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
	    ResultSet  resultSet = null; // 問合せ結果オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);




			//SELECT文準備
			pStmt = conn.prepareStatement(selectEvent2);

			//----------SQL文実行----------
			 resultSet = pStmt.executeQuery();


			while(resultSet.next()){
				 /*EVENTID, EVENTNAME, ORGANIZARNAME, REGISTDAY, "
						+ "AUTHERNAME, AUTHERPASS, DEADLINEDAY, DETERMINEDDAY, "
						+ "DETERMINEDFLAG, EVENTOPENFLAG, NUMBEROFEVENT, "
						+ "EVENTURL, EVENTPAGEFILENAME FROM EVENT"*/
				String eventId = String.valueOf(resultSet.getInt("EVENTID"));
				String eventName = resultSet.getString("EVENTNAME");//イベントの名前
				String organizarName = resultSet.getString("ORGANIZARNAME");//幹事の名前
				Calendar  registDay = getCalendarByStr(resultSet.getString("REGISTDAY"));//投稿日時
				String autherName  = resultSet.getString("AUTHERNAME");//イベント製作者の名前
				String autherPass  = resultSet.getString("AUTHERPASS");//イベント製作者の編集用pass
				Calendar deadlineDay = getCalendarByStr(resultSet.getString("DEADLINEDAY"));//締め切り日時
				Calendar determinedDay = getCalendarByStr(resultSet.getString("DETERMINEDDAY"));//確定日時
				int determinedFlag = resultSet.getInt("DETERMINEDFLAG");//イベント確定のフラグ 1:確定,0:未確定
				int eventOpenFlag = resultSet.getInt("EVENTOPENFLAG");//イベントの公開フラグ.1:公開,0:非公開
				String numberOfEvent = String.valueOf(resultSet.getInt("NUMBEROFEVENT"));//イベント会数
				String eventUrl =  resultSet.getString("EVENTURL");//イベントページのURL
				String eventPageFileName="";//つかわない


				Event2 event2 = new Event2(eventId,eventName,organizarName,registDay,autherName,
						autherPass,deadlineDay, determinedDay, determinedFlag,
						eventOpenFlag, numberOfEvent,eventUrl,eventPageFileName);
				EventList2.add(event2);

			}

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return null;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return null;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return EventList2;//------returnしたいものを書け------

	}



	public   boolean insertEventVenueList(int eventId, ArrayList<String> eventVenueList){//indexにeventIDを入力すると、そのeventIDのeventVenueを返す
		//----------接続パラメータ設定----------
			Connection conn = null;
			PreparedStatement  pStmt = null; // SQL文オブジェクト
			int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//INSERT文準備
			 pStmt = conn.prepareStatement(updateEventVenue(eventId));
			//----------SQL文実行----------
			 //int j= eventVenueId;
			 for(String i:eventVenueList){
				  pStmt.setInt(1, eventId);
				  pStmt.setString(2,i);
				  //pStmt.setInt(3,j);
				  result *= pStmt.executeUpdate();
			 }




		//---------------------ここからエラー処理-----------------------
			 if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}


	public  boolean insertAutherRemarkList(int id, ArrayList<String> remark){//indexにeventIDを入力すると、そのeventIDのAutherRemarkを返す

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//INSERT文準備
			pStmt = conn.prepareStatement(insertAutherRemark(id));
			//----------SQL文実行----------
			for(String i:remark){
				pStmt.setInt(1, id);
				pStmt.setString(2,i);
				result *= pStmt.executeUpdate();
			}




			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}



	public  boolean insertPricePerPersonList  (int id, ArrayList<Integer> remark){//indexにeventIDを入力すると、そのeventIDのAutherRemarkを返す

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//SELECT文準備
			pStmt = conn.prepareStatement(insertPricePerPerson(id));
			//----------SQL文実行----------
			for(int i:remark){
				pStmt.setInt(1, id);
				pStmt.setInt(2,i);

				result *= pStmt.executeUpdate();
			}




			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}



	public  boolean insertCandidateList(int id, ArrayList<Calendar> cal){//indexにeventIDを入力すると、そのeventIDのAutherRemarkを返す

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//SELECT文準備
			pStmt = conn.prepareStatement(insertCandidate(id));
			//----------SQL文実行----------
			//int j= rid;
			for(Calendar i:cal){
				pStmt.setInt(1, id);
				pStmt.setString(2,getStrByCalendar(i));
				//pStmt.setInt(3,j);
				result *= pStmt.executeUpdate();
			}




			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}



	public  boolean insertBordItemList2(BordItems2 item2){   //indexにeventIDを入力すると、そのeventIDのAutherRemarkを返す

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//INSERT文準備
			pStmt = conn.prepareStatement(insertBordItems(item2.getItemId()));
			//----------SQL文実行----------
			pStmt.setInt(1, item2.getEventId());
			//pStmt.setInt(1,item2.getItemId());
				pStmt.setString(2, item2.getUserName());
				pStmt.setString(3, item2.getUserPass());
				pStmt.setString(4, item2.getUserRemark());
				pStmt.setString(5, getStrByCalendar(item2.getUserRegistDay()));
				result *= pStmt.executeUpdate();





			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}




	public boolean insertPreferredFlagSet(int eid,int itemId, ArrayList<Integer> flagSet){

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//INSERT文準備
			pStmt = conn.prepareStatement(insertPreferredFlag(eid,itemId));
			//----------SQL文実行----------
			for(int i :flagSet){
				pStmt.setInt(1, eid);
				pStmt.setInt(2,itemId);
				pStmt.setInt(3, i);
				result *= pStmt.executeUpdate();
			}





			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}



	public   int insertEvent2List(Event2 event2){
		int eventId =0;
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		ResultSet  resultSet = null;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//INSERT文準備
			pStmt = conn.prepareStatement(insertEvent2);
			//----------SQL文実行----------
			/*String insertEvent2 = "INSERT INTO EVENT ( EVENTID, EVENTNAME, ORGANIZARNAME, REGISTDAY, "
					+ "AUTHERNAME, AUTHERPASS, DEADLINEDAY, DETERMINEDDAY, "
					+ "DETERMINEDFLAG, EVENTOPENFLAG, NUMBEROFEVENT, "
					+ "EVENTURL, EVENTPAGEFILENAME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";*/

				//pStmt.setInt(1, Integer.parseInt(event2.getEventId()));
				pStmt.setString(1, event2.getEventName());
				pStmt.setString(2, event2.getOrganizarName());
				pStmt.setString(3, getStrByCalendar(event2.getRegistDay()));
				pStmt.setString(4, event2.getAutherName());
				pStmt.setString(5, event2.getAutherPass());
				pStmt.setString(6, getStrByCalendar(event2.getDeadlineDay()));
				pStmt.setString(7, getStrByCalendar(event2.getDeterminedDay()));
				pStmt.setInt(8, event2.getDeterminedFlag());
				pStmt.setInt(9, event2.getEventOpenFlag());
				pStmt.setInt(10, Integer.parseInt(event2.getNumberOfEvent()));
				pStmt.setString(11,event2.getEventUrl());
				pStmt.setString(12, event2.getEventPageFileName());
				result *= pStmt.executeUpdate();


				//--------------ID返却用
				String s = "SELECT MAX(EVENTID)  FROM event ";


				//SELECT文準備
				pStmt = conn.prepareStatement(s);
				//----------SQL文実行----------
				resultSet = pStmt.executeQuery();
				eventId = resultSet.getInt("EVENTID");

				//---------------------ここからエラー処理-----------------------
				if(result != 1) return 0;
		} catch (SQLException e){
			e.printStackTrace();
			return 0;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return 0;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return 0;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return eventId;

	}

//-----------------------------------UPDATE methd------------------------------------

	public   boolean updateEventVenueList(int eventId, String eventVenue,int eventVenueId){//indexにeventIDを入力すると、そのeventIDのeventVenueを返す
		//----------接続パラメータ設定----------
			Connection conn = null;
			PreparedStatement  pStmt = null; // SQL文オブジェクト
			int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//UPDATE文準備
			 pStmt = conn.prepareStatement(updateEventVenue(eventId));
			//----------SQL文実行----------

				  pStmt.setInt(2, eventId);
				  pStmt.setString(1,eventVenue);
				  pStmt.setInt(3,eventVenueId);
				  result *= pStmt.executeUpdate();




		//---------------------ここからエラー処理-----------------------
			 if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}


	public  boolean updateAutherRemarkList(int id, String remark,int rid){//indexにeventIDを入力すると、そのeventIDのAutherRemarkを返す

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//UPDATE文準備
			pStmt = conn.prepareStatement(updateAutherRemark(id));
			//----------SQL文実行----------

				pStmt.setInt(2, id);
				pStmt.setString(1,remark);
				pStmt.setInt(3,rid);
				result *= pStmt.executeUpdate();




			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}



	public  boolean updatePricePerPersonList  (int id, String s,int rid){//indexにeventIDを入力すると、そのeventIDのAutherRemarkを返す

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//UPDATE文準備
			pStmt = conn.prepareStatement(updatePricePerPerson(id));
			//----------SQL文実行----------

				pStmt.setInt(2, id);
				pStmt.setInt(1,Integer.parseInt(s));
				pStmt.setInt(3,rid);
				result *= pStmt.executeUpdate();





			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}



	public  boolean updateCandidateList(int id, Calendar cal,int rid){//indexにeventIDを入力すると、そのeventIDのAutherRemarkを返す

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//UPDATE文準備
			pStmt = conn.prepareStatement(updateCandidate(id));
			//----------SQL文実行----------

				pStmt.setInt(2, id);
				pStmt.setString(1,getStrByCalendar(cal));
				pStmt.setInt(3,rid);
				result *= pStmt.executeUpdate();





			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}



	public  boolean updateBordItemList2(int eid,int itemId, String userName,
			String userPass, String userRemark,Calendar userRegistDay,BordItems2 item2){   //indexにeventIDを入力すると、そのeventIDのAutherRemarkを返す

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//UPDATE文準備
			pStmt = conn.prepareStatement(updateBordItems(itemId));
			//----------SQL文実行----------
				pStmt.setInt(5, item2.getItemId());
				pStmt.setInt(6,item2.getItemId());
				pStmt.setString(1, item2.getUserName());
				pStmt.setString(2, item2.getUserPass());
				pStmt.setString(3, item2.getUserRemark());
				pStmt.setString(4, getStrByCalendar(item2.getUserRegistDay()));
				result *= pStmt.executeUpdate();





			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}




	public boolean updatePreferredFlagSet(int eid,int itemId, int flag){

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//UPDATE文準備
			pStmt = conn.prepareStatement(updatePreferredFlag(eid,itemId));
			//----------SQL文実行----------

				pStmt.setInt(2, eid);
				pStmt.setInt(3,itemId);
				pStmt.setInt(1, itemId);
				result *= pStmt.executeUpdate();






			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}



	public   boolean updateEvent2List(Event2 event2){

		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		int result=1;
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);



			//UPDATE文準備
			pStmt = conn.prepareStatement(updateEvent2);
			//----------SQL文実行----------
			/*String insertEvent2 = "INSERT INTO EVENT ( EVENTID, EVENTNAME, ORGANIZARNAME, REGISTDAY, "
					+ "AUTHERNAME, AUTHERPASS, DEADLINEDAY, DETERMINEDDAY, "
					+ "DETERMINEDFLAG, EVENTOPENFLAG, NUMBEROFEVENT, "
					+ "EVENTURL, EVENTPAGEFILENAME) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";*/

				pStmt.setInt(13, Integer.parseInt(event2.getEventId()));
				pStmt.setString(1, event2.getEventName());
				pStmt.setString(2, event2.getOrganizarName());
				pStmt.setString(3, getStrByCalendar(event2.getRegistDay()));
				pStmt.setString(4, event2.getAutherName());
				pStmt.setString(5, event2.getAutherPass());
				pStmt.setString(6, getStrByCalendar(event2.getDeadlineDay()));
				pStmt.setString(7, getStrByCalendar(event2.getDeterminedDay()));
				pStmt.setInt(8, event2.getDeterminedFlag());
				pStmt.setInt(9, event2.getEventOpenFlag());
				pStmt.setInt(10, Integer.parseInt(event2.getNumberOfEvent()));
				pStmt.setString(11,event2.getEventUrl());
				pStmt.setString(12, event2.getEventPageFileName());
				result *= pStmt.executeUpdate();





			//---------------------ここからエラー処理-----------------------
			if(result != 1) return false;
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		//--------------------ここまでエラー処理---------------------------
		return true;

	}
	//----------------------------CREATE文method------------------------------
	public   boolean createEventVenueTable(int index){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(createEventVenue(index));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean createBordItemTable(int index){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(createBordItems(index));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean createAutherRemarkTable(int index){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(createAutherRemark(index));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean createPricePerPersonTable(int index){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(createPricePerPerson(index));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean createCandidateTable(int index){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(createCandidate(index));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean createPreferredFlagTable(int eid,int iid){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(createPreferredFlag(eid,iid));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}


	//--------------------------------DELETE method----------------------------------
	public   boolean deleteBordItemRecord(int eid,int id){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(deleteBordItems(eid,id));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
	public   boolean deleteEventTable(int eid){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(deleteEvent(eid));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean deleteAutherRemarkRecord(int eid,int id){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(deleteAutherRemark(eid ,id));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean deletePricePerPersonRecord(int eid,int id){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(deletePricePerPerson(eid,id));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean deleteCandidateRecord(int eid,int id){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(deleteCandidate(eid,id));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean deletePreferredFlagRecord(int eid,int iid,int id){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(deletePreferredFlag(eid,iid,id));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	//------------------------------------DROP Method----------------------------------------
	public   boolean dropEventVenueTable(int eid){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(dropEventVenue(eid));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean dropBordItemTable(int eid){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(dropBordItems(eid));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean dropAutherRemarkTable(int eid){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(dropAutherRemark(eid));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean dropPricePerPersonTable(int eid){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(dropPricePerPerson(eid));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean dropCandidateTable(int eid){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(dropCandidate(eid));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}

	public   boolean dropPreferredFlagTable(int eid,int iid){
		//----------接続パラメータ設定----------
		Connection conn = null;
		PreparedStatement  pStmt = null; // SQL文オブジェクト
		try {
			//--------------JDBCドライバ読み込み----------
			Class.forName("org.postgresql.Driver");

			//----------------DBに接続---------------------
			conn=DriverManager.getConnection(URL,ROLENAME,PASSWORD);

			//SELECT文準備
			pStmt = conn.prepareStatement(dropPreferredFlag(eid,iid));

			//----------SQL文実行----------
			  pStmt.executeUpdate();

		//---------------------ここからエラー処理-----------------------
		} catch (SQLException e){
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e){
			e.printStackTrace();
			return false;
		} finally {
			//-----DB切断-----
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}
}