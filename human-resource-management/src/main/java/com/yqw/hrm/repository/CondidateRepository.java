package com.yqw.hrm.repository;


import com.yqw.hrm.domain.Condidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CondidateRepository extends JpaRepository<Condidate, Integer> {
}
