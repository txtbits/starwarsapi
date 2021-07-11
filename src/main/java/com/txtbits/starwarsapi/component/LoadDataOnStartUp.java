package com.txtbits.starwarsapi.component;

import com.txtbits.starwarsapi.service.LoadService;
import com.txtbits.starwarsapi.util.SwapiReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class LoadDataOnStartUp {

    private final Logger log = LoggerFactory.getLogger(LoadDataOnStartUp.class);

    @Autowired
    private LoadService loadService;

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        log.info("Start loading entities from swapi");
        loadService.loadEntitiesFromSwapi();
        log.info("End loading entities from swapi");
    }
}
