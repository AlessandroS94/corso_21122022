package com.ntt.accademy.springbootdatajsp.respository;

import com.ntt.accademy.springbootdatajsp.domain.Gruppo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GruppoRepository extends JpaRepository<Gruppo, Long> {
}