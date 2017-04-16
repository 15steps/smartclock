package br.ufpe.nti.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.ufpe.nti.model.Clock;

@Service
public class ClockService {
	
	/**
	 * @param clock
	 * @param angle
	 * @return complete HTTP response
	 */
	public ResponseEntity<String> getClockResponse(Clock clock, double angle) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		DateFormat createdAtFormatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		
		JSONObject body = new JSONObject();
		try {
			body.put("id", clock.getId() == null ? "null" : clock.getId());
			body.put("time", clock.getTime().format(DateTimeFormatter.ofPattern("HH:mm")));
			body.put("createdAt", createdAtFormatter.format(clock.getCreatedAt()));
			body.put("angle", angle);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new ResponseEntity<String>(body.toString(), header, HttpStatus.OK);
	}
	
	/**
	 * @param time
	 * @return angle between clock hands
	 */
	public double computeAngle(LocalTime time) {
		double angle = 0.0;
		int hour = time.getHour();
		int minute = time.getMinute();
		
		hour = hour > 12 ? hour - 12 : hour;
		
		double dh = (60.0*hour + minute)/2.0;
		double dm = 6.0*minute;
		
		angle = Math.abs(dh - dm);
		angle = angle > 180.0 ? 360.0 - angle : angle;
		
		return angle;
	}
	
	/**
	 * @param body
	 * @return LocalTime from json body
	 */
	public LocalTime jsonToLocalTime(String body) {
		LocalTime lt = LocalTime.MIDNIGHT;
		try {
			JSONObject jsonBody = new JSONObject(body);
			lt = LocalTime.parse(jsonBody.getString("time"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lt;
	}
}
