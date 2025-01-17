package wipeout;

import java.util.LinkedList;
import java.util.List;

/**
 * The obstacle course is a long rolling log connecting two ends of a cliff
 * that crosses over a raging river below.   The log is old, and can only
 * allow a certain number of Woolies on it at the same time.  The Woolies
 * arrive to the course and are allowed to enter the course in the order
 * they arrive.  Woolies can only cross the log when there is room on it,
 * and it is moving.  The Kraken is in control of turning the course off and
 * on in order to handle any Woolies that fall of it.  If a Woolie falls, any
 * Woolies currently crossing the course must return to the back of the line
 * to try to cross again.
 *
 * @author RIT CS
 * @author Huy Le
 */
public class ObstacleCourse {
    /** The maximum number of Woolies allowed on the course */
    private int maxOnCourse;

    /** Is the course open for use? */
    private boolean open;

    /** Is the course powered on? */
    private boolean running;

    /** the line of Woolies waiting to enter the course */
    private List<Woolie> waitingLine;

    /** number of Woolies currently on the course */
    private int numOnCourse;

    /** has a Woolie fallen into the river? */
    private boolean fallenWoolie;

    /**
     * Construct the course.
     *
     * @param maxOnCourse the max number of Woolies allowed on the course.
     */
    public ObstacleCourse(int maxOnCourse) {
        this.maxOnCourse = maxOnCourse;
        this.open = true;
        this.running = false;
        this.waitingLine = new LinkedList<>();
        this.numOnCourse = 0;
        this.fallenWoolie = false;
    }

    /**
     * Find out how many Woolies are allowed to be crossing the course at once.
     *
     * @return max Woolies allowed on course at once
     */
    public int getMaxOnCourse() { return this.maxOnCourse; }

    /**
     * Has a Woolie fallen off the course?  The Kraken needs to constantly
     * check this while they are crossing.
     *
     * @return whether a Woolie has fallen or not since last checked
     */
    public boolean hasWoolieFallen() { return this.fallenWoolie; }

    /**
     * Are any Woolies currently on the course?
     *
     * @return Whether the course is being used or not
     */
    public boolean isOccupied() { return this.numOnCourse > 0; }


    /** When the course is closed, the simulation has ended. */
    public void setClosed() { this.open = false; }

    /** Turns the course off.  When the Kraken does this, no Woolies can
     * cross. */
    public void turnOff() { this.running = false; }

    /** Turn the course on.  When the Kraken does this, Woolies can't cross. */
    public synchronized void turnOn() {
        this.running = true;
        this.fallenWoolie = false;
        notifyAll();
    }

    /** This is called by a Woolie when they fall off the course.  It will
     * signal the Kraken to jump into action and resolve the situation. */
    public synchronized void fallOff() {
        this.fallenWoolie = true;
    }

    /**
     * Is the course open for use?   The Kraken uses this to know if he
     * needs to keep working.
     * @return is the obstacle course open?
     */
    public boolean isOpen() { return this.open; }

    /**
     * Is the course running?  If it isn't, the Woolies can't cross it.
     *
     * @return whether the course is running or not
     */
    public boolean isRunning() { return this.running; }

    /**
     * A newly arrived Woolie calls this to gain permission to cross the
     * the course.  The Woolie will not return from this method until
     * the following conditions are met:<br>
     * <br>
     * 1. The course is currently running.<br>
     * 2. There is room on the course.<br>
     * 3. The Woolie is at the front of the line.<br>
     * <br>
     * @param woolie the Woolie who wants to cross the obstacle course
     */
    public synchronized void enter(Woolie woolie) {
        System.out.println("WOOLIE: " + woolie + " enters line");
        this.waitingLine.addLast(woolie);
        //TODO
        while(!this.waitingLine.getFirst()
                .equals(woolie)
                ||! this.isRunning()
                ||this.numOnCourse==this.maxOnCourse){
            try{
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.waitingLine.remove(woolie);
            ++this.numOnCourse;
        }

        System.out.println("WOOLIE: " + woolie + " enters course");

    }

    /**
     * This is called when a Woolie successfully navigates the obstacle
     * course and reached the end of it.  This method is used to signal
     * to the other Woolies that may be waiting that they should wake
     * up and check if the conditions that caused them to pause in the
     * enter method are now all met.
     *
     * @param woolie the Woolie leaving
     */
    public synchronized void leave(Woolie woolie) {
        //TODO
            this.numOnCourse--;
            notifyAll();
            System.out.println("WOOLIE: " + woolie + " leaves course");

    }


    /**
     * This method is called from the Kraken class to print in the standard output the given string concatenated 
     * with the string representation of this ObstacleCourse.
     *
     * The method is synchronized to prevent printing this ObstacleCourse's state while an ObstacleCourse's mutator method
     * is running.
     *
     * @param s the string
     */
    public synchronized void display(String s){
        System.out.println(s + this);
    }

    /**
     * Return the string representation of the obstaclec course.
     * @return the string.
     */
    @Override
    public String toString() {
        return "OBSTACLE_COURSE: ObstacleCourse(" +
                "maxOnCourse=" + this.maxOnCourse + ", " +
                "numOnCourse=" + this.numOnCourse + ", " +
                "fallenWoolie=" + this.fallenWoolie + ", " +
                "running=" + this.running + ", " +
                "open=" + this.open + ", " +
                "wooliesWaiting=" + this.waitingLine.size() +
                ")";
    }
}
