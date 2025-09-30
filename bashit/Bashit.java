package bashit;
import bashLogic.BashLogic;

public class Bashit {
    public static IOBashit getIOBash(){
        return new BashLogic();
    }
}
