package com.example.wbsu1viratgoradiaserverjava.repository;

import com.example.wbsu1viratgoradiaserverjava.models.ListWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ListWidgetRepository extends CrudRepository<ListWidget,Integer> {

    @Query("SELECT lsw from ListWidget lsw where lsw.topic=:topic")
    Iterable<ListWidget> findListWidgetsForTopic(
            @Param("topic") Topic topic);
}
