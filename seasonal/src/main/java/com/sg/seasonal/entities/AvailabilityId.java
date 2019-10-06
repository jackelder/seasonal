package com.sg.seasonal.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author jackelder
 */
public class AvailabilityId implements Serializable {
    
    private String locationId;
    
    private int seasonId;
    
    private int ingredientId;

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

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.locationId);
        hash = 97 * hash + this.seasonId;
        hash = 97 * hash + this.ingredientId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AvailabilityId other = (AvailabilityId) obj;
        if (this.seasonId != other.seasonId) {
            return false;
        }
        if (this.ingredientId != other.ingredientId) {
            return false;
        }
        if (!Objects.equals(this.locationId, other.locationId)) {
            return false;
        }
        return true;
    } 
}
