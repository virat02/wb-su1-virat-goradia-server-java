package com.example.wbsu1viratgoradiaserverjava.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import com.example.wbsu1viratgoradiaserverjava.models.Widget;
import com.example.wbsu1viratgoradiaserverjava.repository.TopicRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class WidgetService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    WidgetRepository widgetRepository;

    @GetMapping("/api/topics/{tid}/widgets")
    public List<Widget> findAllWidgets(@PathVariable("tid") int tid){
        Optional<Topic> topic = topicRepository.findById(tid);
        if(topic.isPresent()) {
            return (List<Widget>) widgetRepository.findWidgetsForTopic(topic.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/widgets/{wid}")
    public Optional<Widget> findWidgetById(@PathVariable("wid") int wid) {
        return widgetRepository.findById(wid);
    }

    @PostMapping("/api/topics/{tid}/widget")
    public Widget addWidget(@PathVariable("tid") int tid,
                            @RequestBody Widget w) {
        Optional<Topic> t = topicRepository.findById(tid);
        if(t.isPresent()) {
            List<Widget> widgets = t.get().getWidgets();
            widgets.add(w);
            t.get().setWidgets(widgets);

            w.setTopic(t.get());
            widgetRepository.save(w);
            return w;
        }
        return null;
    }

    @PutMapping("/api/widgets/{wid}")
    public void updateWidget(@PathVariable("wid") int wid,
                             @RequestBody Widget newWidget) {
        Optional<Widget> w = widgetRepository.findById(wid);
        if(w.isPresent()) {
            w.get().setType(newWidget.getType());
            w.get().setTopic(newWidget.getTopic());
            w.get().setName(newWidget.getName());
            widgetRepository.save(w.get());
        }
    }

    @DeleteMapping("/api/widgets/{wid}")
    public void deleteWidget(@PathVariable("wid") int wid) {
        widgetRepository.deleteById(wid);
    }
}
