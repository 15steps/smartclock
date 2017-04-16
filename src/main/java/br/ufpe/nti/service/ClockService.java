package br.ufpe.nti.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
	
	/**
	 * @param history
	 * @return json of all records in Clock table
	 */
	public ResponseEntity<String> getClockHistoryResponse(List<Clock> history) {
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		DateFormat createdAtFormatter = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss");
		
		JSONObject body = new JSONObject();
		for(Clock clock : history) {
			try {
				body.put("id", clock.getId());
				body.put("time", clock.getTime());
				body.put("createdAt", clock.getCreatedAt());
				body.put("angle", clock.getAngle());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new ResponseEntity<String>(body.toString(), header, HttpStatus.OK);
	}
}
