package Main.Frame;

/**
 * Created by gjr8050 on 5/1/2016.
 */

abstract class FrameHandler {

    abstract int calculateFrameScore(Frame frame);

    abstract void setPinCount(Frame frame, int pinCount);

    abstract int getChainScore(Frame frame, FrameState state);
}