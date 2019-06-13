package com.example.wbsu1viratgoradiaserverjava.repository;

import com.example.wbsu1viratgoradiaserverjava.models.Lesson;
import com.example.wbsu1viratgoradiaserverjava.models.Module;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LessonRepository extends CrudRepository<Lesson, Integer> {

    @Query("SELECT lesson from Lesson lesson where lesson.module=:module")
    Iterable<Lesson> findLessonsForModule(
            @Param("module") Module module);
}
