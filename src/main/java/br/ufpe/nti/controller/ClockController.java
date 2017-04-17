package br.ufpe.nti.controller;

import java.time.LocalTime;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufpe.nti.controller.repository.ClockHistoryRepository;
import br.ufpe.nti.model.Clock;

@RestController
public class ClockController {
	
	@Autowired
	private ClockHistoryRepository clockDB;
	
	@GetMapping(value = "/clock")
	public Clock GetAngleRequest() {
		return new Clock();
		
	}
	
	@PostMapping(value = "/clock")
	public Clock PostAngleRequest(@RequestBody String body) {
		Clock clock = new Clock();
		LocalTime lt = LocalTime.MIDNIGHT;
		
		try {
			JSONObject jsonBody = new JSONObject(body);
			lt = LocalTime.parse(jsonBody.getString("time"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		clock.setTime(lt);
		
		return clockDB.save(clock);
	}
	
	@GetMapping(value = "/clockhistory")
	public List<Clock> GetHistory() {
		return clockDB.listAll();
	}
	
	@GetMapping(value = "/clockhistory/{id}")
	public Clock GetClockById(@PathVariable int id) {
		return clockDB.clockById(id);
	}
	
}
