package com.sdwl.video.service;

import com.sdwl.video.exception.BaseException;
import com.sdwl.video.model.News;
import com.sdwl.video.util.response.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Creaed by fj on 2018/10/29
 */
public interface INewsService {
     List<News> getAllNews(Integer pageNo, Integer pageSize, String title);


    Integer updateNews(News news);

    Integer deleteNews(Integer id);

    Page<News> getNewsByType(Integer pageNo, Integer pageSize, Integer type, boolean isPush) throws BaseException;

    News getNewsDetail(Integer id);

    Integer addNews(News news) throws Exception;

    Object uploadFiles(MultipartFile url, HttpServletRequest request) throws Exception;
}
