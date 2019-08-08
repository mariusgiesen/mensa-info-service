package org.ict.mensainfoservice.repository;

import org.ict.mensainfoservice.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> getCommentsByCustomUserId(Long customUserId);
    List<Comment> getCommentsByMealId(Long mealId);
}
