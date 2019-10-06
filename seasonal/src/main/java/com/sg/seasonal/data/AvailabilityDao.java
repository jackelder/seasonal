package com.sg.seasonal.data;

import com.sg.seasonal.entities.Availability;
import com.sg.seasonal.entities.AvailabilityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface AvailabilityDao extends JpaRepository<Availability, AvailabilityId>{
    
}
