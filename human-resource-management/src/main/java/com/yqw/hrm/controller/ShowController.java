package com.yqw.hrm.controller;

import com.yqw.hrm.domain.Condidate;
import com.yqw.hrm.service.CondidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ShowController {

    Logger logger = LoggerFactory.getLogger(ShowController.class);

    @Autowired
    private CondidateService condidateService;

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello,World";
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Condidate> listAll() {
        return condidateService.listAll();
    }

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(ModelMap map) {
        logger.info("/show show map=" + map);
        map.addAttribute("cds", condidateService.listAll());
        return "show";
    }
}
