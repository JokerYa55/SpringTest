/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author vasil
 */
@Component
public class ShedulerTask {

    private static final Logger LOG = Logger.getLogger(ShedulerTask.class.getName());
    
    @Autowired
    LoadDao loadDao;
    
    @Scheduled(fixedRate = 5000)
    private void uploadData() throws IOException{
        LOG.info("uploadData");
        loadDao.upload();
    }
}
