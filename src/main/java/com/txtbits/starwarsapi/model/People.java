package com.txtbits.starwarsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.txtbits.starwarsapi.util.ListToStringConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PEOPLE")
public class People implements Serializable {

    @Id
    @GeneratedValue
    public Long id;

    public String name;

    @JsonProperty(value = "birth_year")
    public String birthYear;

    @JsonProperty(value = "films_urls")
    //@Convert(converter = ListToStringConverter.class)
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "film_id"))
    private List<Film> films;

    public String gender;

    @JsonProperty(value = "hair_color")
    public String hairColor;

    public String height;

    public String homeworld;

    public String mass;

    @JsonProperty(value = "skin_color")
    public String skinColor;

    public String created;

    public String edited;

    public String url;

    //public List<String> species;

    /*@JsonProperty(value = "starships_urls")
    //@Convert(converter = ListToStringConverter.class)
    @ManyToMany
    @JoinTable(
            joinColumns = @JoinColumn(name = "people_id"),
            inverseJoinColumns = @JoinColumn(name = "starship_id"))
    private List<Starship> starships;*/

    //public List<String> vehicles;

}
