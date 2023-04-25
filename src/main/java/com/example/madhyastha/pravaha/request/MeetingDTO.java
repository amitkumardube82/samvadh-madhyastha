package com.example.madhyastha.pravaha.request;

import java.util.Map;
import java.util.Date;
import java.util.HashMap;

import lombok.*;

/**
 *
 */

@Data
public class MeetingDTO {
	private String name = null;
	private String meetingID;
	private String attendeePW = null;
	private String moderatorPW = null;
	private String dialNumber = null;
	private String voiceBridge = null;
	private String webVoice = null;
	private String logoutURL = null;
	private Boolean record = null;
	private Long duration = null;
	
	// user cannot directly modify this field

	private Map<String, String> meta = new HashMap<String, String>();
	private String moderatorOnlyMessage = null;
	private Boolean autoStartRecording = null;
	private Boolean allowStartStopRecording = null;
	private Boolean webcamsOnlyForModerator = null;
	private String logo = null;
	private String copyright = null;
	private Boolean muteOnStart = null;
	private String welcome = null;
	private Date startDate = null;
	private Date endDate = null;
	
	public MeetingDTO(String meetingID) {
		this.meetingID = meetingID;
	}
	
	public void addMeta(String key, String value) {
		meta.put(key, value);
	}
	
	public void removeMeta(String key) {
		if (meta.containsKey(key))
			meta.remove(key);
	}
}
