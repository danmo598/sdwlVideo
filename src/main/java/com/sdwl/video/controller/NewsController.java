package com.sdwl.video.controller;

import com.github.pagehelper.PageInfo;
import com.sdwl.video.exception.BaseException;
import com.sdwl.video.model.News;
import com.sdwl.video.service.INewsService;
import com.sdwl.video.util.response.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Creaed by fj on 2018/10/29
 */

/**
 * 新闻模块
 */
@RestController
@RequestMapping(value = "news")
@Api(value = "新闻相关接口")
@CrossOrigin(origins = "*", maxAge = 3600)
public class NewsController {
    Logger logger = LoggerFactory.getLogger(NewsController.class);

    @Autowired
    INewsService newsService;

    /**
     * 分页获取新闻
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value="/getAllNews")
    @ApiOperation(value="(后台)分页获取新闻")
    public PageInfo<News> getAllNews(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "title",required = false)String title){
        List<News> newsList =  newsService.getAllNews(pageNo,pageSize,title);
        return new PageInfo<>(newsList);
    }


    /**
     * 添加新闻
     * @param news
     * @return
     */
    @PostMapping(value = "/addNews")
    @ApiOperation(value="(后台)添加一条新闻")
    public Integer addNews(@RequestBody News news) throws Exception {
        return  newsService.addNews(news);
    }


    /**
     * 图片上传
     * @return
     */
    @PostMapping(value = "/uploadFiles",produces="application/json")
    @ApiOperation(value="(后台)图片上传")
    public Object uploadFiles(MultipartFile url, HttpServletRequest request) throws Exception {
        logger.info("上传文件{}",url);
        Object obj =  newsService.uploadFiles(url,request);
        logger.info("obj{}",obj);
        return obj;
    }

    /**
     * 更新新闻
     * @param news
     * @return
     */
    @PostMapping(value = "/updateUser")
    @ApiOperation(value="(后台)更新一条新闻")
    public Integer updateUser(@RequestBody News news){
        return  newsService.updateNews(news);
    }

    /**
     * 删除新闻
     * @param id
     * @return
     */
    @PostMapping(value = "/deleteNews")
    @ApiOperation(value="(后台)删除一条新闻")
    public  Integer deleteNews(@RequestParam("id") Integer id){
        return newsService.deleteNews(id);
    }

    /**
     * 分页获取新闻
     * @param pageNo
     * @param pageSize
     * @return
     */
    @GetMapping(value="getNewsByType")
    @ApiOperation(value="(接口)根据type和是否发布分页查询新闻，0动态通知 1集团新闻 2媒体报道")
    public Page<News> getNewsByType(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize,
                                    @RequestParam("type")  Integer type, @RequestParam("isPush") boolean isPush) throws BaseException {
        return  newsService.getNewsByType(pageNo,pageSize,type,isPush);
    }


    /**
     * 获取新闻详情
     * @param id
     * @return
     */
    @GetMapping(value = "getNewsDetail")
    @ApiOperation(value="(接口)根据id查询新闻详情")
    public News getNewsDetail(@RequestParam("id") Integer id){
        return newsService.getNewsDetail(id);
    }
}
