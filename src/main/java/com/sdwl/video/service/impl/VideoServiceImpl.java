package com.sdwl.video.service.impl;

import com.sdwl.video.exception.BaseException;
import com.sdwl.video.mapper.VideoMapper;
import com.sdwl.video.model.Video;
import com.sdwl.video.service.IVideoService;
import com.sdwl.video.util.DateUtil;
import com.sdwl.video.util.FFMpegUtil;
import com.sdwl.video.util.enums.StatEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;

/**
 * Creaed by fj on 2019/2/18
 */
@Service
public class VideoServiceImpl implements IVideoService {


    @Autowired
    VideoMapper videoMapper;

    @Value("${image.url}")
    private String imageUrl;

    @Value("${image.filePath}")
    private String filePath;

    @Override
    public void uploadVideo(MultipartFile fileUpload, HttpServletRequest request, HttpServletResponse response, Video video) throws BaseException {
        String date = DateUtil.formatDateTime("yyyyMMddhhmmss", new Date());
        String base_topath = filePath+"to/";
        String fileName = fileUpload.getOriginalFilename();
        System.out.println(fileName);
        if(fileUpload.getSize()>52428800){
            throw new BaseException(StatEnum.VIDEO_OVER);
        }if(fileName==""||fileName==null){
            throw new BaseException(StatEnum.VIDEO_EMPTY);
        }else if(!fileName.substring(fileName.lastIndexOf(".")+1).equalsIgnoreCase("flv")&&!fileName.substring(fileName.lastIndexOf(".")+1).equalsIgnoreCase("mp4")&&!fileName.substring(fileName.lastIndexOf(".")+1).equalsIgnoreCase("mov")){
            throw new BaseException(StatEnum.VIDEO_TYPE_ERROR);
        }
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存

        String path=  filePath + fileName + date;
        try {
            fileUpload.transferTo(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* String up_sounce_path = "";
        if (!fileName.split("\\.")[1].toLowerCase().equals("mp4")) {
            System.out.println("转换开始");
            //D:\ffmpeg\bin\ffmpeg
            FFMpegUtil ffm = new FFMpegUtil("/usr/local/ffmpeg-4.1/ffmpeg", targetFile.getPath());
            String newfilename = fileName.split("\\.")[0] + "." + "mp4";
            System.out.println(newfilename);
            up_sounce_path = base_topath + newfilename;
            //如果不是MP4，转换类型为mp4
            ffm.videoTransfer(up_sounce_path);
        } else {
            up_sounce_path = filePath + fileName;
        }
        System.out.println("压缩开始");
        //压缩MP4文件
        String commp_path = base_topath + fileName.split("\\.")[0] +date+"_commp.mp4";
        FFMpegUtil ffm_commp = new FFMpegUtil("/usr/local/ffmpeg-4.1/ffmpeg", up_sounce_path);
        boolean flag = ffm_commp.videoCompress(commp_path);
        if(!flag){
            throw new BaseException(StatEnum.SERVER_ERROR);
        }
        //截取一帧图片
       *//* String grip_path = commp_path.split("\\.")[0] +".jpg";
        FFMpegUtil ffm_grip = new FFMpegUtil("/usr/local/ffmpeg-4.1/ffmpeg", commp_path);
        ffm_grip.videoGripframe(grip_path);
        *//*

        File file = new File(filePath);
        File[] files=file.listFiles();//获取文件列表
        for(int i=0;i<files.length;i++)
        {
            files[i].delete();
        }

        System.out.println(fileName.split("\\.")[0] + "." + "mp4");
        File tofile = new File(base_topath+fileName.split("\\.")[0] + "." + "mp4");*/
        //tofile.delete();
       // String video_url = "http://"+imageUrl+"/sdwlvideo/upload/to/"+fileName.split("\\.")[0] +date+"_commp.mp4";
         String video_url = "http://"+imageUrl+"/sdwlvideo/upload/"+fileName.split("\\.")[0] +date+"_commp.mp4";
        System.out.println(video_url);
        video.setVideoUrl(video_url);
        videoMapper.insertSelective(video);

    }
}
