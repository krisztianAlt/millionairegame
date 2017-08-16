import java.util.ArrayList;
import java.util.Set;
import java.util.*;

public class Game {
    private int currentLevel;
    private String playerName;
    private ArrayList maxLineNumbers;
    private Set<Boolean> usedHelpers = new HashSet<>();


    public Game() {
        this.currentLevel = 1;
        for (int index = 0; index < 3; index++){
            usedHelpers.add(false);
        }
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

}
