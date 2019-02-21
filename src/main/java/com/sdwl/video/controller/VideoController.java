package com.sdwl.video.controller;

import com.github.pagehelper.PageInfo;
import com.sdwl.video.exception.BaseException;
import com.sdwl.video.model.News;
import com.sdwl.video.model.Video;
import com.sdwl.video.service.IVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    public String  uploadVideo(@RequestParam MultipartFile fileName) throws BaseException {
        return  videoService.uploadVideo(fileName);
    }


    @PostMapping(value = "insertVideo")
    @ApiOperation(value="插入视频")
    public void  insertVideo(@RequestBody Video video) throws BaseException {
          videoService.insertVideo(video);
    }

    /**
     * 分页获取视频
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value="/getAllVideos")
    @ApiOperation(value="(后台)分页获取视频")
    public PageInfo<Video> getAllVideos(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "title",required = false)String title){
        List<Video> videoList =  videoService.getAllVideos(pageNo,pageSize,title);
        return new PageInfo<>(videoList);
    }

    /**
     * 更新视频
     * @param id
     * @return
     */
    @PostMapping(value = "/updateVideo")
    @ApiOperation(value="(更新一条视频")
    public Integer updateVideo( @RequestBody Video video) throws BaseException {
        return  videoService.updateVideo(video);
    }

    /**
     * 删除视频
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteVideo")
    @ApiOperation(value="(删除一条视频")
    public  Integer deleteVideo(@RequestParam("id") Integer id){
        return videoService.deleteVideo(id);
    }


}
