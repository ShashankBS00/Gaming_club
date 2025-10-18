package com.SBS.GamingClub.services;

import java.util.Optional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SBS.GamingClub.Entitis.Game;
import com.SBS.GamingClub.repository.GameRepository;

@Service
public class GameService {
    
    @Autowired
    private GameRepository gameRepository;
    
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public boolean deleteGame(int id) {
        Optional<Game> existingGame = gameRepository.findById(id);
        if(existingGame.isPresent()) {
            gameRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Optional<Game> getGame(int id) {
        return gameRepository.findById(id);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game updateGame(int id, Game game) {
        Optional<Game> existingGame = gameRepository.findById(id);
        if(existingGame.isPresent()) {
            Game oldGame = existingGame.get();
            oldGame.setName(game.getName());
            oldGame.setDescription(game.getDescription());
            oldGame.setAmount(game.getAmount());
            oldGame.setStatus(game.getStatus());
            return gameRepository.save(oldGame);
        }
        return null;
    }
}