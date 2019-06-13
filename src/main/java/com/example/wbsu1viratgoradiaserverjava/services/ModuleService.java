package com.example.wbsu1viratgoradiaserverjava.services;

import com.example.wbsu1viratgoradiaserverjava.models.Course;
import com.example.wbsu1viratgoradiaserverjava.models.Module;
import com.example.wbsu1viratgoradiaserverjava.repository.CourseRepository;
import com.example.wbsu1viratgoradiaserverjava.repository.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    CourseRepository courseRepository;

    @GetMapping("/api/courses/{cid}/modules")
    public List<Module> findAllModules(@PathVariable("cid") int cid){
        Optional<Course> course = courseRepository.findById(cid);
        if(course.isPresent()) {
            return (List<Module>) moduleRepository.findModulesForCourse(course.get());
        }
        return new ArrayList<>();
    }

    @GetMapping("/api/modules/{mid}")
    public Optional<Module> findModuleById(@PathVariable("mid") int mid) {
        return moduleRepository.findById(mid);
    }

    @PostMapping("/api/courses/{cid}/module")
    public Module addModule(@PathVariable("cid") int cid,
                            @RequestBody Module m) {
        Optional<Course> c = courseRepository.findById(cid);
        if(c.isPresent()) {
            List<Module> modules = c.get().getModules();
            modules.add(m);
            c.get().setModules(modules);

            m.setCourse(c.get());
            moduleRepository.save(m);

            return m;
        }
        return null;
    }

    @PutMapping("/api/modules/{mid}")
    public void updateModule(@PathVariable("mid") int mid,
                             @RequestBody Module newModule) {
        Optional<Module> m  = moduleRepository.findById(mid);
        if(m.isPresent()) {
            m.get().setLessons(newModule.getLessons());
            m.get().setTitle(newModule.getTitle());
            moduleRepository.save(m.get());
        }
    }

    @DeleteMapping("/api/modules/{mid}")
    public void deleteModule(@PathVariable("mid") int mid) {
        moduleRepository.deleteById(mid);
    }
}
