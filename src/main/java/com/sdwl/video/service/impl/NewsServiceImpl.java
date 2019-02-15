package com.sdwl.video.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.sdwl.video.exception.BaseException;
import com.sdwl.video.mapper.NewsMapper;
import com.sdwl.video.model.News;
import com.sdwl.video.service.INewsService;
import com.sdwl.video.util.enums.StatEnum;
import com.sdwl.video.util.response.Page;
import com.sdwl.video.util.upload.UploadUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Creaed by fj on 2018/10/29
 */
@Service
public class NewsServiceImpl implements INewsService {
    Logger logger = LoggerFactory.getLogger(NewsServiceImpl.class);

    @Autowired
    NewsMapper newsMapper;


    @Autowired
    UploadUtil uploadUtil;


    @Override
    public List<News> getAllNews(Integer pageNo, Integer pageSize,String title) {
        PageHelper.startPage(pageNo,pageSize,"date desc");
        Example example = new Example(News.class);
        if(StringUtils.isNotBlank(title)){
            title = "title like '%" + title + "%' ";
        }
        if(StringUtils.isNotBlank(title)){
            example.createCriteria().andCondition(title);
        }
        return newsMapper.selectByExample(example);
    }

    @Override
    public Integer addNews(News news) {
        return newsMapper.insertSelective(news);
    }

    @Override
    public Object uploadFiles(MultipartFile url, HttpServletRequest request) throws Exception {
        String returnUrl =   uploadUtil.handleImg(url,request);
        logger.info("returnUrl{}",returnUrl);
        JSONObject kkk=new JSONObject();
        kkk.put("url", returnUrl);
        logger.info("returnJson{}",kkk);
        return kkk;
    }

    @Override
    public Integer updateNews(News news) {
        return newsMapper.updateByPrimaryKey(news);
    }

    @Override
    public Integer deleteNews(Integer id) {
        return newsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Page<News> getNewsByType(Integer pageNo, Integer pageSize, Integer type, boolean isPush) throws BaseException {
       PageHelper.startPage(pageNo,pageSize,"date desc");
       Example example = new Example(News.class);
       example.createCriteria().andEqualTo("type",type).andEqualTo("isPush",isPush);
       List<News> newsList = newsMapper.selectByExample(example);
       if(CollectionUtils.isEmpty(newsList)){
           throw new BaseException(StatEnum.STAT_NO_DATA);
       }
       return new Page<>(newsList);
    }

    @Override
    public News getNewsDetail(Integer id) {
        newsMapper.updateById(id);
        return newsMapper.selectByPrimaryKey(id);
    }
}
