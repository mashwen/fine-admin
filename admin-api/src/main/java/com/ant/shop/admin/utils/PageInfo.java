package com.ant.shop.admin.utils;

public class PageInfo {
    private int page;
    private int prevPage;
    private int nextPage;
    private int totalCount;
    private int countPerPage;
    private int totalPage;

    public PageInfo() {
    }

    public PageInfo(int page, int prevPage, int nextPage, int totalCount, int countPerPage, int totalPage) {
        this.page = page;
        this.prevPage = prevPage;
        this.nextPage = nextPage;
        this.totalCount = totalCount;
        this.countPerPage = countPerPage;
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

}
