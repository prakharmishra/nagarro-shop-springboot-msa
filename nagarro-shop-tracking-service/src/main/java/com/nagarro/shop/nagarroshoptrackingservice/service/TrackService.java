package com.nagarro.shop.nagarroshoptrackingservice.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.shop.nagarroshoptrackingservice.dao.TrackDAO;
import com.nagarro.shop.nagarroshoptrackingservice.entity.Track;

@Service
public class TrackService {

	@Autowired
	private TrackDAO trackDAO;
	
	@Transactional
	public void save(Track track) {
		trackDAO.save(track);
	}
}
