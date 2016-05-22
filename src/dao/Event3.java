package dao;

import java.util.Calendar;

public class Event3 extends Event2 {
	private String autherRemark;

	public Event3(String eventId, String eventName, String organizarName, Calendar registDay, String autherName,
			String autherPass, Calendar deadlineDay, Calendar determinedDay, int determinedFlag, int eventOpenFlag,
			String numberOfEvent, String eventUrl, String eventPageFileName, String autherRemark) {
		super(eventId, eventName, organizarName, registDay, autherName, autherPass, deadlineDay, determinedDay,
				determinedFlag, eventOpenFlag, numberOfEvent, eventUrl, eventPageFileName);
		this.autherRemark = autherRemark;
	}

	public String getAutherRemark() {
		return autherRemark;
	}

	public void setAutherRemark(String autherRemark) {
		this.autherRemark = autherRemark;
	}


}
