import java.util.ArrayList;
import java.util.Set;
import java.util.*;

public class Game {
    public int currentLevel;
    public String playerName;
    public ArrayList maxLineNumbers;
    public Set<Boolean> usedHelpers = new HashSet<>();
    //*time(kezdés, vége)

    public void setUserName(String userName){
        this.playerName = userName;
    }

    public String getUserName(){
        return this.playerName;
    }

    public Game() {
        this.currentLevel = 1;
        for (int index = 0; index < 3; index++){
            usedHelpers.add(false);
        }
    }

    public void setCurrentLevel(int newLevel){
        this.currentLevel = newLevel;
    }

    public int getCurrentLevel(){
        return this.currentLevel;
    }

}
