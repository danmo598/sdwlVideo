package com.sdwl.video.util.upload;


import com.sdwl.video.exception.FileTypeException;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 上传工具类
 * Created by fj
 */
@Component
public class UploadUtil {

    Logger logger = LoggerFactory.getLogger(UploadUtil.class);


    @Value("${image.filePath}")
    private String filePath;


    @Value("${image.fileType}")
    private String fileType;

    @Value("${image.fileHeader}")
    private String fileHeader;


    @Value("${image.url}")
    private String url;



    /**
     * 图片处理
     *
     * @return
     */
    public String handleImg(MultipartFile file, HttpServletRequest request) throws Exception {
        boolean result = checkFileType(file);
        if (!result) {
            throw new FileTypeException();
        }
        String fileName = getFileName(file);
        logger.info("fileName{}",fileName);
        saveFile(filePath + fileName, file);
        String urlPath = getUrlPath(fileName, request);
        return urlPath;
    }



    /**
     * zip处理
     * 带日期
     * @return
     */
    public String handleZip(MultipartFile file, HttpServletRequest request, String date) throws Exception {
        boolean result = checkFileType(file);
        if (!result) {
            throw new FileTypeException();
        }
        String fileName = getFileName(file);
        File f = new File(filePath + date);
        if (!f.exists()) {
            f.mkdirs();
        }
        saveFile(filePath + date + File.separator + fileName, file);
        String urlPath = getUrlPath(fileName, request, date);
        return urlPath;
    }

    /**
     * 获取到url路径
     *
     * @return
     */
    public String getUrlPath(String fileName, HttpServletRequest request, String date) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getScheme()).append("://").append(url).append("/highway/upload/").append(date).append("/").append(fileName);
        return sb.toString();
    }



    /**
     * 生成文件名称
     *
     * @param headImg
     * @return
     */
    public String getFileName(MultipartFile headImg) {
        // 获取图片的文件名
        String fileName = headImg.getOriginalFilename();
        // 获取图片的扩展名
        String originName = fileName.split("\\.")[0];
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 新的图片文件名 = 获取时间戳+"."图片扩展名
        StringBuilder sb = new StringBuilder();
        sb.append(originName).append(System.currentTimeMillis()).append(RandomStringUtils.randomNumeric(6)).append(".").append(extensionName);
        return sb.toString();
    }


    /**
     * 保存文件
     *
     * @return
     */
    public void saveFile(String fileName, MultipartFile file) throws Exception {
        logger.info("fileName{}",fileName);
        file.transferTo(new File(fileName));
    }


    /**
     * 获取到url路径
     *
     * @return
     */
    public String getUrlPath(String fileName, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.getScheme()).append("://").append(url).append("/sdwlvideo/upload/").append(fileName);
        return sb.toString();
    }


    /**
     * 获取到zip url路径
     *
     * @return
     */
    public String getZipUrlPath(String fileName, HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMdd");
        String date = df.format(new Date());
        sb.append(request.getScheme()).append("://").append(url).append("/video-chat/upload/").append(date + "/").append(fileName);
        return sb.toString();
    }



    /**
     * 根据文件的输入流获取文件头信息
     *
     * @return 文件头信息
     */
    public String getFileHeader(InputStream is) {
        byte[] b = new byte[4];
        if (is != null) {
            try {
                is.read(b, 0, b.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytesToHexString(b);
    }


    /**
     * 将要读取文件头信息的文件的byte数组转换成string类型表示
     *
     * @param src 要读取文件头信息的文件的byte数组
     * @return 文件头信息
     */
    private String bytesToHexString(byte[] src) {
        StringBuilder builder = new StringBuilder();
        if (src == null || src.length <= 0) {
            return null;
        }
        String hv;
        for (int i = 0; i < src.length; i++) {
            // 以十六进制（基数 16）无符号整数形式返回一个整数参数的字符串表示形式，并转换为大写
            hv = Integer.toHexString(src[i] & 0xFF).toUpperCase();
            if (hv.length() < 2) {
                builder.append(0);
            }
            builder.append(hv);
        }
        return builder.toString();
    }


    /**
     * 判断上传的文件是否合法
     * （一）、第一：检查文件的扩展名，
     * (二）、 第二：检查文件的MIME类型 。
     *
     * @return boolean
     */
    public boolean checkFileType(MultipartFile file) throws Exception {
        boolean flag = false;//为真表示符合上传条件，为假表标不符合
        String fileName = file.getOriginalFilename();
        String extensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        String[] split = fileHeader.split(",");
        /**统一转换为小写**/
        extensionName = extensionName.toLowerCase();
        if (extensionName.endsWith("zip")) {
            return true;
        }
        if (fileType.indexOf(extensionName) != -1) {
            String header = getFileHeader(file.getInputStream());
            logger.error("header,{}",header);
            for (int i = 0; i < split.length; i++) {
                if (header.indexOf(split[i]) != -1) {
                    flag = true;
                }
            }
        }
        return flag;
    }


    /**
     * 该方法主要使用正则表达式来判断字符串中是否包含字母
     */
    public static boolean judgeContainsStr(String cardNum) {
        String regex=".*[a-zA-Z]+.*";
        Matcher m= Pattern.compile(regex).matcher(cardNum);
        return m.matches();
    }
/*    public static void main(String [] args){

    }*/
}
