package Main.Frame;

/**
 * Created by gjr8050 on 5/1/2016.
 */
public class BowledFrame extends FrameHandler {
    @Override
    int calculateFrameScore(Frame frame) {
        return frame.getBaseScore();
    }

    @Override
    void setPinCount(Frame frame, int pinCount) {
        if(frame.isLast())
            throw new UnsupportedOperationException("No valid frame to set ball on");

        frame.next.setPinCount(pinCount);
    }

    @Override
    int getChainScore(Frame frame, FrameState forState) {
        switch(forState){
            case STRIKE:
                return frame.getPinCount(Ball.ONE) + frame.getPinCount(Ball.TWO);
            case SPARE:
                return frame.getPinCount(Ball.ONE);
            default:
                return 0;
        }
    }
}
