package com.example.wbsu1viratgoradiaserverjava.repository;

import com.example.wbsu1viratgoradiaserverjava.models.HeadingWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface HeadingWidgetRepository extends CrudRepository<HeadingWidget, Integer> {

    @Query("SELECT hw from HeadingWidget hw where hw.topic=:topic")
    Iterable<HeadingWidget> findHeadingWidgetsForTopic(
            @Param("topic") Topic topic);
}
