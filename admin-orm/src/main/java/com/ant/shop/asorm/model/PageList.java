package com.ant.shop.asorm.model;

import lombok.Data;

import java.util.List;

@Data
public class PageList<T> {
    List<T> list;
    Page pagination;
}
