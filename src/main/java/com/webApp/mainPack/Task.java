package com.webApp.mainPack;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class Task {
    private String name;
    private List<Option> options;

    public Task() {
        this.name = null;
        this.options = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
}
