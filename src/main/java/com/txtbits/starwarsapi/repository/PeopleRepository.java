
package com.txtbits.starwarsapi.repository;

import com.txtbits.starwarsapi.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<People, Long> {

    /*@Query("SELECT p.name, COUNT(pf.id) AS num, GROUP_CONCAT(f.title) AS films "
            + " FROM People p "
            + " INNER JOIN people_films pf ON p.id = pf.people_id "
            + " INNER JOIN Film f ON pf.film_id = f.id "
            + " GROUP BY pf.id ")
    List<Object[]> counts();*/

}
