package com.example.restdemo.service;

import com.example.restdemo.dto.StateDTO;
import com.example.restdemo.entity.State;

import java.util.List;

public interface StateService {

    public State createState(State state);

    public State getStateById(Long id);

    public List<StateDTO> fetchStates();
}
