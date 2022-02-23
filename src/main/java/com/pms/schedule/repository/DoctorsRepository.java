package com.pms.schedule.repository;

import com.pms.schedule.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctor, Integer> {

    // SELECT * FROM doctor where treatingAilment=string;
    List<Doctor> findAllByTreatingAilment(String treatingAilment);
}
