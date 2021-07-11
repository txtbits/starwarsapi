package com.txtbits.starwarsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "STARSHIP")
public class Starship implements Serializable {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    public String model;

    @JsonProperty(value = "starship_class")
    public String starshipClass;

    public String manufacturer;

    @JsonProperty(value = "cost_in_credits")
    public String costInCredits;

    public String length;

    public String crew;

    public String passengers;

    @JsonProperty(value = "max_atmosphering_speed")
    public String maxAtmospheringSpeed;

    @JsonProperty(value = "hyperdrive_rating")
    public String hyperdriveRating;

    public String MGLT;

    @JsonProperty(value = "cargo_capacity")
    public String cargoCapacity;

    public String consumables;

    //@JsonProperty(value = "films_urls")
    //public List<Film> filmsUrls;

    //@JsonProperty(value = "pilots_urls")
    //public List<String> pilotsUrls;

    public String url;

    public Date created;

    public Date edited;

}
