package com.wang.myblog.utils;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;

import com.wang.myblog.constant.AliyunOssConfigConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**阿里云OSS上传工具类
 */
@Component
public class AliyunOssUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunOssUtil.class);

    /**
     * 上传文件
     */
    public String upLoad(File file) {
        logger.info("------OSS文件上传开始--------" + file.getName());
        String endpoint = AliyunOssConfigConstant.END_POINT;
        System.out.println("获取到的Point为:" + endpoint);
        String accessKeyId = AliyunOssConfigConstant.ACCESSKEY_ID;
        String accessKeySecret = AliyunOssConfigConstant.ACCESSKEY_SECRET;
        String bucketName = AliyunOssConfigConstant.BUCKE_NAME;  //刚才新建的bucket名称
        String fileHost = AliyunOssConfigConstant.FILE_HOST;   //在刚才新建的bucket下面新建一个目录，这就是那个目录的名称
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        // 判断文件
        if (file == null) {
            return null;
        }
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setCacheControl("no-cache");
            metadata.setHeader("Pragma", "no-cache");
            metadata.setContentType(getContentType(file.getName().substring(file.getName().lastIndexOf(".") + 1)));
            metadata.setContentDisposition("inline;filename=" + file.getName());

             //指定上传文件操作时是否覆盖同名Object。
             //不指定x-oss-forbid-overwrite时，默认覆盖同名Object。
             //指定x-oss-forbid-overwrite为false时，表示允许覆盖同名Object。
             //指定x-oss-forbid-overwrite为true时，表示禁止覆盖同名Object，如果同名Object已存在，程序将报错。
            metadata.setHeader("x-oss-forbid-overwrite", "false");

            // 判断容器是否存在,不存在就创建
            if (!client.doesBucketExist(bucketName)) {
                client.createBucket(bucketName);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                client.createBucket(createBucketRequest);
            }
            // 设置文件路径和名称
            //String fileUrl = fileHost + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            String fileUrl = fileHost + "/" + (dateStr + "/"  + file.getName());
            // 上传文件
            PutObjectResult result = client.putObject(new PutObjectRequest(bucketName, fileUrl, file,metadata));
            // 设置权限(公开读)
            client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            if (result != null) {
                logger.info("------OSS文件上传成功------" + "https://mywblog.oss-cn-beijing.aliyuncs.com/" + fileUrl);
            }
        } catch (OSSException oe) {
            logger.error(oe.getMessage());
        } catch (ClientException ce) {
            logger.error(ce.getErrorMessage());
        } finally {
            if (client != null) {
                client.shutdown();
            }
        }
        return "https://" + bucketName + "." + endpoint + "/" + fileHost + "/" + (dateStr + "/"  + file.getName());
    }
    public static String getContentType(String FilenameExtension) {
        if ("bmp".equalsIgnoreCase(FilenameExtension)) {
            return "image/bmp";
        }
        if ("gif".equalsIgnoreCase(FilenameExtension)) {
            return "image/gif";
        }
        if ("jpeg".equalsIgnoreCase(FilenameExtension)
                || "jpg".equalsIgnoreCase(FilenameExtension)
                || "png".equalsIgnoreCase(FilenameExtension)) {
            return "image/jpg";
        }
        if ("pdf".equalsIgnoreCase(FilenameExtension)) {
            return "application/pdf";
        }
        return "application/octet-stream";
    }
}