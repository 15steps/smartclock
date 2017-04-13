package br.ufpe.nti.model;

import java.sql.Timestamp;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.ufpe.nti.util.ClockLocalTimeSerializer;

@Entity
public class Clock {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Column(name = "CLOCK_TIME")
	@JsonSerialize(using = ClockLocalTimeSerializer.class)
	private LocalTime time;

	@NotNull
	@Column(name = "CREATED_AT")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private Timestamp createdAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
}
