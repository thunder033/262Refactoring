package Main.Frame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by gjr8050 on 5/1/2016.
 */
public class FrameTest {

    Frame frame;

    @Before
    public void setUp() throws Exception {
        frame = new Frame();
    }

    @Test
    public void setBallPinCount() throws Exception{
        int pinCount1 = 5;
        frame.setPinCount(Ball.ONE, pinCount1);
        Assert.assertEquals("Expected pin count for Ball.ONE", pinCount1, frame.getPinCount(Ball.ONE));
    }

    @Test
    public void setPinCountActive() throws Exception {
        int pinCount1 = 5;
        frame.setPinCount(pinCount1);
        Assert.assertEquals("Expected pin count for Ball.ONE", pinCount1, frame.getPinCount(Ball.ONE));

        int pinCount2 = 3;
        frame.setPinCount(pinCount2);
        Assert.assertEquals("Expected pin count for Ball.TWO", pinCount2, frame.getPinCount(Ball.TWO));
    }

    @Test
    public void setPinCountChain() throws Exception {
        Frame frame = new Frame();
        Frame frame2 = new Frame();
        frame.setNext(frame2);
        frame2.setPrev(frame);

        int pinCount = 3;
        int pinCount2 = 2;

        frame.setPinCount(5);
        frame.setPinCount(4);
        frame.setPinCount(pinCount);
        frame.setPinCount(pinCount2);

        Assert.assertEquals("Expected pin count for frame 2 Ball.ONE", pinCount, frame2.getPinCount(Ball.ONE));
        Assert.assertEquals("Expected pin count for frame 2 Ball.TWO", pinCount2, frame2.getPinCount(Ball.TWO));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void setPinCountLast() {
        frame.setPinCount(1);
        frame.setPinCount(1);
        //We should not be allowed to set a third ball on the last frame w/o strike or spare
        frame.setPinCount(1);
    }

    @Test
    public void isBowled() throws Exception {
        assert !frame.isBowled() : "Expected frame to not be bowled";
        frame.setPinCount(1);
        frame.setPinCount(1);

        assert frame.isBowled() : "Expected frame to be bowled";
        assert !frame.isStrike() : "Expected frame to not be strike";
        assert !frame.isSpare() : "Expected frame to not be a spare";
    }

    @Test
    public void isStrike() throws Exception {
        frame.setPinCount(10);

        assert frame.isBowled() : "Expected frame to be bowled";
        assert frame.isStrike() : "Expected frame to be strike";
        assert !frame.isSpare() : "Expected frame to not be a spare";
    }

    @Test
    public void isSpare() throws Exception {
        frame.setPinCount(5);
        frame.setPinCount(5);

        assert frame.isBowled() : "Expected frame to be bowled";
        assert !frame.isStrike() : "Expected frame to not be strike";
        assert frame.isSpare() : "Expected frame to be a spare";
    }

    @Test
    public void setPinCountLastSpare() throws Exception {
        frame.setPinCount(5);
        frame.setPinCount(5);

        int pinCount3 = 4;
        frame.setPinCount(pinCount3);

        assert frame.getPinCount(Ball.THREE) == pinCount3 : "Expected Ball.THREE to have pin count of " + pinCount3;
        assert frame.isSpare() : "Expected frame to be a spare";
    }

    @Test
    public void setPinCountLastStrike() throws Exception {
        frame.setPinCount(10);

        int pinCount2 = 5;
        frame.setPinCount(pinCount2);

        int pinCount3 = 4;
        frame.setPinCount(pinCount3);

        assert frame.getPinCount(Ball.TWO) == pinCount2 : "Expected Ball.TWO to have pin count of " + pinCount2;
        assert frame.getPinCount(Ball.THREE) == pinCount3 : "Expected Ball.THREE to have pin count of " + pinCount3;
        assert frame.isStrike() : "Expected frame to be strike";
    }

    @Test
    public void getBaseScore() throws Exception {
        int pinCount = 5;
        frame.setPinCount(pinCount);
        int pinCount2 = 4;
        frame.setPinCount(pinCount2);

        Assert.assertEquals("Frame did not return expected base score", pinCount + pinCount2, frame.getBaseScore());
    }

    @Test
    public void getBaseScoreLast() throws Exception {
        int pinCount = 10;
        frame.setPinCount(pinCount);
        int pinCount2 = 5;
        frame.setPinCount(pinCount2);
        int pinCount3 = 4;
        frame.setPinCount(pinCount3);

        Assert.assertEquals("Frame did not return expected base score", pinCount + pinCount2 + pinCount3, frame.getBaseScore());
    }

    @Test
    public void isBowled1() throws Exception {
        frame.setPinCount(1);

        assert frame.isBowled(Ball.ONE) : "Expected Ball.ONE to be bowled";
        assert !frame.isBowled(Ball.TWO) : "Expected Ball.TWO to not be bowled";
        assert !frame.isBowled(Ball.THREE) : "Expected Ball.THREE to not be bowled";
    }

    @Test
    public void isLast() throws Exception {
        Frame frame1 = new Frame();
        Frame frame2 = new Frame();

        frame1.setNext(frame2);
        frame2.setPrev(frame1);

        assert !frame1.isLast() : "Expected frame 1 to not be last";
        assert frame2.isLast() : "Expected frame 2 to be last";
    }

    @Test
    public void getChainScore() throws Exception {

    }

    @Test
    public void setFrameState() throws Exception {
        frame.setFrameState(FrameState.BOWLED);
        assert frame.isBowled() : "Expected frame to have state of Bowled";
    }

    @Test
    public void setPinCount1() throws Exception {

    }

    @Test
    public void calculateFrameScore() throws Exception {
        Assert.assertEquals("Expected frame score to be Unset", Frame.Unset, frame.calculateFrameScore());
    }

    @Test
    public void getTotalScore() throws Exception {
        Assert.assertEquals("Expected total score to be Unset", Frame.Unset, frame.getTotalScore());
    }

    @Test
    public void calculateTotalScoreActive() throws Exception {
        frame.calculateTotalScore();
        Assert.assertEquals("Expected total score to be Unset", Frame.Unset, frame.getTotalScore());
    }

    @Test
    public void calculateTotalScore() throws Exception {
        Frame frame1 = new Frame();
        Frame frame2 = new Frame();

        frame1.setNext(frame2);
        frame2.setPrev(frame1);

        frame1.setPinCount(1);
        frame1.setPinCount(1);
        frame1.setPinCount(1);
        frame1.setPinCount(1);

        frame1.calculateTotalScore();
        Assert.assertEquals("Unexpected total score for frame 1", 2, frame1.getTotalScore());

        frame2.calculateTotalScore();
        Assert.assertEquals("Unexpected total score for frame 2", 4, frame2.getTotalScore());
    }
}