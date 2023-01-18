package it.nntdata.corso.springjsp.business.interfaces;

import org.springframework.dao.DataAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface WebSiteInfoBO {

    public it.nntdata.corso.springjsp.model.WebSiteInfo getWebSiteInfo() throws DataAccessException;

    void insertWebSiteInfo(it.nntdata.corso.springjsp.model.WebSiteInfo webSiteInfo) throws DataAccessException;

    void deleteWebSiteInfo(long id) throws DataAccessException;

    it.nntdata.corso.springjsp.model.WebSiteInfo findByIdFile(Long id) throws DataAccessException;
    void uploadFile(Long id, MultipartFile data) throws IOException ;
}
