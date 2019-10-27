package com.rest.billme.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.rest.billme.service.RequestLogService;

@Controller
public class RequestLogController {

    private final RequestLogService requestLogService;

    public RequestLogController(RequestLogService requestLogService) {
        this.requestLogService = requestLogService;
    }


    @GetMapping("/log")
    String getAllLogs(Model model) {
        model.addAttribute("requestLogs", requestLogService.getAllLogs());
        return "index";
    }
}
