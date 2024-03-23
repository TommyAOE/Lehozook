public class Chart {
    Chart(boolean init){
        System.out.println("Chart created");
        new Room(1);
        System.out.print(1+".");
        new Room().SetNeighbours();
        new Room(2);
        System.out.print(2+".");
        new Room().SetNeighbours();
    }
    Chart(){
    }
    public void IterateForRoomChanges(){
        System.out.println("Chart: IterateForRoomChanges()");
    }
}
