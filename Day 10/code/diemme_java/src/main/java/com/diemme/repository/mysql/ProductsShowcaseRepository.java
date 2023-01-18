package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.ProductShowcase;

@Repository("ProducsShowcaseRepository")
public interface ProductsShowcaseRepository extends JpaRepository<ProductShowcase, Long>{

}
