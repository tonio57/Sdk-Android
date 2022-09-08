package edu.fbansept.Antoine_presentation;

import org.json.JSONException;
import org.json.JSONObject;

public class Product {
    private Integer id;
    private String title;
    private String description;

    public Product(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Product(JSONObject json) {

        try {
            this.id = json.getInt("id");
            this.title = json.getString("title");
            this.description = json.getString("description");
        } catch (JSONException e) {
            e.printStackTrace();
            this.id = 0;
            this.title = "";
            this.description = "";
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
