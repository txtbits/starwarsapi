package com.txtbits.starwarsapi.repository;

import com.txtbits.starwarsapi.model.Starship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarshipRepository extends JpaRepository<Starship, Long> {

}
