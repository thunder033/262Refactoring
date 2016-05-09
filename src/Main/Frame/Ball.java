package Main.Frame;

/**
 * provides a type-safe way to reference the pin counts stored in each frame
 * Created by gjr8050 on 5/1/2016.
 */
public enum Ball {
    ONE(0),
    TWO(1),
    THREE(2);

    private final int index;

    Ball(int num){
        this.index = num;
    }

    int getIndex(){
        return index;
    }
}