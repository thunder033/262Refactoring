package Main.Frame;

import java.util.HashMap;

/**
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

    int getBaseScore(){
        return isLast()
                ? scoreAdd(scoreAdd(getPinCount(Ball.ONE), getPinCount(Ball.TWO)), getPinCount(Ball.THREE))
                : scoreAdd(getPinCount(Ball.ONE), getPinCount(Ball.TWO));
    }

    boolean isBowled(){
        return state != FrameState.ACTIVE;
    }

    boolean isBowled(Ball ball){
        return pinCounts.get(ball) != Unset;
    }

    int getChainScore(FrameState forState){
        return state.getChainScore(this, forState);
    }

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

    boolean isLast(){
        return next == null;
    }

    void calculateTotalScore(){
        if(!isBowled())
            totalScore = Unset;
        else {
            totalScore = Frame.scoreAdd(calculateFrameScore(), (prev != null ? prev.getTotalScore() : 0));
        }

    }

    public int getTotalScore(){
        if(totalScore == Unset)
            calculateTotalScore();

        return totalScore;
    }

    public String getScoreMark(){
        return getTotalScore() == Unset ? "" : Integer.toString(getTotalScore());
    }

    public String[] getBallMarks(){
        return state.getMarks(this);
    }

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