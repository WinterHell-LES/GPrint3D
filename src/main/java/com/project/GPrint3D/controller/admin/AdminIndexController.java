package com.project.GPrint3D.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminIndexController
{
    @RequestMapping({ "/", "/index" })
    public ModelAndView index ()
    {
        return new ModelAndView("/admin/index");
    }
}
