package com.changgou.file.controller;

import com.changgou.entity.Result;
import com.changgou.entity.StatusCode;
import com.changgou.file.pojo.FastDFSFile;
import com.changgou.file.util.FastDFSClient;
import org.apache.commons.lang.StringUtils;
import org.csource.fastdfs.FileInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private Logger logger= LoggerFactory.getLogger(FileController.class);
    @PostMapping("/upload")
    public Result uploadFile(List<MultipartFile> file){
        try{
            List<String> urlList=new ArrayList<>();
            //判断文件是否存在
            if (file == null||file.size()<1){
                throw new RuntimeException("文件不存在");
            }
            for (MultipartFile multipartFile : file) {
                //获取文件的完整名称
                String originalFilename = multipartFile.getOriginalFilename();
                if (StringUtils.isEmpty(originalFilename)){
                    throw new RuntimeException("文件不存在");
                }

                //获取文件的扩展名称  abc.jpg   jpg
                String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

                //获取文件内容
                byte[] content = multipartFile.getBytes();

                //创建文件上传的封装实体类
                FastDFSFile fastDFSFile = new FastDFSFile(originalFilename,content,extName);

                //基于工具类进行文件上传,并接受返回参数  String[]
                String[] uploadResult = FastDFSClient.upload(fastDFSFile);

                //封装返回结果
                String url = FastDFSClient.getTrackerUrl()+uploadResult[0]+"/"+uploadResult[1];
                urlList.add(url);
            }

            return new Result(true, StatusCode.OK,"文件上传成功",urlList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"文件上传失败");
    }
    @DeleteMapping("/delete")
    public Result deleteFile(String groupName, String remoteFileName){
        try{
            if(StringUtils.isBlank(groupName)||StringUtils.isBlank(remoteFileName)){
                return new Result(false, StatusCode.ERROR,"参数不正确");
            }
            logger.info("删除文件groupName:{},remoteFileName:{}",groupName,remoteFileName);
           FastDFSClient.deleteFile(groupName,remoteFileName);
            return new Result(true, StatusCode.OK,"文件删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"文件删除失败");
    }

    @GetMapping ("/queryFile")
    public Result queryFile(String groupName, String remoteFileName){
        try{
            if(StringUtils.isBlank(groupName)||StringUtils.isBlank(remoteFileName)){
                return new Result(false, StatusCode.ERROR,"参数不正确");
            }
            FileInfo fileInfo=FastDFSClient.queryFile(groupName,remoteFileName);
            return new Result(true, StatusCode.OK,"查询成功",fileInfo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Result(false, StatusCode.ERROR,"查询失败");
    }
}
