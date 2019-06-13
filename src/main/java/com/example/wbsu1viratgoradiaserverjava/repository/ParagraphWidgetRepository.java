package com.example.wbsu1viratgoradiaserverjava.repository;

import com.example.wbsu1viratgoradiaserverjava.models.ParagraphWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ParagraphWidgetRepository extends CrudRepository<ParagraphWidget, Integer> {

    @Query("SELECT pw from ParagraphWidget pw where pw.topic=:topic")
    Iterable<ParagraphWidget> findParagraphWidgetsForTopic(
            @Param("topic") Topic topic);
}
