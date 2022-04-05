package ru.tsu.hits.springdb1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.springdb1.dto.CommentDto;
import ru.tsu.hits.springdb1.dto.CreateUpdateComment;
import ru.tsu.hits.springdb1.dto.CreateUpdateProjectDto;
import ru.tsu.hits.springdb1.dto.ProjectDto;
import ru.tsu.hits.springdb1.dto.converter.CommentDtoConverter;
import ru.tsu.hits.springdb1.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.springdb1.entity.CommentEntity;
import ru.tsu.hits.springdb1.entity.ProjectEntity;
import ru.tsu.hits.springdb1.exception.CommentNotFoundException;
import ru.tsu.hits.springdb1.exception.ProjectNotFoundException;
import ru.tsu.hits.springdb1.repository.CommentRepository;
import ru.tsu.hits.springdb1.repository.ProjectRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CommentDto save(CreateUpdateComment createUpdateComment) {
        CommentEntity commentEntity = CommentDtoConverter.converterDtoToEntity(createUpdateComment);
        commentEntity = commentRepository.save(commentEntity);
        return CommentDtoConverter.converterEntityToDto(commentEntity);

    }
    @Transactional(readOnly = true)
    public CommentEntity getCommentEntityById(String id){
        return commentRepository.findById(id)
                .orElseThrow(()->new CommentNotFoundException("Комментарий с id" + id + " не найден"));

    }

    @Transactional(readOnly = true)
    public CommentDto getCommentDtoById(String id) {
        CommentEntity commentEntity=getCommentEntityById(id);
        return CommentDtoConverter.converterEntityToDto(commentEntity);
    }
}