package com.sdwl.video.controller;

import com.sdwl.video.exception.BaseException;
import com.sdwl.video.model.Video;
import com.sdwl.video.service.IVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Creaed by fj on 2019/2/18
 */
@RestController
@RequestMapping(value = "users")
@Api("视频相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class VideoController {

    @Autowired
    IVideoService videoService;

    @PostMapping(value = "uploadVideo")
    @ApiOperation(value="上传视频接口")
    public void  uploadVideo(@RequestParam MultipartFile fileName, HttpServletRequest request, HttpServletResponse response,
                             Video video) throws BaseException {
        videoService.uploadVideo(fileName,request,response,video);
    }
}
