package Main.Frame;

/**
 * Created by gjr8050 on 5/1/2016.
 */
enum FrameState {
    ACTIVE(new ActiveFrame()),
    BOWLED(new BowledFrame()),
    STRIKE(new StrikeFrame()),
    SPARE(new SpareFrame());

    private final FrameHandler state;

    FrameState(FrameHandler handler){
        state = handler;
    }

    public void setPinCount(Frame frame, int count){
        state.setPinCount(frame, count);
    }

    public int calculateFrameScore(Frame frame){
        return state.calculateFrameScore(frame);
    }

    public int getChainScore(Frame frame, FrameState forState){
        return state.getChainScore(frame, forState);
    }
}