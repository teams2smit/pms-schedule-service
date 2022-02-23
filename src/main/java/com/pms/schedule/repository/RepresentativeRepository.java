package com.pms.schedule.repository;

import com.pms.schedule.entity.Representative;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative, Integer> {

    List<Representative> findByAilment(String ailment);

    Optional<Representative> findByRepresentativeName(String repName);
}
