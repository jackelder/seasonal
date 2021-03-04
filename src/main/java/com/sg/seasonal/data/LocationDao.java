package com.sg.seasonal.data;

import com.sg.seasonal.entities.Location;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface LocationDao extends JpaRepository<Location, String>{
    
    @Override
    @Query(
            value="SELECT * FROM Location ORDER BY name",
            nativeQuery=true)
    public List<Location> findAll();
    
}
