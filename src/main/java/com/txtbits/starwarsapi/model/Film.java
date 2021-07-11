package com.txtbits.starwarsapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "FILM")
public class Film implements Serializable {

    @Id
    @GeneratedValue
    public Long id;

    public String title;

    @JsonProperty(value = "episode_id")
    public int episodeId;

    @JsonProperty(value = "opening_crawl")
    @Lob
    public String openingCrawl;

    public String director;

    public String producer;

    public String url;

    public Date created;

    public Date edited;

    @JsonProperty(value = "release_date")
    public Date releaseDate;

    //public List<String> speciesUrls;

    //public List<String> starships;

    //public List<String> vehicles;

    //public List<String> planets;

    //@Transient
    //@ManyToMany
    //@JoinTable(
    //        joinColumns = @JoinColumn(name = "film_id"),
    //        inverseJoinColumns = @JoinColumn(name = "people_id"))
    //public List<People> characters =  new ArrayList<>();

}