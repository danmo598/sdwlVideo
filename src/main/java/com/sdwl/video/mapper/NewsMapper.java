package com.sdwl.video.mapper;


import com.sdwl.video.model.News;

public interface NewsMapper extends CommonMapper<News> {
    void updateById(Integer id);
}