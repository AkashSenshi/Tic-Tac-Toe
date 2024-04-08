package strategy;

import model.BotDificulityLevel;

public class BotPlayingStrategyFactory {
    public static BotPlayingStrategy getBotPlayingStrategy(BotDificulityLevel level) {
        if (level == BotDificulityLevel.EASY) {
            return new EasyBotPlayingStrategy();
        }
        return null;
    }
}
