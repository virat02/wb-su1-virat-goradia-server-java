package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.ParagraphWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import com.example.wbsu1viratgoradiaserverjava.models.Widget;
import com.example.wbsu1viratgoradiaserverjava.repository.ParagraphWidgetRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class ParagraphWidgetService {

    @Autowired
    ParagraphWidgetRepository paragraphWidgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/topics/{tid}/para/widgets")
    public List<ParagraphWidget> findAllParaWidgets(@PathVariable("tid") int tid){
        Optional<Topic> top = topicRepository.findById(tid);
        if(top.isPresent()) {
            return (List<ParagraphWidget>) paragraphWidgetRepository.findParagraphWidgetsForTopic(top.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/para/widgets/{wid}")
    public Optional<ParagraphWidget> findParaWidgetById(@PathVariable("wid") int wid) {
        return paragraphWidgetRepository.findById(wid);
    }

    @PostMapping("/api/topics/{tid}/para/widget")
    public Widget addParaWidget(@PathVariable("tid") int tid,
                                @RequestBody ParagraphWidget pw) {
        Optional<Topic> t = topicRepository.findById(tid);
        if(t.isPresent()) {
            List<Widget> widgets = t.get().getWidgets();
            widgets.add(pw);
            t.get().setWidgets(widgets);

            pw.setTopic(t.get());
            paragraphWidgetRepository.save(pw);
            return pw;
        }
        return null;
    }

    @PutMapping("/api/para/widgets/{wid}")
    public void updateParaWidget(@PathVariable("wid") int wid,
                                 @RequestBody ParagraphWidget pw) {
        Optional<ParagraphWidget> w = paragraphWidgetRepository.findById(wid);
        if(w.isPresent()) {
            w.get().setText(pw.getText());
            w.get().setTopic(pw.getTopic());
            paragraphWidgetRepository.save(w.get());
        }
    }

    @DeleteMapping("/api/para/widgets/{wid}")
    public void deleteParaWidget(@PathVariable("wid") int wid) {
        paragraphWidgetRepository.deleteById(wid);
    }
}
