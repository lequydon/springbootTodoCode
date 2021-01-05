package com.demo.Service.Interface;

import java.util.List;

import com.demo.entity.Status;

public interface IStatusService {
List<Status> getListStatus();
Status getStatus(int id);
}
