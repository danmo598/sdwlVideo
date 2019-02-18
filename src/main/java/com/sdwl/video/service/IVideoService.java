package com.sdwl.video.service;

import com.sdwl.video.exception.BaseException;
import com.sdwl.video.model.Video;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; /**
 * Creaed by fj on 2019/2/18
 */
public interface IVideoService {
    void uploadVideo(MultipartFile fileName, HttpServletRequest request, HttpServletResponse response, Video video) throws BaseException;
}
