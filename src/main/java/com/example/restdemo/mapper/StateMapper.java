package com.example.restdemo.mapper;

import com.example.restdemo.dto.StateDTO;
import com.example.restdemo.entity.State;

public class StateMapper {

    public static StateDTO mapToStateDTO(State state) {
        return StateDTO.builder()
                .stateId(state.getId())
                .stateName(state.getName())
                .build();
    }
}
