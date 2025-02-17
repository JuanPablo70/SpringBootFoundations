package com.juan.sanchez.web.form;

import com.juan.sanchez.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
class WelcomeController {

    @Value("${spring.application.name}")
    private String appName;

    @RequestMapping(path={"/welcome", "/welcome.html"},
                    method= RequestMethod.GET,
                    produces= MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    String welcome(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("currentDataTime", DateTimeUtils.currentDateTime());
        return "general/welcome";
    }

//    @RequestMapping(path={"/home","/home.html"},
//			        method=RequestMethod.GET,
//	                produces=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//	String home() {
//		return "general/home";
//	}

}
