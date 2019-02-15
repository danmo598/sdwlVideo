package com.sdwl.video;

import com.sdwl.video.mapper.CommonMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Creaed by fj on 2019/102/15
 */
@SpringBootApplication
@MapperScan(basePackages="com.sdwl.video.mapper", markerInterface = CommonMapper.class)
public class SdwlVideoyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SdwlVideoyApplication.class, args);
    }
}
