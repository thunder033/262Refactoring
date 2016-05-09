package Main.Frame;

import java.util.HashMap;

/**
 * Stores pin counts, total score, and state; provides interface for managing
 * those values through a state-machine and chain-of-responsibility
 * Created by gjr8050 on 5/1/2016.
 */
public class Frame {
    static final int Unset = -1;

    private HashMap<Ball, Integer> pinCounts;

    Frame next;
    Frame prev;

    private FrameState state;

    private int totalScore = Unset;

    public Frame(){
        state = FrameState.ACTIVE;
        pinCounts = new HashMap<>();
        pinCounts.put(Ball.ONE, Unset);
        pinCounts.put(Ball.TWO, Unset);
        pinCounts.put(Ball.THREE, Unset);
    }

    public void setNext(Frame frame){
        next = frame;
    }

    public void setPrev(Frame frame){
        prev = frame;
    }

    boolean isStrike(){
        return getPinCount(Ball.ONE) == 10;
    }

    boolean isSpare(){
        return !isStrike() && getPinCount(Ball.ONE) + getPinCount(Ball.TWO) == 10;
    }

    int getPinCount(Ball ball){
        return isBowled(ball) ? pinCounts.get(ball) : 0;
    }

    int getScore(Ball ball){
        return pinCounts.get(ball);
    }

    /**
     * Gets the string mark for a ball, does not handle spares
     * @param ball the ball to get a mark for
     * @return an empty string for un-bowled, a dash for no pins, or the count as a string
     */
    String getMark(Ball ball){
        if(!isBowled(ball))
            return "";
        else {
            int pins = getPinCount(ball);
            switch (pins){
                case 0: return "-";
                case 10: return "X";
                default: return Integer.toString(pins);
            }
        }
    }

    /**
     * returns the base pin count on a frame
     * @return numeric value or Frame.Unset if can't be determined
     */
    int getBaseScore(){
        return isLast()
                ? scoreAdd(scoreAdd(getPinCount(Ball.ONE), getPinCount(Ball.TWO)), getPinCount(Ball.THREE))
                : scoreAdd(getPinCount(Ball.ONE), getPinCount(Ball.TWO));
    }

    /**
     * Indicates if the frame has been bowled
     */
    boolean isBowled(){
        return state != FrameState.ACTIVE;
    }

    /**
     * Indicates if a specific ball on a frame has been bowled
     * @param ball the ball to check
     * @return whether the given ball is Frame.Unset
     */
    boolean isBowled(Ball ball){
        return pinCounts.get(ball) != Unset;
    }

    /**
     * Calculate the number of points this frame would contribute to a special scoring case on a previous frame
     * @param forState the special scoring case being handled
     * @return the points to add or Frame.Unset
     */
    int getChainScore(FrameState forState){
        return state.getChainScore(this, forState);
    }

    /**
     * Sets pin count for the given ball
     * @param ball the ball to set count for
     * @param count pins bowled on that ball
     */
    void setPinCount(Ball ball, int count){
        pinCounts.put(ball, count);
    }

    void setFrameState(FrameState state){
        this.state = state;
    }

    public void setPinCount(int count){
        state.setPinCount(this, count);
    }

    int calculateFrameScore(){
        return state.calculateFrameScore(this);
    }

    /**
     * indicates if this is the last frame
     */
    boolean isLast(){
        return next == null;
    }

    /**
     * Calculates total score for this frame or unsets it if not bowled yet
     */
    void calculateTotalScore(){
        if(!isBowled())
            totalScore = Unset;
        else {
            totalScore = Frame.scoreAdd(calculateFrameScore(), (prev != null ? prev.getTotalScore() : 0));
        }
    }

    /**
     * returns the total scored and calculates total score for this frame if it has not yet been calculated
     * @return the total score on this frame
     */
    public int getTotalScore(){
        if(totalScore == Unset)
            calculateTotalScore();

        return totalScore;
    }

    /**
     * Gets the total score for this frame or an empty string if not calculated yet
     * @return string representation of total score
     */
    public String getScoreMark(){
        return getTotalScore() == Unset ? "" : Integer.toString(getTotalScore());
    }

    public String[] getBallMarks(){
        return state.getMarks(this);
    }

    /**
     * Resets all properties of the frame
     */
    public void reset(){
        totalScore = Unset;
        pinCounts.put(Ball.ONE, Unset);
        pinCounts.put(Ball.TWO, Unset);
        pinCounts.put(Ball.THREE, Unset);
        setFrameState(FrameState.ACTIVE);

        if(next != null)
            next.reset();
    }

    /**
     * Ensures Unset scores propagate throughout a calculation
     * @param a number
     * @param b number
     * @return the sum of a and b, or Unset if either one is unset
     */
    static int scoreAdd(int a, int b){
        return a == Frame.Unset || b == Frame.Unset ? Frame.Unset : a + b;
    }
}