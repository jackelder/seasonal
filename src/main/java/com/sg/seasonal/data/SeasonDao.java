package com.sg.seasonal.data;

import com.sg.seasonal.entities.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jackelder
 */
@Repository
public interface SeasonDao extends JpaRepository<Season, Integer>{
    
}
