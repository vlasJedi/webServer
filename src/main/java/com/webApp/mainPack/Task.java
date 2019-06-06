package com.webApp.mainPack;

import java.util.List;

public class Task {
    private String name;
    private List<Option> options;

    public Task(String name, List<Option> options) {
        this.name = name;
        this.options = options;
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
