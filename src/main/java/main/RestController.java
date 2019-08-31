/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author vasil
 */
@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/api")
public class RestController {

    @Autowired
    AppCache cache;

    @Autowired
    LoadDao loadDao;

    @GetMapping(path = "/test", produces = "application/json")
    public String getEmployees() {
        cache.getCache().put("test", "99");
        return "test + " + cache.getCache().get("test");
    }

    @PutMapping(path = "/reload")
    public void reload() throws IOException {
        loadDao.upload();
    }

    @GetMapping(path = "/code/{countruname}")
    public ResponseEntity getCountryCode(@PathVariable(value = "countruname") String countryname) {
        String result = (String) cache.getCache().get(countryname.toLowerCase());
        if (result == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Actor Not Found");
        } else {
            return ResponseEntity.ok(result);
        }

    }
}
