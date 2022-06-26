package com.kavindu.test;

public class job {
    String Job_Title, Expirience, Username, Phone, Description, Map, Category;

    public String getJob_Title() {
        return Job_Title;
    }

    public void setJob_Title(String job_Title) {
        Job_Title = job_Title;
    }

    public String getExpirience() {
        return Expirience;
    }

    public void setExpirience(String expirience) {
        Expirience = expirience;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getMap() {
        return Map;
    }

    public void setMap(String map) {
        Map = map;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public job(String name, String expirience, String username, String phone, String description, String map) {
        this.Job_Title = name;
        this.Expirience = expirience;
        this.Username = username;
        this.Phone = phone;
        this.Description = description;
        this.Map = map;


    }
}
