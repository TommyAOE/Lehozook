
public class Scheduler {
    Scheduler(boolean init){
        System.out.println("Scheduler created");
        new Chart(true);
        int i = 1;
        do {
            new Student(i++);
        }while (SkeletonHelper.AddMoreStudents());
        i=1;
        do {
            new Professor(i++);
        }while (SkeletonHelper.AddMoreProfessors());
    }
    Scheduler(){
    }
    public void Play(){
        System.out.println("Scheduler: Play()");
    }
}
