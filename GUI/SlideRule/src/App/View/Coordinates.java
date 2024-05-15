package App.View;

public class Coordinates {

    private int x;
    private int y;

    public Coordinates(){
        x = 0;
        y = 0;
    }

    public int GetX(){
        return x;
    }

    public int GetY(){
        return y;
    }

    public void Set(int x, int y){
        this.x = x;
        this.y = y;
    }
}
