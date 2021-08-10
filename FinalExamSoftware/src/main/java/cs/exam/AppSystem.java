package cs.exam;
import java.util.logging.Logger;

public class AppSystem {

    static final Logger logger = Logger.getLogger(AppSystem.class.getName());
    static final  ProjectSystem projectSystem = ProjectSystem.getInstance();

    public static void main(String[] args){
        logger.info("¡Bienvenido a <<El aire que respiras>>!");
        while(true){
            if(projectSystem.userMenu().equals(-1)){
                break;
            }
        }
    }



}
