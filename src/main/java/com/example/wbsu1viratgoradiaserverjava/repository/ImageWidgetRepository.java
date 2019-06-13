package com.example.wbsu1viratgoradiaserverjava.repository;

import com.example.wbsu1viratgoradiaserverjava.models.ImageWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ImageWidgetRepository extends CrudRepository<ImageWidget, Integer> {

    @Query("SELECT imw from ImageWidget imw where imw.topic=:topic")
    Iterable<ImageWidget> findImageWidgetsForTopic(
            @Param("topic") Topic topic);
}
