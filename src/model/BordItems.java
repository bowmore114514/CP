package model;
import java.util.ArrayList;
import java.util.Calendar;

public class BordItems {
	//----------Fields----------
	private String itemId;//投稿ID
	//private ArrayList<Calendar>  preferredDaySet; //希望日の集合
	private ArrayList<Integer> preferredFlagSet;//希望日時ごとのフラグ 1:参加,0:不参加(初期値), 2:△
	private String userName; //投稿者の名前
	private String userPass;//編集用のpass
	private String userRemark;//投稿者の備考
	private Calendar userRegistDay;//投稿日時
	static int itemCounter = 0;//投稿ID生成用のカウンター



	//----------Constructor----------
	public BordItems(String itemId, /*ArrayList<Calendar> preferredDaySet*/ ArrayList<Integer> preferredFlagSet,
			String userName, String userPass, String userRemark, Calendar userRegistDay) {
		super();
		this.itemId = String.valueOf(itemCounter);
		//this.preferredDaySet = preferredDaySet;
		this.preferredFlagSet = preferredFlagSet;
		this.userName = userName;
		this.userPass = userPass;
		this.userRemark = userRemark;
		this.userRegistDay = userRegistDay;
		itemCounter ++;
	}


	//----------Getter and Setter----------
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/*public ArrayList<Calendar> getPreferredDaySet() {//ArrayList型を返すことに注意
		return preferredDaySet;
	}

	public void addPreferredDaySet(Calendar preferredDay) {//preferredDaySetにCalendarクラスを一つ追加
		this.preferredDaySet.add(preferredDay);
	}*/


	public ArrayList<Integer> getPreferredFlagSet() {
		return preferredFlagSet;
	}

	public void addPreferredFlagSet(int preferredFlag) {//preferredFlagSetにint型のFlagを一つ追加
		this.preferredFlagSet.add(preferredFlag);
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
	public void setUserRegistDay(int year,int month,int date,int hour, int minute ,int second ) {
		Event.setYear(this.userRegistDay ,year);
		Event.setMonth(this.userRegistDay ,month);
		Event.setDate(this.userRegistDay ,date);
		Event.setHour(this.userRegistDay ,hour);
		Event.setMinute(this.userRegistDay ,minute);
		Event.setSecond(this.userRegistDay ,second);
	}

	public int getUserRegistYear(){
		return Event.getYear(userRegistDay);
	}
	public int getRegistMonth(){
		return Event.getMonth(userRegistDay);
	}
	public int getRegistDate(){
		return Event.getDate(userRegistDay);
	}
	public int getRegistHour(){
		return Event.getHour(userRegistDay);
	}
	public int getRegistMinute(){
		return Event.getMinute(userRegistDay);
	}
	public int getRegistSecond(){
		return Event.getSecond(userRegistDay);
	}

	public void setRegistYear(int year){
		Event.setYear(this.userRegistDay ,year);
	}
	public void setRegistMonth(int month){
		Event.setMonth(this.userRegistDay ,month);
	}
	public void setRegistDate(int date){
		Event.setDate(this.userRegistDay ,date);
	}
	public void setRegistHour(int hour){
		Event.setHour(this.userRegistDay ,hour);
	}
	public void setRegistMinute(int minute){
		Event.setMinute(this.userRegistDay ,minute);
	}
	public void setRegistSeconde(int second){
		Event.setSecond(this.userRegistDay ,second);
	}

	public static int getItemCounter() {
		return itemCounter;
	}

	public static void setItemCounter(int itemCounter) {
		BordItems.itemCounter = itemCounter;
	}

	public boolean isUserPassEquals (String str){//ユーザーパス確認
		return false;
	}

}
