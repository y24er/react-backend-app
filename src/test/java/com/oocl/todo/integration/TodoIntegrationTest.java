package com.oocl.todo.integration;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oocl.todo.dto.TodoRequestDTO;
import com.oocl.todo.dto.TodoResponseDTO;
import com.oocl.todo.mapper.TodoMapper;
import com.oocl.todo.model.Todo;
import com.oocl.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TodoIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TodoRepository todoRepository;
    @Autowired
    private TodoMapper todoMapper;

    @BeforeEach
    private void deleteData() {
        todoRepository.deleteAll();
    }

    @Test
    void should_return_todo_list_when_get_all_todo_given_nothing() throws Exception {
        //given
        todoRepository.saveAll(asList(new Todo("sleeping"), new Todo("eating")));
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$.[0].content").value("sleeping"));
    }

    @Test
    void should_return_response_todo_dto_when_add_todo_given_request_todo_dto() throws Exception {
        //given
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("sleeping",false);
        String requestJson = JSONObject.toJSONString(todoRequestDTO);
        //when
        //then
        String responseString = mockMvc.perform(MockMvcRequestBuilders.post("/todos").contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.id").isNumber()).andReturn()
                .getResponse().getContentAsString();
        assertTrue(responseString.contains("sleeping"));
    }

    @Test
    void should_return_updated_todo_response_dto_when_update_todo_given_todo_id_and_todo_request_dto() throws Exception {
        //given
        Todo todo = new Todo("sleeping");
        Todo savedTodo = todoRepository.save(todo);
        int id = savedTodo.getId();
        TodoRequestDTO todoRequestDTO = new TodoRequestDTO("sleeping",true);
        String jsonString = JSONObject.toJSONString(todoRequestDTO);
        //when
        //then
        String responseAsString = mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}", id).contentType(MediaType.APPLICATION_JSON).content(jsonString))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        TodoResponseDTO todoResponseDTO = JSON.parseObject(responseAsString, TodoResponseDTO.class);
        assertEquals(id, todoResponseDTO.getId());
        assertEquals("sleeping", todoResponseDTO.getContent());
        assertEquals(true, todoResponseDTO.getStatus());
    }

    @Test
    void should_return_nothing_when_delete_todo_given_id() throws Exception {
        //given
        Todo todo = new Todo("sleeping");
        Todo savedTodo = todoRepository.save(todo);
        int id = savedTodo.getId();
        //when
        //then
        mockMvc.perform(MockMvcRequestBuilders.delete("/todo/{id}", id)).andReturn();
    }
}
