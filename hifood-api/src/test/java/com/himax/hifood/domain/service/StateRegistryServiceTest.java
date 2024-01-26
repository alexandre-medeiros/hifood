package com.himax.hifood.domain.service;

import com.himax.hifood.domain.exception.EntityNotFoundException;
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

import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StateRegistryServiceTest {
    private final Long stateId = 1L;
    private final Long noExistentStateId = 999999L;

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
    void Given_a_existing_State_When_find_Then_return_the_State() {
        // Arrange
        State state = StateMotherTest.getExistingStateWithId(stateId);
        when(stateRepository.findOrFail(stateId)).thenReturn(state);

        // Act
        State result = stateRegistryService.find(stateId);

        // Assert
        assertNotNull(result);
        StateMotherTest.deepComparation(state, result);
        verify(stateRepository, times(1)).findOrFail(stateId);
    }

    @Test
    void Given_a_nonexistent_stateId_When_find_Then_throw_EntityNotFoundException() {
        // Arrange
        when(stateRepository.findOrFail(noExistentStateId)).thenThrow(EntityNotFoundException.class);

        // Act
        EntityNotFoundException e = catchThrowableOfType(() -> stateRegistryService.find(noExistentStateId), EntityNotFoundException.class);

        // Assert
        assertNotNull(e);
        verify(stateRepository, times(1)).findOrFail(noExistentStateId);
        verify(stateRepository, never()).deleteById(noExistentStateId);
    }

    @Test
    void Given_a_existing_child_State_When_findChild_Then_return_the_child_State() {
        // Arrange
        State state = StateMotherTest.getExistingStateWithId(stateId);
        when(stateRepository.findChildOrFail(stateId)).thenReturn(state);

        // Act
        State result = stateRegistryService.findChild(stateId);

        // Assert
        assertNotNull(result);
        StateMotherTest.deepComparation(state, result);
        verify(stateRepository, times(1)).findChildOrFail(stateId);
    }

    @Test
    void Given_a_nonexistent_child_stateId_When_findChild_Then_throw_EntityNotFoundException() {
        // Arrange
        when(stateRepository.findChildOrFail(noExistentStateId)).thenThrow(EntityNotFoundException.class);

        // Act
        EntityNotFoundException e = catchThrowableOfType(() -> stateRegistryService.findChild(noExistentStateId), EntityNotFoundException.class);

        // Assert
        assertNotNull(e);
        verify(stateRepository, times(1)).findChildOrFail(noExistentStateId);
    }

    @Test
    void Given_a_new_State_When_create_Then_return_the_new_one_with_id(){
        //Arrange
        State state = StateMotherTest.getNewState();
        when(stateRepository.save(state)).thenAnswer(call->{
            State newState = call.getArgument(0,State.class);
            newState.setId(stateId);
            return newState;
        });

        //Act
        State result = stateRegistryService.create(state);

        //Assert
        assertNotNull(result);
        state.setId(stateId);
        StateMotherTest.deepComparation(state, result);
        verify(stateRepository, times(1)).save(state);
    }

    @Test
    void Given_a_existing_State_When_updated_Then_return_updated_state(){
        //Arrange
        State state = StateMotherTest.getExistingStateWithId(stateId);
        when(stateRepository.findOrFail(stateId)).thenReturn(state);
        when(stateRepository.save(state)).thenReturn(state);

        //Act
        State result = stateRegistryService.update(state,stateId);

        //Assert
        assertNotNull(result);
        StateMotherTest.deepComparation(state, result);
        verify(stateRepository, times(1)).save(state);
        verify(stateRepository, times(1)).findOrFail(stateId);
    }

    @Test
    void Given_a_nonexistent_stateId_and_State_When_update_Then_throw_EntityNotFoundException(){
        //Arrange
        State noExistentState = StateMotherTest.getNoExistentStateWithId(noExistentStateId);
        when(stateRepository.findOrFail(noExistentStateId)).thenThrow(EntityNotFoundException.class);

        //Act
        EntityNotFoundException e = catchThrowableOfType(() -> stateRegistryService.update(noExistentState, noExistentStateId), EntityNotFoundException.class);

        //Assert
        assertNotNull(e);
        verify(stateRepository, times(1)).findOrFail(noExistentStateId);
        verify(stateRepository, never()).save(noExistentState);
    }

    @Test
    void Given_a_existing_State_When_remove_Then_return_void(){
        //Arrange
        State state = StateMotherTest.getExistingStateWithId(stateId);
        when(stateRepository.findOrFail(stateId)).thenReturn(state);

        //Act
        stateRegistryService.remove(stateId);

        //Assert
        verify(stateRepository, times(1)).findOrFail(stateId);
        verify(stateRepository, times(1)).deleteOrFail(stateId);
    }

}