package dao;
import java.util.Calendar;
public class BordItems2 {
	private int EventId;
	private int itemId ;
	private String userName ;
	private String userPass ;
	private String userRemark ;
	private Calendar userRegistDay ;
/*	public BordItems2(int itemId, String userName, String userPass, String userRemark,Calendar userRegistDay) {
		super();
		this.itemId = itemId;
		this.userName = userName;
		this.userPass = userPass;
		this.userRemark = userRemark;
		this.userRegistDay = userRegistDay;
	}*/

	public BordItems2(int eventId, int itemId, String userName, String userPass, String userRemark,
			Calendar userRegistDay) {
		super();
		EventId = eventId;
		this.itemId = itemId;
		this.userName = userName;
		this.userPass = userPass;
		this.userRemark = userRemark;
		this.userRegistDay = userRegistDay;
	}

	public int getEventId() {
		return EventId;
	}

	public void setEventId(int eventId) {
		EventId = eventId;
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPass() {
		return userPass;
	}
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	public String getUserRemark() {
		return userRemark;
	}
	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}
	public Calendar getUserRegistDay() {
		return userRegistDay;
	}
	public void setUserRegistDay(Calendar userRegistDay) {
		this.userRegistDay = userRegistDay;
	}


}
