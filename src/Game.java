import java.util.ArrayList;
import java.util.Set;
import java.util.*;

public class Game {
    private int currentLevel;
    private int checkPoint;
    private String playerName;
    private ArrayList maxLineNumbers;
    private HashMap<String, Boolean> hasHelpers = new HashMap<>();
    private HashMap<Integer, Integer> levelToPrize = new HashMap<>();


    public Game() {
        this.currentLevel = 1;
        this.checkPoint = 0;

        hasHelpers.put("half", true);
        hasHelpers.put("poll", true);
        hasHelpers.put("expert", true);

        levelToPrize.put(1, 5);
        levelToPrize.put(2, 10);
        levelToPrize.put(3, 25);
        levelToPrize.put(4, 50);
        levelToPrize.put(5, 100);
        levelToPrize.put(6, 250);
        levelToPrize.put(7, 500);
        levelToPrize.put(8, 1000);
        levelToPrize.put(9, 2000);
        levelToPrize.put(10, 5000);

    }

    public int getCurrentLevel(){

        return this.currentLevel;

    }

    public void setCurrentLevel(int newLevel){

        this.currentLevel = newLevel;

    }

    public String getUserName(){

        return this.playerName;

    }

    public void setUserName(String userName){

        this.playerName = userName;

    }

    public HashMap getHasHelpers(){

        return this.hasHelpers;

    }

    public void setHasHelpers(String key, Boolean value){

        this.hasHelpers.put(key, value);

    }

    public int getCurrentCheckpoint(){
        return this.checkPoint;
    }

    public void setCheckPoint(int newCheckPoint){

        this.checkPoint = newCheckPoint;

    }

    public int getPrize(int level){

        return this.levelToPrize.get(level);

    }

}
