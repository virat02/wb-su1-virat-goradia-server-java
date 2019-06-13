package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.Lesson;
import com.example.wbsu1viratgoradiaserverjava.models.Topic;
import com.example.wbsu1viratgoradiaserverjava.repository.LessonRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class TopicService {

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/api/lessons/{lid}/topics")
    public List<Topic> findAllTopics(@PathVariable("lid") int lid){
        Optional<Lesson> l = lessonRepository.findById(lid);
        if(l.isPresent()) {
            return (List<Topic>) topicRepository.findTopicsForLesson(l.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/topics/{tid}")
    public Optional<Topic> findTopicById(@PathVariable("tid") int tid) {
        return topicRepository.findById(tid);
    }

    @PostMapping("/api/lessons/{lid}/topic")
    public Topic addTopic(@PathVariable("lid") int lid,
                          @RequestBody Topic t) {
        Optional<Lesson> l = lessonRepository.findById(lid);
        if(l.isPresent()) {
            List<Topic> topics = l.get().getTopics();
            topics.add(t);
            l.get().setTopics(topics);

            t.setLesson(l.get());
            topicRepository.save(t);
            return t;
        }
        return null;
    }

    @PutMapping("/api/topics/{tid}")
    public void updateTopic(@PathVariable("tid") int tid,
                            @RequestBody Topic newTopic) {
        Optional<Topic> t = topicRepository.findById(tid);
        if(t.isPresent()) {
            t.get().setTitle(newTopic.getTitle());
            t.get().setWidgets(newTopic.getWidgets());
            topicRepository.save(t.get());
        }
    }

    @DeleteMapping("/api/topics/{tid}")
    public void deleteTopic(@PathVariable("tid") int tid) {
        topicRepository.deleteById(tid);
    }
}
