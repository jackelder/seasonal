package com.sg.seasonal.entities;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 *
 * @author jackelder
 */
@Entity
@IdClass(AvailabilityId.class)
public class Availability {
    
    @Id
    private String locationId;
    
    @Id
    private int seasonId;
    
    @Id
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
    
    public AvailabilityId getAvailabilityId(){
        AvailabilityId id = new AvailabilityId();
        id.setIngredientId(ingredientId);
        id.setLocationId(locationId);
        id.setSeasonId(seasonId);
        return id;
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
        final Availability other = (Availability) obj;
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
