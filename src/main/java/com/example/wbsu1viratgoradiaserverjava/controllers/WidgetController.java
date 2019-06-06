package com.example.wbsu1viratgoradiaserverjava.controllers;

import java.util.List;
import java.util.Optional;

import com.example.wbsu1viratgoradiaserverjava.models.Widget;
import com.example.wbsu1viratgoradiaserverjava.repository.WidgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class WidgetController {

    @Autowired
    WidgetRepository widgetRepository;

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAll();
    }

    @GetMapping("/api/widgets/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") Integer widgetId) {
        Optional<Widget> widget = widgetRepository.findById(widgetId);
        return widget.get();
    }

    @PostMapping(path= "/api/widgets", consumes = "application/json",
            produces = "application/json")
    public List<Widget> createWidget(@RequestBody Widget widget) {
        widgetRepository.save(widget);
        return findAllWidgets();
    }


    @PutMapping(path= "/api/widgets/{widgetId}", consumes = "application/json",
            produces = "application/json")
    public List<Widget> updateWidget(@PathVariable("widgetId") Integer id, @RequestBody Widget newWidget) {

        //Get the widget to update
        Optional<Widget> optional = widgetRepository.findById(id);
        Widget widget = optional.get();

        //Update the widget
        widget.setOrdr(newWidget.getOrdr());
        widget.setUrl(newWidget.getUrl());
        widget.setText(newWidget.getText());
        widget.setName(newWidget.getName());
        widget.setType(newWidget.getType());
        widget.setSize(newWidget.getSize());
        widget.setListType(newWidget.getListType());

        //Save the updated widget to the database
        widgetRepository.save(widget);
        return findAllWidgets();
    }

    @DeleteMapping("/api/widgets/{widgetId}")
    public List<Widget> deleteWidget(@PathVariable("widgetId") Integer id) {

        widgetRepository.deleteById(id);
        return findAllWidgets();
    }
}
