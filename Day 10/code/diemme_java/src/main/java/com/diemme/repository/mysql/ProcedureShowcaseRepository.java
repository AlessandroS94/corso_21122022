package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.ProcedureShowcase;

@Repository
public interface ProcedureShowcaseRepository extends JpaRepository<ProcedureShowcase, Long> {

}
