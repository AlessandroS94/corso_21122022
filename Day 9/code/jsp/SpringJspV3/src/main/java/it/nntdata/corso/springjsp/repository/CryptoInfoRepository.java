package it.nntdata.corso.springjsp.repository;

import it.nntdata.corso.springjsp.model.CryptoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CryptoInfoRepository extends JpaRepository<CryptoInfo, Long> {
}