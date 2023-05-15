package com.example.restdemo.service;

import com.example.restdemo.entity.State;

public interface StateService {

    public State createState(State state);

    public State getStateById(Long id);
}
