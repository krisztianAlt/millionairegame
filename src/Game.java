import java.util.ArrayList;
import java.util.Set;
import java.util.*;

public class Game {
    private int currentLevel;
    private String playerName;
    private ArrayList maxLineNumbers;
    private HashMap<String, Boolean> hasHelpers = new HashMap<>();


    public Game() {
        this.currentLevel = 1;

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


}
