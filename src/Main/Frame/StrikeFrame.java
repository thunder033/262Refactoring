package Main.Frame;

/**
 * Defines behaviors when a frame has pin counts that result in a Strike
 * Created by gjr8050 on 5/1/2016.
 */
public class StrikeFrame extends FrameHandler {
    @Override
    int calculateFrameScore(Frame frame) {
        int score = frame.getBaseScore();

        if(frame.isLast()){
            if(!frame.isBowled(Ball.THREE))
                return Frame.Unset;
        }
        else
            score = Frame.scoreAdd(score, frame.next.getChainScore(FrameState.STRIKE));

        return score;
    }

    @Override
    void setPinCount(Frame frame, int pinCount) {
        if(frame.isLast()){
            if(!frame.isBowled(Ball.TWO))
                frame.setPinCount(Ball.TWO, pinCount);
            else if(!frame.isBowled(Ball.THREE))
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
                if(frame.isLast())
                    return Frame.scoreAdd(frame.getScore(Ball.ONE), frame.getScore(Ball.TWO));
                else
                    return Frame.scoreAdd(frame.getScore(Ball.ONE), frame.next.getScore(Ball.ONE));
            case SPARE:
                return frame.getScore(Ball.ONE);
            default:
                return 0;
        }
    }

    @Override
    String[] getMarks(Frame frame) {
        /*
        getMark handles the strike case easily, because if a ball was 10 pins, it is a strike,
        and the 2nd ball on a non-last frame is un-bowled, we just need to handle the last frame
         */
        String mark3 = frame.isLast() && frame.getScore(Ball.TWO) + frame.getScore(Ball.THREE) == 10 ? "/" : frame.getMark(Ball.THREE);
        return new String[] {
                frame.getMark(Ball.ONE),
                frame.getMark(Ball.TWO),
                mark3
        };
    }
}