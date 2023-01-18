package it.nntdata.corso.springjsp.business.interfaces;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CryptoInfoBO {
    void insertCrypto (String name, String url, MultipartFile image) throws DataAccessException, IOException;
}
