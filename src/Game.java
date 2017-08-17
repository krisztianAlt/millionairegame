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
}
