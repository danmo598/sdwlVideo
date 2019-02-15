package com.sdwl.video.util.response;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页
 */

public class Page<E> implements Serializable {

    /**
     * 总条数
     */

    private Long count;

    /**
     * 当前页
     */


    private Integer pageNo;

    /**
     * 总页数
     */

    private Integer pages;


    /**
     * list 列表
     */

    private List<E> list;




    public Page() {
    }


    public Page(List<E> list) {
        com.github.pagehelper.Page<E> page = (com.github.pagehelper.Page<E>) list;
        this.count = page.getTotal();
        this.pageNo = page.getPageNum();
        this.pages = page.getPages();
        this.list = new ArrayList<E>(page.getResult());
    }


    public Page(Long count, Integer pageNo, Integer pages, List<E> list) {
        this.count = count;
        this.pageNo = pageNo;
        this.pages = pages;
        this.list = list;
    }



    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Page{");
        sb.append("count=").append(count);
        sb.append(", pageNo=").append(pageNo);
        sb.append(", pages=").append(pages);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
