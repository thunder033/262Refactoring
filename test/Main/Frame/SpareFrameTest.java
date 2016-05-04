package Main.Frame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by gjr8050 on 5/3/2016.
 */
public class SpareFrameTest {

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

        root.setPinCount(5);
        root.setPinCount(5);

        root.setPinCount(10);
        root.setPinCount(5);
        root.setPinCount(5);
    }

    @Test
    public void setPinCount() throws Exception {
        Frame frame1 = new Frame();
        Frame frame2 = new Frame();

        frame1.setNext(frame2);
        frame2.setPrev(frame1);

        FrameState.SPARE.setPinCount(frame1, 5);
        assert !frame1.isBowled(Ball.ONE) : "Expected frame 1 BALL.ONE to not be bowled";
        Assert.assertEquals("Expected frame 2 to have pin count", 5, frame2.getPinCount(Ball.ONE));

        FrameState.SPARE.setPinCount(frame2, 6);
        Assert.assertEquals("Expected frame 2 to have pin count on BALL.THREE", 6, frame2.getPinCount(Ball.THREE));
    }

    @Test
    public void calculateFrameScore() throws Exception {
        setScores();
        //1 + 1 + 5 = 7
        Assert.assertEquals("Unexpected score calculation 1", 7, FrameState.SPARE.calculateFrameScore(frames.get(0)));
        //5 + 5 + (10) = 20
        Assert.assertEquals("Unexpected score calculation 2", 20, FrameState.SPARE.calculateFrameScore(frames.get(1)));
        //10 + 5 + 5 = 20
        Assert.assertEquals("Unexpected score calculation 3", 20, FrameState.SPARE.calculateFrameScore(frames.get(2)));
    }



    @Test
    public void getChainScore() throws Exception {
        setScores();
        Assert.assertEquals("Unexpected Chain Score result 1", 10, FrameState.SPARE.getChainScore(frames.get(1),FrameState.STRIKE));
        Assert.assertEquals("Unexpected Chain Score result 2", 5, FrameState.SPARE.getChainScore(frames.get(1),FrameState.SPARE));
        Assert.assertEquals("Unexpected Chain Score result 3", 0, FrameState.SPARE.getChainScore(frames.get(1),FrameState.BOWLED));
    }
}