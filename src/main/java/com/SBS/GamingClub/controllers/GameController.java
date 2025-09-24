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
public class GameController {
    
    @Autowired
    private GameService gameService;
    
    @PostMapping
    public Game createGame(@RequestBody Game game) {
        return gameService.saveGame(game);
    }
    
    // @GetMapping("/{id}")
    // public ResponseEntity<Game> getGame(@PathVariable int id) {
    //     Optional<Game> game = gameService.getGame(id);
    //     return game.map(ResponseEntity::ok)
    //               .orElse(ResponseEntity.notFound().build());
    // }
    
    @GetMapping
    public List<Game> getAllGames() {
        return gameService.getAllGames();
    }
    
    // @PutMapping("/{id}")
    // public ResponseEntity<Game> updateGame(@PathVariable int id, @RequestBody Game game) {
    //     Game updatedGame = gameService.updateGame(id, game);
    //     return updatedGame != null ? 
    //            ResponseEntity.ok(updatedGame) : 
    //            ResponseEntity.notFound().build();
    // }
    
    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteGame(@PathVariable int id) {
    //     boolean deleted = gameService.deleteGame(id);
    //     return deleted ? 
    //            ResponseEntity.ok().build() : 
    //            ResponseEntity.notFound().build();
    }
}