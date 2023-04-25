package com.example.madhyastha.pravaha.controller;

import com.example.madhyastha.pravaha.exception.MeetingException;
import com.example.madhyastha.pravaha.request.LaunchMeeting;
import com.example.madhyastha.pravaha.request.QuickMeeting;
import com.example.madhyastha.pravaha.service.MeetingAPI;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@RequestMapping("/v1/myMeeting/")
@AllArgsConstructor
@RestController
public class MadhyasthaController {
    private final MeetingAPI meetingAPI;

    @PostMapping("joiningURL")
    public ResponseEntity<String> quickStart(@RequestBody QuickMeeting quickMeeting) throws MeetingException {
        UUID uuid = UUID.randomUUID();
        String meetingId = uuid.toString();
        meetingId = meetingId +"-"+ System.currentTimeMillis();

        String meetingURL =
                meetingAPI.getJoinMeetingURL(meetingId,quickMeeting.getModeratorPassword(),quickMeeting.getUserDisplayName());
        if(meetingURL.contains("bigbluebutton")){
            meetingURL = meetingURL.replace("bigbluebutton", "navaantrix");
        }
        return new ResponseEntity<>(meetingURL, HttpStatus.OK);
    }

    @PostMapping("launchMeeting")
    public ResponseEntity<Boolean> startMeeting(@RequestBody LaunchMeeting launchMeeting) throws MeetingException {
        String meetingURL =  launchMeeting.getMeetingURL();
        if(meetingURL.contains("navaantrix")){
            meetingURL = meetingURL.replace("navaantrix", "bigbluebutton");
        }
        browse(meetingURL);
        return new ResponseEntity<>(Boolean.TRUE, HttpStatus.OK);
    }

    public static void browse(String url) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                String os = System.getProperty("os.name").toLowerCase();
                System.out.print("The current operating system is :- "+os);
                //runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
                // runtime.exec(new String[] { "chromium-browser", url});
                runtime.exec(new String[]{"cmd", "/c","start chrome "+url});

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
