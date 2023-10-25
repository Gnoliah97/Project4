package com.sem4project.sem4.mapper;

import com.sem4project.sem4.dto.dtomodel.CommentDto;
import com.sem4project.sem4.entity.Comment;

public class CommentMapper implements BaseMapper<Comment, CommentDto>{
    @Override
    public CommentDto toDto(Comment comment) {
        return CommentDto.builder()
                .content(comment.getContent())
                .build();
    }

    @Override
    public Comment toEntity(CommentDto commentDto) {
        return Comment.builder()
                .content(commentDto.getContent())
                .build();
    }

    @Override
    public void transferToDto(Comment comment, CommentDto commentDto) {

    }

    @Override
    public void transferToEntity(CommentDto commentDto, Comment comment) {

    }
}
