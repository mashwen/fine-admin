package com.ant.shop.asorm.model;

import lombok.Data;

import java.util.List;

@Data
public class PageListResp<T> {
    List<T> list;
    PageDTO pagination;
    int count;
}
