package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.NewsShowcase;



@Repository("NewsShowcaseRepository")
public interface NewsShowcaseRepository extends JpaRepository<NewsShowcase, Long>{

}
