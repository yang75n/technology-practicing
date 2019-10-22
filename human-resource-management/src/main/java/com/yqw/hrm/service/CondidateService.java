package com.yqw.hrm.service;

import com.yqw.hrm.domain.Condidate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CondidateService {
    private static final Logger logger = LoggerFactory.getLogger(CondidateService.class);

    public List<Condidate> listAll() {
        logger.info("listAll");
        Condidate condidate = new Condidate();
        condidate.setName("张三");
        condidate.setGender("男");
        condidate.setAge(25);
        condidate.setTelephone("15623659856");
        condidate.setUniversity("清华大学");

        List<Condidate> condidates = new ArrayList<>();
        condidates.add(condidate);
        logger.info("listAll return=" + condidates);
        return condidates;
    }
}
