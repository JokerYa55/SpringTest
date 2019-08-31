/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import cache.AppCache;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vasil
 */
@Repository
public class LoadDao {

    @Autowired
    private AppCache cache;

    @Autowired
    private CountryRepository countryDao;
    
    private static final Object LOCK = new Object();

    private static final String URL_COUNTRY = "http://country.io/names.json";
    private static final String URL_PHONE_CODE = "http://country.io/phone.json";
    
    public void upload() throws IOException {
        synchronized (LOCK) {
            System.out.println("test");
            Map<String, Object> countryList = getJson(URL_COUNTRY);
            countryList.forEach((t, u) -> {
                System.out.println(String.format("t = %s u - %s", t, u));
            });
            Map<String, Object> phoneCodeList = getJson(URL_PHONE_CODE);
            phoneCodeList.forEach((t, u) -> {
                System.out.println(String.format("t = %s u - %s", t, u));
            });
            countryList.forEach((t, u) -> {
                cache.getCache().put(u.toString().toLowerCase(), phoneCodeList.get(t));
            });            
        }
    }

    private Map<String, Object> getJson(String url) throws MalformedURLException, IOException {
        ObjectMapper om = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> mapRes = om.readValue(new URL(url).openStream(), Map.class);
        return mapRes;
    }
}
