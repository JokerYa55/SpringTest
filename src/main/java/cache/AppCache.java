/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache;

import org.infinispan.Cache;
import org.springframework.stereotype.Repository;

/**
 *
 * @author vasil
 */
@Repository
public class AppCache {

    private Cache cache = null;

    public Cache getCache() {
        if (cache == null) {
            cache = CacheLocalFactory.create("testApp", "/temp/cache/testApp");
        } 
        return cache;
    }
}
