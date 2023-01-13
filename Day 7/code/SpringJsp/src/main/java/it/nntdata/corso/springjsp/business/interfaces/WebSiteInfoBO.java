package it.nntdata.corso.springjsp.business.interfaces;

import it.nntdata.corso.springjsp.model.WebSiteInfo;
import org.springframework.dao.DataAccessException;

public interface WebSiteInfoBO {

    public WebSiteInfo getWebSiteInfo() throws DataAccessException;

    void insertWebSiteInfo(WebSiteInfo webSiteInfo) throws DataAccessException;

    void deleteWebSiteInfo(long id) throws DataAccessException;
}
