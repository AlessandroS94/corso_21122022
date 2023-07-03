package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.business.interfaces.TechnologyService;
import com.diemme.domain.mysql.TechnologyShowcase;


@Repository("TecnologyShowcaseRepository")
public interface TechnologyShowcaseRepository extends JpaRepository<TechnologyShowcase, Long> {

	TechnologyService save(TechnologyService technology);

}
