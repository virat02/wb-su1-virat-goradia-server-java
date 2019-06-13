package com.example.wbsu1viratgoradiaserverjava.repository;

import com.example.wbsu1viratgoradiaserverjava.models.LinkWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LinkWidgetRepository extends CrudRepository<LinkWidget, Integer> {

    @Query("SELECT lw from LinkWidget lw where lw.topic=:topic")
    Iterable<LinkWidget> findLinkWidgetsForTopic(
            @Param("topic") Topic topic);
}
