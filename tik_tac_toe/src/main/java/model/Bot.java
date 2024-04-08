package model;

import strategy.BotPlayingStrategy;
import strategy.BotPlayingStrategyFactory;

public class Bot extends Player{
    BotDificulityLevel dificulityLevel;
    // according to dificulity level we should have bot playing strategy
    BotPlayingStrategy botPlayingStrategy;
    public Bot(String id, String name, Symbol symbol, BotDificulityLevel dificulityLevel) {
        super(id, name, symbol, PlayerType.BOT);
        this.dificulityLevel = dificulityLevel;
        botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(dificulityLevel);
    }

    public BotDificulityLevel getDificulityLevel() {
        return dificulityLevel;
    }

    public void setDificulityLevel(BotDificulityLevel dificulityLevel) {
        this.dificulityLevel = dificulityLevel;
    }

    public BotPlayingStrategy getBotPlayingStrategy() {
        return botPlayingStrategy;
    }

    public void setBotPlayingStrategy(BotPlayingStrategy botPlayingStrategy) {
        this.botPlayingStrategy = botPlayingStrategy;
    }
}
