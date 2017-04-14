package br.ufpe.nti.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.ufpe.nti.model.Clock;

@RestController
public class ClockController {
	
	private double angle;
	
	@RequestMapping(path = "/clock", method = RequestMethod.GET)
	public ResponseEntity<String> AngleRequest() {
		Clock clock = new Clock();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		DateFormat createdAtFormatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		
		setAngle(computeAngle(clock.getTime()));
		
		JSONObject body = new JSONObject();
		try {
			body.put("id", "null");
			body.put("time", clock.getTime().getHour() + ":" + clock.getTime().getMinute());
			body.put("createdAt", createdAtFormatter.format(clock.getCreatedAt()));
			body.put("angle", getAngle());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(body.toString(), header, HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param time
	 * @return angle between clock hands
	 */
	private double computeAngle(LocalTime time) {
		double angle = 0.0;
		int hour = time.getHour();
		int minute = time.getMinute();
		
		hour = hour > 12 ? hour - 12 : hour;
		
		double dh = (60.0*hour + minute)/2.0;
		double dm = 6.0*minute;
		
		//angle = Math.abs(0.5*(60*(hour-11)*minute));
		angle = Math.abs(dh - dm);
		angle = angle > 180.0 ? 360.0 - angle : angle;
		
		return angle;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
}
