package com.example.restdemo.service.impl;

import com.example.restdemo.entity.State;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.exception.StateAlreadyExistsException;
import com.example.restdemo.repository.StateRepository;
import com.example.restdemo.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public State createState(State state) {
        if (stateRepository.existsByNameIgnoreCase(state.getName()))
            throw new StateAlreadyExistsException(state.getName());
        return stateRepository.save(state);
    }

    @Override
    public State getStateById(Long id) {
        return stateRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("State", "id", String.valueOf(id)));
    }
}
