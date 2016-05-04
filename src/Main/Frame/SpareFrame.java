package Main.Frame;

/**
 * Created by gjr8050 on 5/1/2016.
 */
public class SpareFrame extends FrameHandler {
    @Override
    int calculateFrameScore(Frame frame) {
        int score = frame.getBaseScore();

        if(frame.isLast()){
            if(!frame.isBowled(Ball.THREE))
                return Frame.Unset;
        }
        else {
            if(frame.next.isBowled(Ball.ONE))
                score += frame.next.getChainScore(FrameState.SPARE);
            else
                return Frame.Unset;
        }

        return score;
    }

    @Override
    void setPinCount(Frame frame, int pinCount) {
        if(frame.isLast()){
            if(!frame.isBowled(Ball.THREE))
                frame.setPinCount(Ball.THREE, pinCount);
            else
                throw new UnsupportedOperationException("No valid frame to set ball on");
        }
        else
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