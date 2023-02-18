package com.analytics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping
public class AnalyticsController {
    @GetMapping("dashboard")
    public String analytics(HttpServletRequest request, Model model)
    {
        String forwardedFor = request.getHeader("X-Forwarded-For");
        if (forwardedFor != null)
        {   //String clientIP = forwardedFor.split(",")[0].trim();
            model.addAttribute("forwarded",true);
            model.addAttribute("client",forwardedFor);
        }
        else
        {model.addAttribute("forwarded",false);}
        return "analytics";
    }

}
