package com.webApp.mainPack;

public class Option {
    private String name;
    private String value;
    private Boolean isSelected;

    public Option(String name, String value, Boolean isSelected) {
        this.name = name;
        this.value = value;
        this.isSelected = isSelected;
    }

    public Option() {
        this("defName_1", "someTextOption_1", false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }
}
