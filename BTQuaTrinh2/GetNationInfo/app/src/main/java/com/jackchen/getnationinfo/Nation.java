package com.jackchen.getnationinfo;

public class Nation {
    private String name, imageUrl;
    private int population;
    private float area;

    public Nation(String name, int population, String imageUrl, float area) {
        this.name = name;
        this.population = population;
        this.imageUrl = imageUrl;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public float getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }
}
