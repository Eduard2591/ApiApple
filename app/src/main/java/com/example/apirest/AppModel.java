package com.example.apirest;

public class AppModel {

    private String name;
    private String image;
    private String summary;
    private String rights;
    private String category;

    public AppModel(){

    }

    public AppModel (String name, String summary, String category) {
        this.name = name;
        this.summary = summary;
        this.category = category;
    }

    public AppModel(String name, String image, String summary, String rights) {
        this.name = name;
        this.image = image;
        this.summary = summary;
        this.rights = rights;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(String rights) {
        this.rights = rights;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
