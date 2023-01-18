package it.nntdata.corso.springjsp.business.impl;


import it.nntdata.corso.springjsp.model.CryptoInfo;
import it.nntdata.corso.springjsp.repository.CryptoInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class CryptoInfoBOImpl implements it.nntdata.corso.springjsp.business.interfaces.CryptoInfoBO {
    @Autowired
    CryptoInfoRepository cryptoInfoRepository;

    @Override
    public void insertCrypto (String name, String url, MultipartFile image) throws DataAccessException, IOException {
        CryptoInfo cryptoInfo = new CryptoInfo();
        cryptoInfo.setImage(image.getBytes());
        cryptoInfo.setTypeImg(image.getContentType());
        cryptoInfo.setName(name);
        cryptoInfo.setUrl(url);
        cryptoInfoRepository.save(cryptoInfo);
    }
}
