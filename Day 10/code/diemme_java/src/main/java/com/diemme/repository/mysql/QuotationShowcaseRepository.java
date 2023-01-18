package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.QuotationShowcase;

@Repository("QuotationShowcaseRepository")
public interface QuotationShowcaseRepository extends JpaRepository<QuotationShowcase, Long> {

}
