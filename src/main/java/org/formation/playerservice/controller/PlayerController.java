package org.formation.playerservice.controller;



import org.formation.playerservice.dto.PlayerDTO;
import org.formation.playerservice.model.Player;
import org.formation.playerservice.repository.PlayerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerRepository repository;

    public PlayerController(PlayerRepository repository) {
        this.repository = repository;
    }

    // ✅ Créer un joueur depuis JSON
    @PostMapping
    public Player createPlayer(@RequestBody PlayerDTO dto) {
        Player player = new Player(dto.name(), dto.email());
        return repository.save(player);
    }
    @GetMapping
    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    // ✅ Modifier le score
    @PutMapping("/{name}/score")
    public Player updateScore(@PathVariable String name, @RequestParam int delta) {
        Player player = repository.findById(name).orElseThrow();
        player.setScore(player.getScore() + delta);
        return repository.save(player);
    }

    // ✅ Lire le score
    @GetMapping("/{name}")
    public int getScore(@PathVariable String name) {
        return repository.findById(name).map(Player::getScore).orElse(0);
    }

    // ✅ Lire l’email
    @GetMapping("/{name}/email")
    public String getEmail(@PathVariable String name) {
        return repository.findById(name).map(Player::getEmail).orElse("not found");
    }

    @GetMapping("/ping")
    public String ping() {
        return "Player service is alive!";
    }
}
