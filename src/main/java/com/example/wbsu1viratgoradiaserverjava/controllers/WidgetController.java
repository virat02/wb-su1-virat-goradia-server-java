package com.example.wbsu1viratgoradiaserverjava.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.wbsu1viratgoradiaserverjava.models.Widget;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WidgetController {

    private List<Widget> widgets = new ArrayList<>();

    @GetMapping("/api/widgets")
    public List<Widget> findAllWidgets() {
        return widgets;
    }

    @PostMapping(path= "/api/widgets", consumes = "application/json",
            produces = "application/json")
    public List<Widget> createWidget(@RequestBody Widget widget) {
        widgets.add(widget);
        return widgets;
    }

    @GetMapping("/api/widgets/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") Integer id) {
        for(Widget widget: widgets) {
            if(id == widget.getId())
                return widget;
        }
        return null;
    }

    @PutMapping(path= "/api/widgets/{widgetId}", consumes = "application/json",
            produces = "application/json")
    public List<Widget> updateWidget(@PathVariable("widgetId") Integer id, @RequestBody Widget widget) {

        for(Widget w : widgets) {
            if (w.getId() == id) {
                w.setName(widget.getName());
                w.setText(widget.getText());
                break;
            }
        }

        return widgets;

    }

    @DeleteMapping("/api/widgets/{widgetId}")
    public List<Widget> deleteWidget(@PathVariable("widgetId") Integer id) {

        for(Iterator<Widget> w = widgets.iterator(); w.hasNext();) {
            Widget widget = w.next();

            if(id == widget.getId())
                w.remove();
        }

        return widgets;
    }
}
