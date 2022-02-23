package com.pms.schedule.repository;

import com.pms.schedule.entity.Representative;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class RepresentativeRepositoryTest {

    @Autowired
    private RepresentativeRepository representativeRepository;


    void test_representative_repository() {
        Iterable<Representative> representativeIterable = representativeRepository.findAll();
        List<Representative> listActual = new ArrayList<>();

        representativeIterable.forEach(listActual::add);

        assertEquals(6, listActual.size());

    }
}