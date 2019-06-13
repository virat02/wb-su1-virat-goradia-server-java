package com.example.wbsu1viratgoradiaserverjava.repository;

import com.example.wbsu1viratgoradiaserverjava.models.Lesson;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TopicRepository extends CrudRepository<Topic, Integer> {

    @Query("SELECT topic FROM Topic topic WHERE topic.lesson=:lesson")
    Iterable<Topic> findTopicsForLesson(
            @Param("lesson") Lesson lesson);
}
