package dao;

import java.util.Calendar;

public class Event2 {
	private String  eventId;//イベントID(一意)
	private String eventName;//イベントの名前
	private String organizarName;//幹事の名前
	private Calendar  registDay;//投稿日時
	private String autherName;//イベント製作者の名前
	private String autherPass;//イベント製作者の編集用pass
	private Calendar deadlineDay;//締め切り日時
	private Calendar determinedDay ;//確定日時
	private int determinedFlag;//イベント確定のフラグ 1:確定,0:未確定
	private int eventOpenFlag;//イベントの公開フラグ.1:公開,0:非公開
	private String numberOfEvent;//イベント会数
	private String eventUrl;//イベントページのURL
	private String eventPageFileName;//イベントページファイルの名前
	public Event2(String eventId, String eventName, String organizarName, Calendar registDay, String autherName,
			String autherPass, Calendar deadlineDay, Calendar determinedDay, int determinedFlag, int eventOpenFlag,
			String numberOfEvent, String eventUrl, String eventPageFileName) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.organizarName = organizarName;
		this.registDay = registDay;
		this.autherName = autherName;
		this.autherPass = autherPass;
		this.deadlineDay = deadlineDay;
		this.determinedDay = determinedDay;
		this.determinedFlag = determinedFlag;
		this.eventOpenFlag = eventOpenFlag;
		this.numberOfEvent = numberOfEvent;
		this.eventUrl = eventUrl;
		this.eventPageFileName = eventPageFileName;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getOrganizarName() {
		return organizarName;
	}
	public void setOrganizarName(String organizarName) {
		this.organizarName = organizarName;
	}
	public Calendar getRegistDay() {
		return registDay;
	}
	public void setRegistDay(Calendar registDay) {
		this.registDay = registDay;
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
	public void setDeadlineDay(Calendar deadlineDay) {
		this.deadlineDay = deadlineDay;
	}
	public Calendar getDeterminedDay() {
		return determinedDay;
	}
	public void setDeterminedDay(Calendar determinedDay) {
		this.determinedDay = determinedDay;
	}
	public int getDeterminedFlag() {
		return determinedFlag;
	}
	public void setDeterminedFlag(int determinedFlag) {
		this.determinedFlag = determinedFlag;
	}
	public int getEventOpenFlag() {
		return eventOpenFlag;
	}
	public void setEventOpenFlag(int eventOpenFlag) {
		this.eventOpenFlag = eventOpenFlag;
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


}
