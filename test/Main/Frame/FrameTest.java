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

    ArrayList<Frame> frames;

    @Before
    public void setUp() throws Exception {
        frames = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            Frame frame = new Frame();
            frames.add(frame);
            if(i > 0){
                frames.get(i - 1).setNext(frame);
                frame.setPrev(frames.get(i - 1));
            }
        }
    }

    @Test
    public void setBallPinCount() throws Exception{
        int pinCount1 = 5;
        Frame frame = new Frame();
        frame.setPinCount(Ball.ONE, pinCount1);
        Assert.assertEquals("Expected pin count for Ball.ONE", pinCount1, frame.getPinCount(Ball.ONE));
    }

    @Test
    public void setPinCountActive() throws Exception {
        int pinCount1 = 5;
        Frame frame = new Frame();
        frame.setPinCount(pinCount1);
        Assert.assertEquals("Expected pin count for Ball.ONE", pinCount1, frame.getPinCount(Ball.ONE));

        int pinCount2 = 3;
        frame.setPinCount(pinCount2);
        Assert.assertEquals("Expected pin count for Ball.TWO", pinCount2, frame.getPinCount(Ball.TWO));
    }

    @Test
    public void setPinCountChain() throws Exception {

    }

    @Test(expected = UnsupportedOperationException.class)
    public void setPinCountLast() {
        Frame frame = new Frame();
        frame.setPinCount(1);
        frame.setPinCount(1);
        //We should not be allowed to set a third ball on the last frame w/o strike or spare
        frame.setPinCount(1);
    }

    @Test
    public void isBowled() throws Exception {
        assert !frames.get(0).isBowled() : "Expected first frame to not be bowled";
        Frame frame = new Frame();
        frame.setPinCount(1);
        frame.setPinCount(1);

        assert frame.isBowled() : "Expected frame to be bowled";
        assert !frame.isStrike() : "Expected frame to not be strike";
        assert !frame.isSpare() : "Expected frame to not be a spare";
    }

    @Test
    public void isStrike() throws Exception {
        Frame frame = new Frame();
        frame.setPinCount(10);

        assert frame.isBowled() : "Expected frame to be bowled";
        assert frame.isStrike() : "Expected frame to be strike";
        assert !frame.isSpare() : "Expected frame to not be a spare";
    }

    @Test
    public void isSpare() throws Exception {
        Frame frame = new Frame();
        frame.setPinCount(5);
        frame.setPinCount(5);

        assert frame.isBowled() : "Expected frame to be bowled";
        assert !frame.isStrike() : "Expected frame to not be strike";
        assert frame.isSpare() : "Expected frame to be a spare";
    }

    @Test
    public void setPinCountLastSpare() throws Exception {
        Frame frame = new Frame();
        frame.setPinCount(5);
        frame.setPinCount(5);

        int pinCount3 = 4;
        frame.setPinCount(pinCount3);

        assert frame.getPinCount(Ball.THREE) == pinCount3 : "Expected Ball.THREE to have pin count of " + pinCount3;
        assert frame.isSpare() : "Expected frame to be a spare";
    }

    @Test
    public void setPinCountLastStrike() throws Exception {
        Frame frame = new Frame();
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
    public void getPinCount() throws Exception {

    }

    @Test
    public void getBaseScore() throws Exception {

    }

    @Test
    public void isBowled1() throws Exception {

    }

    @Test
    public void getChainScore() throws Exception {

    }

    @Test
    public void setFrameState() throws Exception {

    }

    @Test
    public void setPinCount1() throws Exception {

    }

    @Test
    public void calculateFrameScore() throws Exception {

    }

    @Test
    public void isLast() throws Exception {

    }

    @Test
    public void calculateTotalScore() throws Exception {

    }

    @Test
    public void getTotalScore() throws Exception {

    }
}