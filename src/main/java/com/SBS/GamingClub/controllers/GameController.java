package com.SBS.GamingClub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.SBS.GamingClub.Entitis.Game;
import com.SBS.GamingClub.services.GameService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/games")
@CrossOrigin(origins = "*")
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game savedGame = gameService.saveGame(game);
        return ResponseEntity.ok(savedGame);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGame(@PathVariable int id) {
        Optional<Game> game = gameService.getGame(id);
        return game.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
        Game updatedGame = gameService.updateGame(id, game);
        return updatedGame != null ? 
               ResponseEntity.ok(updatedGame) : 
               ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable int id) {
        boolean deleted = gameService.deleteGame(id);
        return deleted ? 
               ResponseEntity.ok().build() : 
               ResponseEntity.notFound().build();
    }
}