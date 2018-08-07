package com.readwrite.ratelimtit;

import java.util.List;
import java.util.Map;

public interface DistributedRateLimit {


    /**
     *
     * 考虑最大访问数也做成可配置的
     *
     */
    public void init(String key,String context,Integer maxPermits,Integer rate);



    /**
     *
     * 申请令牌
     * @param context
     * @param key
     * @return
     */
    public boolean acquire(String context, String key);

}
