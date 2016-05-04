package Main.Frame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Testing functionality of BowledFrame state handler
 * Created by gjr8050 on 5/3/2016.
 */
public class BowledFrameTest {

    private ArrayList<Frame> frames;

    @Before
    public void setUp() throws Exception {
        frames = new ArrayList<>();
        for(int i = 0; i < 3; i++){
            Frame frame = new Frame();
            frames.add(frame);
            if(i > 0){
                frames.get(i - 1).setNext(frame);
                frame.setPrev(frames.get(i - 1));
            }
        }
    }

    private void setScores(){
        Frame root = frames.get(0);
        root.setPinCount(1);
        root.setPinCount(1);

        root.setPinCount(10);

        root.setPinCount(5);
        root.setPinCount(5);
        root.setPinCount(5);
    }

    @Test
    public void setPinCount() throws Exception {
        Frame frame1 = new Frame();
        Frame frame2 = new Frame();

        frame1.setNext(frame2);
        frame2.setPrev(frame1);

        FrameState.BOWLED.setPinCount(frame1, 5);
        assert !frame1.isBowled(Ball.ONE) : "Expected frame 1 BALL.ONE to not be bowled";
        Assert.assertEquals("Expected frame 2 to have pin count", 5, frame2.getPinCount(Ball.ONE));
    }

    @Test
    public void calculateFrameScore() throws Exception {
        setScores();
        Assert.assertEquals("Unexpected score calculation 1", 2, FrameState.BOWLED.calculateFrameScore(frames.get(0)));
        Assert.assertEquals("Unexpected score calculation 2", 10, FrameState.BOWLED.calculateFrameScore(frames.get(1)));
        Assert.assertEquals("Unexpected score calculation 3", 15, FrameState.BOWLED.calculateFrameScore(frames.get(2)));
    }

    @Test
    public void getChainScore() throws Exception {
        setScores();
        Assert.assertEquals("Unexpected Chain Score result 1", 10, FrameState.BOWLED.getChainScore(frames.get(1),FrameState.STRIKE));
        Assert.assertEquals("Unexpected Chain Score result 2", 10, FrameState.BOWLED.getChainScore(frames.get(1),FrameState.SPARE));
        Assert.assertEquals("Unexpected Chain Score result 3", 0, FrameState.BOWLED.getChainScore(frames.get(1),FrameState.BOWLED));
    }
}