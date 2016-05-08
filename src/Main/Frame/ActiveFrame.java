package Main.Frame;

/**
 * Created by gjr8050 on 5/1/2016.
 */
public class ActiveFrame extends FrameHandler {
    @Override
    int calculateFrameScore(Frame frame) {
        return Frame.Unset;
    }

    @Override
    void setPinCount(Frame frame, int count) {
        if(!frame.isBowled(Ball.ONE))
            frame.setPinCount(Ball.ONE, count);
        else if(!frame.isBowled(Ball.TWO))
            frame.setPinCount(Ball.TWO, count);

        if(frame.isStrike())
            frame.setFrameState(FrameState.STRIKE);
        else if(frame.isSpare())
            frame.setFrameState(FrameState.SPARE);
        else if(frame.isBowled(Ball.TWO))
            frame.setFrameState(FrameState.BOWLED);
    }

    @Override
    int getChainScore(Frame frame, FrameState state) {
        return 0;
    }

    @Override
    String[] getMarks(Frame frame) {
        return new String[]{"","",""};
    }
}