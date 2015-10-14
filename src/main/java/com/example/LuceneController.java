package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parks")
public class LuceneController {

    @Autowired
    private QueryHandler queryHandler;

    @RequestMapping(produces = "application/json")
    public List getAllParks() {
        ArrayList allParksList = new ArrayList();
        allParksList = (ArrayList) queryHandler.getAllParks();
        return allParksList;
    }

    /*
     * get parks near a coord circle?lat=37.5&lon=-83.0&radius=3 and a radius in
     * degrees
     */
    @RequestMapping(value = "/circle", produces = "application/json")
    public List findParksNear(@RequestParam("lat") float lat, @RequestParam("lon") float lon, @RequestParam("radius") float radius) {
        ArrayList<Map> allParksList = new ArrayList<Map>();
        allParksList = (ArrayList) queryHandler.getParksNear(lat, lon, radius);
        return allParksList;
    }

    /*
     * get parks near a coord sorted by distance
     * nearpoint?lat=37.5&lon=-83.0&number=10 number of results to return
     */
    @RequestMapping(value = "/nearpoint", produces = "application/json")
    public List findNumNear(@RequestParam("lat") float lat, @RequestParam("lon") float lon, @RequestParam("number") int numberResults) {
        ArrayList<Map> allParksList = new ArrayList<Map>();
        allParksList = (ArrayList) queryHandler.getNumNear(lat, lon, numberResults);
        return allParksList;
    }

    /*
     * Now handle a distance and string query - going to return all the results
     * in sorted distance order nearpoint/washington?lat=37.5&lon=-83.0
     */
    @RequestMapping(value = "/nearpoint/{name}", produces = "application/json")
    public List findNameNear(@PathVariable("name") String name, @RequestParam("lat") float lat, @RequestParam("lon") float lon) {
        ArrayList<Map> allParksList = new ArrayList<Map>();
        allParksList = (ArrayList) queryHandler.getNameNear(lat, lon, name);
        return allParksList;
    }

    @RequestMapping(value = "/within", produces = "application/json")
    public List findParksWithin(@RequestParam("lat1") float lat1, @RequestParam("lon1") float lon1, @RequestParam("lat2") float lat2, @RequestParam("lon2") float lon2) {
        List allParksList = queryHandler.getABoxOfPoints(lon2, lon1, lat2, lat1);
        return allParksList;
    }
}