package model;

import java.util.ArrayList;
import java.util.Calendar;

public class Event  {
	//----------Fields----------
	private String  eventId;//イベントID(一意)
	private String eventName;//イベントの名前
	private String organizarName;//幹事の名前
	private ArrayList <String> eventVenue;//イベントの場所
	private Calendar  registDay;//投稿日時
	private String autherName;//イベント製作者の名前
	private String autherPass;//イベント製作者の編集用pass
	private Calendar deadlineDay;//締め切り日時
    private ArrayList <String> autherRemark;//イベント製作者の備考欄
	private Calendar determinedDay ;//確定日時
	private int determinedFlag;//イベント確定のフラグ 1:確定,0:未確定
	private int eventOpenFlag;//イベントの公開フラグ.1:公開,0:非公開
	private String numberOfEvent;//イベント会数
	private String eventUrl;//イベントページのURL
	private String eventPageFileName;//イベントページファイルの名前
	private ArrayList<String> pricePerPerson;//イベント一人当たりの料金
	private ArrayList<Calendar> candidate ; //イベント候補日の集合
	static int eventCounter=0;
	public static String masterKey = "password";//管理者用のパスワード
	static String str="undefined";
	//----------Constructor----------
	public Event(String eventName, String organizarName, ArrayList<String> eventVenue,
			Calendar registDay, String autherName, String autherPass, Calendar deadlineDay,
			ArrayList<String> autherRemark, Calendar determinedDay, int determinedFlag, int eventOpenFlga,
			String numberOfEvent, String eventUrl, String eventPageFileName, ArrayList<String> pricePerPerson,
			ArrayList<Calendar>candidate) {
		super();
		this.eventId = String.valueOf(eventCounter);
		this.eventName = eventName;
		this.organizarName = organizarName;
		this.eventVenue = eventVenue;
		this.registDay = Calendar.getInstance();
		this.autherName = autherName;
		this.autherPass = autherPass;
		this.deadlineDay = deadlineDay;
		this.autherRemark = autherRemark;
		this.determinedDay = Calendar.getInstance();
		this.determinedFlag = 0;
		this.eventOpenFlag = eventOpenFlga;
		this.numberOfEvent = numberOfEvent;
		this.eventUrl = "undefined";
		this.eventPageFileName = "undefined";
		this.pricePerPerson = pricePerPerson;
		this.candidate = candidate;
		eventCounter++;
	}

	//----------Getter and Setter----------
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;

	}

	public String getOrganizarName() {
		return organizarName;
	}

	public void setOrganizarName(String organizarName) {
		this.organizarName = organizarName;
	}

	public ArrayList<String> getEventVenue() {
		return eventVenue;
	}

	public void setEventVenue(ArrayList<String> eventVenue) {
		this.eventVenue = eventVenue;
	}

	public ArrayList<String> getPricePerPerson() {
		return pricePerPerson;
	}

	public void setPricePerPerson(ArrayList<String> pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Calendar getRegistDay() {
		return registDay;
	}

	public void setRegistDay(int year,int month,int date,int hour, int minute ,int second ) {
		setYear(this.registDay ,year);
		setMonth(this.registDay ,month);
		setDate(this.registDay ,date);
		setHour(this.registDay ,hour);
		setMinute(this.registDay ,minute);
		setSecond(this.registDay ,second);
	}

	public int getRegistYear(){
		return getYear(registDay);
	}
	public int getRegistMonth(){
		return getMonth(registDay);
	}
	public int getRegistDate(){
		return getDate(registDay);
	}
	public int getRegistHour(){
		return getHour(registDay);
	}
	public int getRegistMinute(){
		return getMinute(registDay);
	}
	public int getRegistSecond(){
		return getSecond(registDay);
	}

	public void setRegistYear(int year){
		setYear(this.registDay ,year);
	}
	public void setRegistMonth(int month){
		setMonth(this.registDay ,month);
	}
	public void setRegistDate(int date){
		setDate(this.registDay ,date);
	}
	public void setRegistHour(int hour){
		setHour(this.registDay ,hour);
	}
	public void setRegistMinute(int minute){
		setMinute(this.registDay ,minute);
	}
	public void setRegistSeconde(int second){
		setSecond(this.registDay ,second);
	}


	public String getAutherName() {
		return autherName;
	}

	public void setAutherName(String autherName) {
		this.autherName = autherName;
	}

	public String getAutherPass() {
		return autherPass;
	}

	public void setAutherPass(String autherPass) {
		this.autherPass = autherPass;
	}

	public Calendar getDeadlineDay() {
		return deadlineDay;
	}

	public void setDeadlineDay(int year,int month,int date,int hour, int minute ,int second ) {
		setYear(this.deadlineDay ,year);
		setMonth(this.deadlineDay ,month);
		setDate(this.deadlineDay ,date);
		setHour(this.deadlineDay ,hour);
		setMinute(this.deadlineDay ,minute);
		setSecond(this.deadlineDay ,second);
	}

	public int getDeadlineYear(){
		return getYear(deadlineDay);
	}
	public int getDeadlineMonth(){
		return getMonth(deadlineDay);
	}
	public int getDeadlineDate(){
		return getDate(deadlineDay);
	}
	public int getDeadlineHour(){
		return getHour(deadlineDay);
	}
	public int getDeadlineMinute(){
		return getMinute(deadlineDay);
	}
	public int getDeadlineSecond(){
		return getSecond(deadlineDay);
	}

	public void setDeadlineYear(int year){
		setYear(this.deadlineDay ,year);
	}
	public void setDeadlineMonth(int month){
		setMonth(this.deadlineDay ,month);
	}
	public void setDeadlineDate(int date){
		setDate(this.deadlineDay ,date);
	}
	public void setDeadlineHour(int hour){
		setHour(this.deadlineDay ,hour);
	}
	public void setDeadlineMinute(int minute){
		setMinute(this.deadlineDay ,minute);
	}
	public void setDeadlineSeconde(int second){
		setSecond(this.deadlineDay ,second);
	}

	public ArrayList<String> getAutherRemark() {
		return autherRemark;
	}

	public void setAutherRemark(ArrayList<String> autherRemark) {
		this.autherRemark = autherRemark;
	}

	public Calendar getDeterminedDay() {
		return determinedDay;
	}

	public void setDeterminedDay(int year,int month,int date,int hour, int minute ,int second ) {
		setYear(this.determinedDay ,year);
		setMonth(this.determinedDay ,month);
		setDate(this.determinedDay ,date);
		setHour(this.determinedDay ,hour);
		setMinute(this.determinedDay ,minute);
		setSecond(this.determinedDay ,second);
	}

	public int getDeterminedYear(){
		return getYear(registDay);
	}
	public int getDeterminedMonth(){
		return getMonth(registDay);
	}
	public int getDeterminedDate(){
		return getDate(registDay);
	}
	public int getDeterminedHour(){
		return getHour(registDay);
	}
	public int getDeterminedMinute(){
		return getMinute(registDay);
	}
	public int getDeterminedSecond(){
		return getSecond(registDay);
	}

	public void setDeterminedYear(int year){
		setYear(this.registDay ,year);
	}
	public void setDeterminedMonth(int month){
		setMonth(this.registDay ,month);
	}
	public void setDeterminedDate(int date){
		setDate(this.registDay ,date);
	}
	public void setDeterminedHour(int hour){
		setHour(this.registDay ,hour);
	}
	public void setDeterminedMinute(int minute){
		setMinute(this.registDay ,minute);
	}
	public void setDeterminedSeconde(int second){
		setSecond(this.registDay ,second);
	}

	public int getDeterminedFlag() {
		return determinedFlag;
	}

	public void setDeterminedFlag(int determinedFlag) {
		this.determinedFlag = determinedFlag;
	}

	public int getEventOpenFlga() {
		return eventOpenFlag;
	}

	public void setEventOpenFlga(int eventOpenFlga) {
		this.eventOpenFlag = eventOpenFlga;
	}

	public String getNumberOfEvent() {
		return numberOfEvent;
	}

	public void setNumberOfEvent(String numberOfEvent) {
		this.numberOfEvent = numberOfEvent;
	}

	public String getEventUrl() {
		return eventUrl;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}

	public String getEventPageFileName() {
		return eventPageFileName;
	}

	public void setEventPageFileName(String eventPageFileName) {
		this.eventPageFileName = eventPageFileName;
	}

	public static int getEventCounter() {
		return eventCounter;
	}

	public static void setEventCounter(int eventCounter) {
		Event.eventCounter = eventCounter;
	}



	public ArrayList<Calendar> getCandidate() {
		return candidate;
	}

	public void setCandidate(ArrayList<Calendar> candidate) {
		this.candidate = candidate;
	}

	public void addCandidate(Calendar candidate){
		this.candidate.add(candidate);
	}

	public Calendar getCandidateElement(int i){ //i番目の要素(Calendar Class)を取り出す
		return candidate.get(i);
	}

	//----------instance methods----------
	public boolean isDeadlineDay(Calendar nowTime){
		nowTime=Calendar.getInstance();
		return deadlineDay.before(nowTime);
	}

	public boolean isAutherPassEquals(String key){
		if(autherPass == key) return true;
		else return false;
	}


	//----------static methods----------
	public static boolean isNotStrEnpty (String str){
		if(str!= null && str.length() != 0) return true;
		else return false;
	}
	public static boolean isFlagEquals(int n){
		if(n==1) return true;
		else return false;
	}

	public  static int getYear(Calendar cal){
		return cal.get(Calendar.YEAR);
	}

	public  static int getMonth(Calendar cal){
		return cal.get(Calendar.MONTH);
	}

	public  static int getDate(Calendar cal){
		return cal.get(Calendar.DATE);
	}

	public  static int getHour(Calendar cal){
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public  static int getMinute(Calendar cal){
		return cal.get(Calendar.MINUTE);
	}

	public  static int getSecond(Calendar cal){
		return cal.get(Calendar.SECOND);
	}

	public  static void setYear(Calendar cal,int n){
		if(n>0) cal.set(Calendar.YEAR, n);
		else cal.set(Calendar.YEAR, Calendar.YEAR);
	}

	public  static void setMonth(Calendar cal,int n){
		if(n>=0) cal.set(Calendar.MONTH, n);
		else cal.set(Calendar.MONTH, Calendar.MONTH );
	}

	public  static void setDate(Calendar cal,int n){
		if(n>0) cal.set(Calendar.DATE, n);
		else cal.set(Calendar.DATE, Calendar.DATE);
	}

	public  static void setHour(Calendar cal,int n){
		if(n>0) cal.set(Calendar.HOUR_OF_DAY, n);
		else cal.set(Calendar.HOUR_OF_DAY, Calendar.HOUR_OF_DAY);
	}

	public  static void setMinute(Calendar cal,int n){
		if(n>0) cal.set(Calendar.MINUTE, n);
		else cal.set(Calendar.MINUTE, Calendar.MINUTE);
	}

	public  static void setSecond(Calendar cal,int n){
		if(n>0) cal.set(Calendar.SECOND, n);
		else cal.set(Calendar.SECOND, Calendar.SECOND);
	}

    public static String rightstring(String value, int length){
        try{
            if(value.length() >= length){
                return value.substring(value.length() - length);
            }else{
                return  value.substring(1);
            }
        }catch(Exception e){
            return value;
        }
    }

    public static String getIdFromURL(String url){
    	return rightstring(url,4);
    }

}