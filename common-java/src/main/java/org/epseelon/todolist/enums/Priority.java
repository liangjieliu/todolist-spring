package org.epseelon.todolist.enums;

/**
 * @author sarbogast
 * @version 21 mai 2009, 16:09:05
 */
public enum Priority {
    HIGH(3), MEDIUM(2), LOW(1);

    private int level;

    private Priority(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
