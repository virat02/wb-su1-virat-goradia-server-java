package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.ImageWidget;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import com.example.wbsu1viratgoradiaserverjava.models.Widget;
import com.example.wbsu1viratgoradiaserverjava.repository.ImageWidgetRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class ImageWidgetService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ImageWidgetRepository imageWidgetRepository;

    @GetMapping("/api/topics/{tid}/image/widgets")
    public List<ImageWidget> findAllImageWidgets(@PathVariable("tid") int tid){
        Optional<Topic> top = topicRepository.findById(tid);
        if(top.isPresent()) {
            return (List<ImageWidget>) imageWidgetRepository.findImageWidgetsForTopic(top.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/image/widgets/{wid}")
    public Optional<ImageWidget> findImageWidgetById(@PathVariable("wid") int wid) {
        return imageWidgetRepository.findById(wid);
    }

    @PostMapping("/api/topics/{tid}/image/widget")
    public Widget addImageWidget(@PathVariable("tid") int tid,
                                 @RequestBody ImageWidget iw) {
        Optional<Topic> t = topicRepository.findById(tid);
        if(t.isPresent()) {
            List<Widget> widgets = t.get().getWidgets();
            widgets.add(iw);
            t.get().setWidgets(widgets);
            //tr.save(t.get());

            iw.setTopic(t.get());
            imageWidgetRepository.save(iw);
            return iw;
        }
        return null;
    }

    @PutMapping("/api/image/widgets/{wid}")
    public void updateImageWidget(@PathVariable("wid") int wid,
                                  @RequestBody ImageWidget iw) {
        Optional<ImageWidget> w = imageWidgetRepository.findById(wid);
        if(w.isPresent()) {
            w.get().setSrc(iw.getSrc());
            w.get().setTopic(iw.getTopic());
            imageWidgetRepository.save(w.get());
        }
    }

    @DeleteMapping("/api/image/widgets/{wid}")
    public void deleteImageWidget(@PathVariable("wid") int wid) {
        imageWidgetRepository.deleteById(wid);
    }
}
