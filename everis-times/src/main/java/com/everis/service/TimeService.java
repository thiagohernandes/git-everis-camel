package com.everis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.everis.model.Time;
import com.everis.repository.TimeRepositoryFake;

@Service
public class TimeService {
	
	@Autowired
	private TimeRepositoryFake timeRepositoryFake;
	
	public List<Time> getAllTimes() {
		return timeRepositoryFake.fakeTimes();
	}

}
