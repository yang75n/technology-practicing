package com.yqw.hrm.service;

import com.yqw.hrm.domain.Condidate;
import com.yqw.hrm.repository.CondidateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CondidateService {
    private static final Logger logger = LoggerFactory.getLogger(CondidateService.class);

    @Autowired
    private CondidateRepository condidateRepository;

    public void save(Condidate condidate) {
        condidateRepository.save(condidate);
    }


    public List<Condidate> listAll() {
        logger.info("listAll");


        List<Condidate> condidates = condidateRepository.findAll();

        logger.info("get from database, conditates=" + condidates);

        if (condidates == null || condidates.size() == 0) {
            Condidate condidate = new Condidate();
            condidate.setName("张三");
            condidate.setGender("男");
            condidate.setAge(25);
            condidate.setTelephone("15623659856");
            condidate.setUniversity("清华大学");

            condidates.add(condidate);
        }

        logger.info("listAll return=" + condidates);

        return condidates;
    }


}
