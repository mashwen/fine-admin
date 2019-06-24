package com.ant.shop.asorm.model;

import lombok.Data;

@Data
public class Page {
    private int totalCount;
    private int page;
    private int prevPage;
    private int nextPage;
    private int countPerPage;
    private int totalPage;
    private int[] pages;
    private int start;
    private int startPage;
    private int endPage;
}
