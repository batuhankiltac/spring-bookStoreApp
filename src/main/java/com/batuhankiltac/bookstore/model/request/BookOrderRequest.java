package com.batuhankiltac.bookstore.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BookOrderRequest {
    private List<Integer> bookIdList;
    private String userName;
}