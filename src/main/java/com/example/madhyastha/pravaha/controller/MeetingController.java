package com.example.madhyastha.pravaha.controller;

import com.example.madhyastha.pravaha.exception.MeetingException;
import com.example.madhyastha.pravaha.request.MeetingDTO;
import com.example.madhyastha.pravaha.service.impl.MeetingAPIImpl;
import lombok.AllArgsConstructor;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@RequestMapping("/bigbluebutton/")
@AllArgsConstructor
@RestController
public class MeetingController {
private final MeetingAPIImpl api;

    @GetMapping("api/join")
    public ResponseEntity<String> createDepartment() throws MeetingException {

         String bbbUrl = "https://sam1.gyanada.in/bigbluebutton/api";
        /** BBB security salt */
         String bbbSalt = "cJgF1zxc8MjkiLO6FFfqquBL8rL3c9H9vlrfNyZUbL8";
        api.setBbbSalt(bbbSalt);
        api.setBbbUrl(bbbUrl);
        System.out.println("In Controller :- "+api.getUrl());
        MeetingDTO bbbMeeting =new MeetingDTO("897234324723-4lkjlj");
        bbbMeeting.setName("testeing meting");
        MeetingDTO meeting = api.createMeeting(bbbMeeting);

        String  url = api.getJoinMeetingURL(meeting.getMeetingID(),meeting.getModeratorPW(),"Amitkumar");

        System.out.println("Meeting URL :- "+url);


        final String uri = "https://sam1.gyanada.in/bigbluebutton/api";

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);

        browse(uri);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

   /* @EventListener({ApplicationReadyEvent.class})
    void applicationReadyEvent() {
        System.out.println("Application started ... launching browser now");
        browse("www.google.com");
    }*/

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
              //  runtime.exec(new String[]{"cmd", "/c","start chrome "+" https://sam1.gyanada.in/bigbluebutton/api/join?meetingID=897234324723-4lkjlj&fullName=Amitkumar&password=mxGqF6DE&checksum=8812e69388685a03bfa6f0ff4335291477478d3b"});
                ChromeDriver driver = new ChromeDriver();

                System.setProperty("webdriver.chrome.driver", "/usr/bin/google-chrome");

                // And now use this to visit Google
                driver.get(url);
            } catch (/*IOException*/Exception e) {
                e.printStackTrace();
            }
        }
    }
}
