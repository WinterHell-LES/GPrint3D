package com.project.GPrint3D.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController 
{
    @RequestMapping({"/", "/index"})
    public ModelAndView index(@AuthenticationPrincipal User user, HttpServletRequest auth, Principal principal)
    {
        ModelAndView mv = new ModelAndView("/index");
        
        return mv;
    }
}
