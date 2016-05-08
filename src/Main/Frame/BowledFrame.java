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
                return Frame.scoreAdd(frame.getScore(Ball.ONE), frame.getScore(Ball.TWO));
            case SPARE:
                return frame.getScore(Ball.ONE);
            default:
                return 0;
        }
    }

    @Override
    String[] getMarks(Frame frame) {
        return new String[] {
                frame.getMark(Ball.ONE),
                frame.getMark(Ball.TWO),
                frame.getMark(Ball.THREE)
        };
    }
}
