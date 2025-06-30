package org.formation.playerservice;

import org.formation.playerservice.dto.PlayerDTO;
import org.formation.playerservice.model.Player;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.formation.playerservice.repository.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PlayerServiceApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PlayerRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreatePlayer() throws Exception {
        PlayerDTO player = new PlayerDTO("bob", "bob@example.com");

        mockMvc.perform(post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(player)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("bob")))
                .andExpect(jsonPath("$.email", is("bob@example.com")))
                .andExpect(jsonPath("$.score", is(0)));
    }

    @Test
    public void testUpdateScore() throws Exception {
        repository.save(new Player("alice", "alice@example.com"));

        mockMvc.perform(put("/players/alice/score?delta=5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score", is(5)));
    }

    @Test
    public void testGetScore() throws Exception {
        repository.save(new Player("john", "john@example.com"));

        mockMvc.perform(get("/players/john"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }

    @Test
    public void testGetEmail() throws Exception {
        repository.save(new Player("david", "david@example.com"));

        mockMvc.perform(get("/players/david/email"))
                .andExpect(status().isOk())
                .andExpect(content().string("david@example.com"));
    }

}
