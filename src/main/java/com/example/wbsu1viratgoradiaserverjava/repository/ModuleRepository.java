package com.example.wbsu1viratgoradiaserverjava.repository;

import com.example.wbsu1viratgoradiaserverjava.models.Course;
import com.example.wbsu1viratgoradiaserverjava.models.Module;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

    @Query("SELECT module from Module module where module.course=:course")
    Iterable<Module> findModulesForCourse(
            @Param("course") Course course);
}
