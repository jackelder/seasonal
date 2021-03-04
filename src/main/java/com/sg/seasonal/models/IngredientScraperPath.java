package com.sg.seasonal.models;


/**
 *
 * @author jackelder
 */
public class IngredientScraperPath {
    
    private String locationId;
    private int seasonId;
    private String path;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(int seasonId) {
        this.seasonId = seasonId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
