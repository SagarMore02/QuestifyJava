package com.questify.questify.controller.organizer;

import com.questify.questify.controller.response.organizer.DashBoardResponse;
import com.questify.questify.service.OrganizerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/questify/organizer")
public class OrganizerController {
private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }
    @GetMapping("/dashboard")
    public DashBoardResponse getOrganizerDashboard() {
        return organizerService.getOrganizerDashboard();
    }
}
