package com.example.madhyastha.pravaha.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuickMeeting {
    private String userDisplayName;
    private String moderatorPassword;
}
