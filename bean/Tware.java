package com.example.zyfypt_no7_406ml.bean;

public class Tware {
    private int id;
    private String name;
    private String thumb;
    private String description;
    private String author;
    private int  userid;
    private String update_time;
    private String pdfattach;

    public String getPdfattach() {
        return pdfattach;
    }

    public void setPdfattach(String pdfattach) {
        this.pdfattach = pdfattach;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
