package game.strategy;

 public interface IStrategyFactory {

    GameStrategy getStrategy(String difficultyLevel);
}
