package org.kosta.finalproject.service;

import org.kosta.finalproject.model.mapper.ApplyAlarmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ApplyAlarmServiceImpl implements ApplyAlarmService{

    @Autowired
    private ApplyAlarmMapper applyAlarmMapper;

}
