package Main.Frame;

/**
 * Defines operations that will be handled through the state machine
 * Created by gjr8050 on 5/1/2016.
 */

abstract class FrameHandler {

    /**
     * Calculates the number of points that a frame contributes to the total score
     * @param frame the frame to calculate points for
     * @return score or Frame.Unset if can't yet be calculated
     */
    abstract int calculateFrameScore(Frame frame);

    /**
     * Sets the next pin count on a frame or passes to the next frame if frame has been bowled
     * @param frame frame to set pin count on
     * @param pinCount number of pins
     */
    abstract void setPinCount(Frame frame, int pinCount);

    /**
     * Gets the number of points this frame would contribute to a special scoring case on a previous frame
     * @param frame the frame to get points from
     * @param forState the type of special condition for which points should be calculated
     * @return points or Frame.Unset if can't be determined
     */
    abstract int getChainScore(Frame frame, FrameState forState);

    /**
     * Gets a series of string marks to be displayed in scoring interface
     * @param frame frame to determine marks for
     * @return a String array of length 3 with appropriate scoring marks
     */
    abstract String[] getMarks(Frame frame);
}