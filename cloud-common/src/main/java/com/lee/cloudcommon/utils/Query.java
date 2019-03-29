package com.lee.cloudcommon.utils;

import com.baomidou.mybatisplus.plugins.Page;
import org.bouncycastle.i18n.filter.SQLFilter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
    private static final long serialVersionUID = 1L;
    private int offset;
    private int limit;
    private int page;
    /**
     * 可以根据offset和limit查询，也可以根据page和limit查询
     */

    public Query(Map<String, Object> params){
        this.putAll(params);
        if (null != params.get("limit")){
            this.limit = Integer.parseInt(params.get("limit").toString());
            if (null != params.get("offset")){
                this.offset = Integer.parseInt(params.get("offset").toString());
                this.put("limit",limit);
            }
            if (null != params.get("page")){
                this.page = Integer.parseInt(params.get("page").toString());
                this.put("offset",(page - 1)* limit);
                this.put("limit",limit);
            }
        }
    }

    public int getOffset(){
        return offset;
    }
    public void setOffset(int offset){
        this.put("offset",offset);
    }
    public int getLimit(){
        return limit;
    }
}
