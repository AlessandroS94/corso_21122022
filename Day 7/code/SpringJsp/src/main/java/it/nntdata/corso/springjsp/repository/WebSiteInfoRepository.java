package it.nntdata.corso.springjsp.repository;

import it.nntdata.corso.springjsp.model.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo, Long> {
    WebSiteInfo findFirstByOrderByIdDesc();
}