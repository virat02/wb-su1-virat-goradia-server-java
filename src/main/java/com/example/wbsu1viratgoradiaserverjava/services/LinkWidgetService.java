package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.LinkWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import com.example.wbsu1viratgoradiaserverjava.models.Widget;
import com.example.wbsu1viratgoradiaserverjava.repository.LinkWidgetRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class LinkWidgetService {

    @Autowired
    LinkWidgetRepository linkWidgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/topics/{tid}/link/widgets")
    public List<LinkWidget> findAllLinkWidgets(@PathVariable("tid") int tid){
        Optional<Topic> topic = topicRepository.findById(tid);
        if(topic.isPresent()) {
            return (List<LinkWidget>) linkWidgetRepository.findLinkWidgetsForTopic(topic.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/link/widgets/{wid}")
    public Optional<LinkWidget> findLinkWidgetById(@PathVariable("wid") int wid) {
        return linkWidgetRepository.findById(wid);
    }

    @PostMapping("/api/topics/{tid}/link/widget")
    public Widget addLinkWidget(@PathVariable("tid") int tid,
                              @RequestBody LinkWidget lw) {
        Optional<Topic> t = topicRepository.findById(tid);
        if(t.isPresent()) {
            List<Widget> widgets = t.get().getWidgets();
            widgets.add(lw);
            t.get().setWidgets(widgets);
            //tr.save(t.get());

            lw.setTopic(t.get());
            linkWidgetRepository.save(lw);
            return lw;
        }
        return null;
    }

    @PutMapping("/api/link/widgets/{wid}")
    public void updateLinkWidget(@PathVariable("wid") int wid,
                             @RequestBody LinkWidget lw) {
        Optional<LinkWidget> w = linkWidgetRepository.findById(wid);
        if(w.isPresent()) {
            w.get().setText(lw.getText());
            w.get().setUrl(lw.getUrl());
            w.get().setTopic(lw.getTopic());
            linkWidgetRepository.save(w.get());
        }
    }

    @DeleteMapping("/api/link/widgets/{wid}")
    public void deleteYtWidget(@PathVariable("wid") int wid) {
        linkWidgetRepository.deleteById(wid);
    }
}
