package com.changgou.goods.client;


import api.FileApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author zhang
 * @date 2019年04月24日 14:41
 */
@FeignClient(value = "fastdfs")
public interface FileClient extends FileApi {
}
