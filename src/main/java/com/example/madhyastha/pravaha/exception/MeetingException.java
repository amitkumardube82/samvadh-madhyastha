package com.example.madhyastha.pravaha.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Exception generated while communicating with BBB server
 * 
 * see https://github.com/sakaicontrib/bbb-tool/blob/master/api/src/java/org/sakaiproject/bbb/api/BBBException.java
 */
@Service
@AllArgsConstructor
@NoArgsConstructor
public class MeetingException extends Exception {
	private static final long	serialVersionUID			= 2421100107566638321L;

	public static final String  MESSAGEKEY_HTTPERROR            = "httpError";
	public static final String  MESSAGEKEY_NOTFOUND             = "notFound";
	public static final String  MESSAGEKEY_NOACTION             = "noActionSpecified";
	public static final String  MESSAGEKEY_IDNOTUNIQUE          = "idNotUnique";
	public static final String  MESSAGEKEY_NOTSTARTED           = "notStarted";
	public static final String  MESSAGEKEY_ALREADYENDED         = "alreadyEnded";
	public static final String  MESSAGEKEY_INTERNALERROR        = "internalError";
	public static final String  MESSAGEKEY_UNREACHABLE          = "unreachableServerError";
	public static final String  MESSAGEKEY_INVALIDRESPONSE      = "invalidResponseError";
	public static final String  MESSAGEKEY_GENERALERROR         = "generalError";

	private String messageKey;

	public MeetingException(String messageKey, String message, Throwable cause) {
		super(message, cause);
		this.messageKey = messageKey;
	}

	public MeetingException(String messageKey, String message) {
		super(message);
		this.messageKey = messageKey;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	
	public String getPrettyMessage() {
		String _message = getMessage();
		String _messageKey = getMessageKey();
		
		StringBuilder pretty = new StringBuilder();
		if(_message != null) {
			pretty.append(_message);
		}
		if(_messageKey != null && !"".equals(_messageKey.trim())) {
			pretty.append(" (");
			pretty.append(_messageKey);
			pretty.append(")");
		}
		return pretty.toString();
	}
	
}