package com.example.madhyastha.pravaha.service;

import java.util.Map;
import com.example.madhyastha.pravaha.exception.MeetingException;
import com.example.madhyastha.pravaha.request.MeetingDTO;
import com.example.madhyastha.pravaha.util.MeetingModule;

/**
 * List of supported API for samvadh
 *
 */
public interface MeetingAPI {
	
	/**
	 * Get the API version of the server
	 * 
	 * @return API version
	 */
	public String getAPIVersion();

	/**
	 * Get the base url of the server
	 * 
	 * @return url
	 */
	public String getUrl();

	/**
     * Allow user to create meeting
     * 
     * @return BBBMeeting object on success, on failure, MeetingException is thrown
     * @throws MeetingException
     */
	public MeetingDTO createMeeting(final String meetingID) throws MeetingException;
	public MeetingDTO createMeeting(final MeetingDTO meeting) throws MeetingException;
	public MeetingDTO createMeeting(final MeetingDTO meeting, final MeetingModule module) throws MeetingException;
	
	/**
	 * Check if the meeting is already running
	 * 
	 * @return true if the meeting is running, false otherwise
	 * @throws MeetingException
	 */
	public boolean isMeetingRunning(String meetingID) throws MeetingException;
	
	/**
	 * Get meeting information corresponds to the given meetingID and role
	 * 
	 * @return Map that contains all meeting information
	 * @throws MeetingException
	 */
	public Map<String, Object> getMeetingInfo(String meetingID, String password) throws MeetingException;
	public Map<String, Object> getMeetingInfo(final MeetingDTO meeting) throws MeetingException;

	/**
	 * End the given meeting
	 * 
	 * @return true if the meeting is successfully ended or does not exist, false otherwise
	 * @throws MeetingException
	 */
	public boolean endMeeting(String meetingID, String password) throws MeetingException;
	public boolean endMeeting(final MeetingDTO meeting) throws MeetingException;

	/**
	 * Get the url to join the given meeting with the display name and corresponding role type
	 * 
	 * @return url for joining the meeting
	 */
	public String getJoinMeetingURL(String meetingID, String password, String userDisplayName);
	public String getJoinMeetingURL(String meetingID, String password, String userDisplayName, String userId);
	
	/**
	 * Get the list of all live meetings in server, every parameter like meetingIDs can be a list of meetings but separated
	 * by commas(e.g., "id1,id2")
	 * 
	 * @return a map which has a field named meetings, and the value is the list of meeting information
	 * @throws MeetingException
	 */
	public Map<String, Object> getMeetings() throws MeetingException;
	
	/**
	 * Get the list of recordings that map the given fields
	 * 
	 * @return a map which has a field named recordings, and the value is the list of recording information
	 * @throws MeetingException
	 */
	public Map<String, Object> getRecordings() throws MeetingException;
	public Map<String, Object> getRecordings(String meetingIDs) throws MeetingException;
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs) throws MeetingException;
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs, String states) throws MeetingException;
	public Map<String, Object> getRecordings(String meetingIDs, String recordIDs, String states, Map<String, String> meta) throws MeetingException;
	
	/**
	 * Delete a given recording (or a list of recordings whose ids are separated by commas)
	 * 
	 * @return true if the recording is successfully deleted, exception is thrown on failure
	 * @throws MeetingException
	 */
	public boolean deteteRecordings(String recordIDs) throws MeetingException;
	
	/**
	 * Publish/unpublish the recording(s)
	 * 
	 * @return true if the recording(s) is successfully published/unpublished, exception is thrown on failure
	 * @throws MeetingException
	 */
	public boolean publishRecordings(String recordIDs, boolean publish) throws MeetingException;

	/**
	 * Update the recording(s)
	 * 
	 * @return true if the recording(s) is successfully updated, exception is thrown on failure
	 * @throws MeetingException
	 */
	public boolean updateRecordings(String recordIDs) throws MeetingException;
	public boolean updateRecordings(String recordIDs, Map<String, String> meta) throws MeetingException;
	
	/**
	 * Get the default config xml file from the BBB server and save it to the given file path
	 * 
	 * @return true on success, on failure, MeetingException is thrown
	 * @throws MeetingException
	 */
	public boolean getDefaultConfigXML(String fileName) throws MeetingException;
	
	
	/**
	 * Set the config for the given meeting
	 * 
	 * @return true on success, on failure, MeetingException is thrown
	 * @throws MeetingException
	 */
	public boolean setConfigXML(String meetingID, String fileName) throws MeetingException;
}
