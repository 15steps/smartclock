package br.ufpe.nti.controller;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpe.nti.controller.repository.ClockHistoryRepository;
import br.ufpe.nti.model.Clock;
import br.ufpe.nti.service.ClockService;

@RestController
public class ClockController {
	
	@Autowired
	private ClockHistoryRepository db;
	@Autowired
	private ClockService clockService;
	
	@GetMapping(value = "/clock")
	public ResponseEntity<String> GetAngleRequest() {
		Clock clock = new Clock();		
		double angle = clockService.computeAngle(clock.getTime());
		
		return clockService.getClockResponse(clock, angle);
	}
	
	@PostMapping(value = "/clock")
	public ResponseEntity<String> PostAngleRequest(@RequestBody String body) {
		Clock clock = new Clock();
		double angle = 0.0;
		
		LocalTime lt = clockService.jsonToLocalTime(body);
		clock.setTime(lt);
		angle = clockService.computeAngle(lt);
		
		db.save(clock);
		Clock lastClock = db.getLastEntry();
		
		return clockService.getClockResponse(lastClock, angle);
	}
	
}
