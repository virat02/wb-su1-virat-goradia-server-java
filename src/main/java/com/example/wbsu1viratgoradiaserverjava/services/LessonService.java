package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.Lesson;
import com.example.wbsu1viratgoradiaserverjava.models.Module;
import com.example.wbsu1viratgoradiaserverjava.repository.LessonRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class LessonService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    LessonRepository lessonRepository;

    @GetMapping("/api/modules/{mid}/lessons")
    public List<Lesson> findAllLessons(@PathVariable("mid") int mid){
        Optional<Module> mod = moduleRepository.findById(mid);
        if(mod.isPresent()) {
            return (List<Lesson>) lessonRepository.findLessonsForModule(mod.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/lessons/{lid}")
    public Optional<Lesson> findLessonById(@PathVariable("lid") int lid) {
        return lessonRepository.findById(lid);
    }

    @PostMapping("/api/modules/{mid}/lesson")
    public Lesson addLesson(@PathVariable("mid") int mid,
                            @RequestBody Lesson l) {
        Optional<Module> m = moduleRepository.findById(mid);
        if(m.isPresent()) {
            List<Lesson> lessons = m.get().getLessons();
            lessons.add(l);
            m.get().setLessons(lessons);

            l.setModule(m.get());
            lessonRepository.save(l);
            return l;
        }
        return null;
    }

    @PutMapping("/api/lessons/{lid}")
    public void updateLesson(@PathVariable("lid") int lid,
                             @RequestBody Lesson newLesson) {
        Optional<Lesson> l = lessonRepository.findById(lid);
        if(l.isPresent()) {
            l.get().setTitle(newLesson.getTitle());
            l.get().setTopics(newLesson.getTopics());
            lessonRepository.save(l.get());
        }
    }

    @DeleteMapping("/api/lessons/{lid}")
    public void deleteLesson(@PathVariable("lid") int lid) {
        lessonRepository.deleteById(lid);
    }
}
