package com.example.restdemo.util;

import com.example.restdemo.entity.State;
import com.example.restdemo.repository.StateRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitialiser {

    private final StateRepository stateRepository;

    @Autowired
    public DatabaseInitialiser(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }


    @PostConstruct
    public void init() {

        Set<State> states = new HashSet<>();

        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("states.csv");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                Long stateId = Long.parseLong(data[0].trim());
                String stateName = data[1].trim();

                State state = State.builder().id(stateId).name(stateName).build();

                states.add(state);
            }
            reader.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        stateRepository.saveAll(states);
    }
}
