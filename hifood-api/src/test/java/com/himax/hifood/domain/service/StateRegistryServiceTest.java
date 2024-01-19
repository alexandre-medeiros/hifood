package com.himax.hifood.domain.service;

import com.himax.hifood.domain.model.State;
import com.himax.hifood.domain.model.state.StateMotherTest;
import com.himax.hifood.domain.repository.StateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StateRegistryServiceTest {
    private final Long stateId = 1L;

    @Mock
    private StateRepository stateRepository;
    private StateRegistryService stateRegistryService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
        stateRegistryService = new StateRegistryService(stateRepository);
    }

    @Test
    void Given_a_list_of_states_When_findAll_Then_return_the_list() {
        // Arrange
        List<State> states = StateMotherTest.getStatesList();
        when(stateRepository.findAll()).thenReturn(states);

        // Act
        List<State> result = stateRegistryService.findAll();

        // Assert
        assertEquals(5, result.size());
        StateMotherTest.deepComparation(states, result);
        verify(stateRepository, times(1)).findAll();
    }

    @Test
    void find() {
    }

}