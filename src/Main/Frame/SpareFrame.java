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
        else
            score = Frame.scoreAdd(score, frame.next.getChainScore(FrameState.SPARE));

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
                return Frame.scoreAdd(frame.getScore(Ball.ONE), frame.getScore(Ball.TWO));
            case SPARE:
                return frame.getScore(Ball.ONE);
            default:
                return 0;
        }
    }

    @Override
    String[] getMarks(Frame frame) {
        return new String[]{
                frame.getMark(Ball.ONE),
                "/",
                frame.getMark(Ball.THREE)
        };
    }
}