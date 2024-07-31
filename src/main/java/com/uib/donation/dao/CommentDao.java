package com.uib.donation.dao;

import lombok.Data;

@Data
public class CommentDao {
    private String content;
    private Long userId;
    private Long postId;
}
