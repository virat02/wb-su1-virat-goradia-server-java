package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.HeadingWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import com.example.wbsu1viratgoradiaserverjava.models.Widget;
import com.example.wbsu1viratgoradiaserverjava.repository.HeadingWidgetRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class HeadingWidgetService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    HeadingWidgetRepository headingWidgetRepository;

    @GetMapping("/api/topics/{tid}/heading/widgets")
    public List<HeadingWidget> findAllHeadingWidgets(@PathVariable("tid") int tid){
        Optional<Topic> topic = topicRepository.findById(tid);
        if(topic.isPresent()) {
            return (List<HeadingWidget>) headingWidgetRepository.findHeadingWidgetsForTopic(topic.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/heading/widgets/{wid}")
    public Optional<HeadingWidget> findHeadingWidgetById(@PathVariable("wid") int wid) {
        return headingWidgetRepository.findById(wid);
    }

    @PostMapping("/api/topics/{tid}/heading/widget")
    public Widget addHeadingWidget(@PathVariable("tid") int tid,
                                   @RequestBody HeadingWidget hw) {
        Optional<Topic> t = topicRepository.findById(tid);
        if(t.isPresent()) {
            List<Widget> widgets = t.get().getWidgets();
            widgets.add(hw);
            t.get().setWidgets(widgets);

            hw.setTopic(t.get());
            headingWidgetRepository.save(hw);
            return hw;
        }
        return null;
    }

    @PutMapping("/api/heading/widgets/{wid}")
    public void updateHeadingWidget(@PathVariable("wid") int wid,
                                    @RequestBody HeadingWidget hw) {
        Optional<HeadingWidget> w = headingWidgetRepository.findById(wid);
        if(w.isPresent()) {
            w.get().setSize(hw.getSize());
            w.get().setText(hw.getText());
            w.get().setTopic(hw.getTopic());

            headingWidgetRepository.save(w.get());
        }
    }

    @DeleteMapping("/api/heading/widgets/{wid}")
    public void deleteHeadingWidget(@PathVariable("wid") int wid) {
        headingWidgetRepository.deleteById(wid);
    }
}
