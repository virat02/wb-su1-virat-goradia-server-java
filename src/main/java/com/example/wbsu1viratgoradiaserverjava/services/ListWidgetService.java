package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.ListWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import com.example.wbsu1viratgoradiaserverjava.models.Widget;
import com.example.wbsu1viratgoradiaserverjava.repository.ListWidgetRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class ListWidgetService {

    @Autowired
    ListWidgetRepository listWidgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/topics/{tid}/list/widgets")
    public List<ListWidget> findAllListWidgets(@PathVariable("tid") int tid){
        Optional<Topic> topic = topicRepository.findById(tid);
        if(topic.isPresent()) {
            return (List<ListWidget>) listWidgetRepository.findListWidgetsForTopic(topic.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/list/widgets/{wid}")
    public Optional<ListWidget> findListWidgetById(@PathVariable("wid") int wid) {
        return listWidgetRepository.findById(wid);
    }

    @PostMapping("/api/topics/{tid}/list/widget")
    public Widget addListWidget(@PathVariable("tid") int tid,
                                @RequestBody ListWidget lw) {
        Optional<Topic> t = topicRepository.findById(tid);
        if(t.isPresent()) {
            List<Widget> widgets = t.get().getWidgets();
            widgets.add(lw);
            t.get().setWidgets(widgets);

            lw.setTopic(t.get());
            listWidgetRepository.save(lw);
            return lw;
        }
        return null;
    }

    @PutMapping("/api/list/widgets/{wid}")
    public void updateListWidget(@PathVariable("wid") int wid,
                                 @RequestBody ListWidget lw) {
        Optional<ListWidget> w = listWidgetRepository.findById(wid);
        if(w.isPresent()) {
            w.get().setOrdered(lw.getOrdered());

            listWidgetRepository.save(w.get());
        }
    }

    @DeleteMapping("/api/list/widgets/{wid}")
    public void deleteListWidget(@PathVariable("wid") int wid) {
        listWidgetRepository.deleteById(wid);
    }
}
