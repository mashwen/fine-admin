package com.ant.shop.asorm.model;

import lombok.Data;

@Data
public class PageDTO {
    private Integer page;

    private Integer prevPage;

    private Integer nextPage;

    private Integer totalCount;

    private Integer countPerPage;

    private Integer totalPage;


}
