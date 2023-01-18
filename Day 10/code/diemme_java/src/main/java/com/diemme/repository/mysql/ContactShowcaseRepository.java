package com.diemme.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.diemme.domain.mysql.ContactShowcase;

@Repository("ContactShowcaseRepository")
public interface ContactShowcaseRepository extends JpaRepository<ContactShowcase, Long> {

	@Query("SELECT c FROM ContactShowcase c WHERE c.active = true")
	public ContactShowcase findActiveContact();

}
