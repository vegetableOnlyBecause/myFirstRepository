package com.example.cache;

import com.example.common.cache.GuavaCache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("guavaCacheTest")
public class GuavaCacheTest {

    @Resource
    GuavaCache guavaCache;

    public List<String> videoList()
    {
        try{
            Object cacheObj =
                    guavaCache.getCache().get("CacheKeyManagerINDEX_VIDEO_LIST",()->{
                        List<String> videoList = new ArrayList<>();
                        videoList.add("s1");
                        return videoList;
                    });
            if(cacheObj instanceof List){
                List<String> videoList = (List<String>)cacheObj;
                return videoList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<>();
        return null;
    }
}
