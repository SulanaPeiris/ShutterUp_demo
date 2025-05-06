package com.photography.shutterup.service;

import com.photography.shutterup.model.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(Comment comment);
    List<Comment> getCommentsByPostId(Long postId);
    Comment updateComment(Long commentId, Long userId, String newContent);
    void deleteComment(Long commentId, Long userId);

    class OAuth2UserServiceImpl {
    }
}
