package com.example.entity.controller;

import com.example.entity.Bus;
import com.example.entity.BusStop;
import com.example.entity.Stop;
import com.example.repository.BusRepository;
import com.example.repository.BusStopRepository;
import com.example.repository.StopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/bus")
public class BusStopController {

    private BusRepository busRepository;

    private StopRepository stopRepository;

    private BusStopRepository busStopRepository;

    public BusStopController(BusRepository busRepository, StopRepository stopRepository, BusStopRepository busStopRepository) {
        this.busRepository = busRepository;
        this.stopRepository = stopRepository;
        this.busStopRepository = busStopRepository;
    }

    @PostMapping
    public ResponseEntity<BusStop> allocateBusRoute(
            @RequestParam long busId,
            @RequestParam long stopId,
            @RequestParam BusStop busStop
    ){
        Bus bus = busRepository.findById(busId).get();
        Stop stop = stopRepository.findById(stopId).get();

        busStop.setbus(bus);
        busStop.setStop(stop);
        BusStop savedEntity = busStopRepository.save(busStop);

        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }
}
