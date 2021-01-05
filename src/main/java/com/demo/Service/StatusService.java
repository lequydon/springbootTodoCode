package com.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Service.Interface.IStatusService;
import com.demo.entity.Status;
import com.demo.repository.StatusRepository;

@Service
public class StatusService implements IStatusService {
	@Autowired
	StatusRepository statusRepository;
	@Override
	public List<Status> getListStatus() {
		// TODO Auto-generated method stub
		List<Status> listStatus =statusRepository.findAll();
		return listStatus;
	}

}
