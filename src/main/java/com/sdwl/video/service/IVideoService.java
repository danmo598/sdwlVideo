package com.sdwl.video.service;

import com.sdwl.video.exception.BaseException;
import com.sdwl.video.model.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Creaed by fj on 2019/2/18
 */
public interface IVideoService {
    void uploadVideo(MultipartFile fileName, Video video) throws BaseException;

    List<Video> getAllVideos(Integer pageNo, Integer pageSize, String title);

    Integer updateVideo(MultipartFile fileName,Video video) throws BaseException;

    Integer deleteVideo(Integer id);
}
