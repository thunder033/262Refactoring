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

    void setNext(Frame frame){
        next = frame;
    }

    void setPrev(Frame frame){
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

    int getBaseScore(){
        return getPinCount(Ball.ONE) + getPinCount(Ball.TWO) + getPinCount(Ball.THREE);
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

    void setPinCount(int count){
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
        else
            totalScore = calculateFrameScore() + (prev != null ? prev.getTotalScore() : 0);
    }

    int getTotalScore(){
        return totalScore;
    }
}