import java.util.*;
import java.util.logging.Logger;

public class ProjectSystem {
    private User currentUser = null;
    private ArrayList<Integer> COInfo = new ArrayList<>();
    private ArrayList<Integer> PMInfo = new ArrayList<>();
    private ArrayList<Integer> CO2Info = new ArrayList<>();
    private final Map<String,User> users = new HashMap<>();
    private final Map<Integer,Sensor> sensors = new HashMap<>();

    static final Logger logger = Logger.getLogger(ProjectSystem.class.getName());
    static Scanner scannerObj = new Scanner(System.in);

    public Integer userMenu(){
        logger.info("1. Registrarse");
        logger.info("2. Ingresar");
        logger.info("3. Salir");
        logger.info("Ingresar número:");
        var option = scannerObj.nextInt();
        scannerObj.nextLine();
        if(Objects.equals(option, 3)){
            return -1;
        }

        logger.info("Ingresa tus datos:");
        logger.info("Username:");
        var usernameTemp = scannerObj.nextLine();
        logger.info("Password:");
        var passwordTemp = scannerObj.nextLine();
        if(Objects.equals(option, 1)){
            var newUser = new User(usernameTemp, passwordTemp);
            if(newUser.getUsername()!=null){
                register(usernameTemp, newUser);
            } else {
                return 0;
            }
        } else {
            login(usernameTemp,passwordTemp);
        }
        while(this.currentUser != null){
            vaccinationMenu();
        }
        return 0;
    }

    private void vaccinationMenu(){
        printMenu();
        logger.info("Ingresar número:");
        var option = scannerObj.nextInt();
        scannerObj.nextLine();
        if(Objects.equals(option,1)){
            printSensor();
        } else if(Objects.equals(option,2)){
            getInfoCO();
        } else if(Objects.equals(option,3)){
            getInfoPM();
        } else if(Objects.equals(option,4)){
            getInfoCO2();
        } else if(Objects.equals(option,5)) {
            addSensor();
        } else if(Objects.equals(option,6)) {
            removeSensor();
        } else {
            logout();
        }
    }

    private void printMenu(){
        logger.info("¿Qué desea ver/hacer?");
        logger.info("1. Información detallada de un sensor.");
        logger.info("2. Información sobre el CO.");
        logger.info("3. Información sobre el PM.");
        logger.info("4. Información sobre el CO2.");
        logger.info("5. Agregar un sensor.");
        logger.info("6. Eliminar un sensor.");
        logger.info("7. Cerrar sesión");
    }

    private Integer getSensorId(){
        logger.info("Ingresa los datos del sensor:");
        logger.info("ID:");
        var ID = scannerObj.nextInt();
        scannerObj.nextLine();
        return ID;
    }

    private void printSensor(){
        var ID = getSensorId();
        var sensorFound = findSensor(ID);
        if(sensorFound!=null){
            sensorFound.displayInfo();
        } else {
            logger.info("No se pudo encontrar el sensor.");
        }
    }

    private void getInfoCO(){

    }

    private void getInfoPM(){

    }

    private void getInfoCO2(){

    }

    private void addSensor(){
        var ID = getSensorId();
        var newSensor = new Sensor(ID);
        if(this.sensors.isEmpty()) {
            this.sensors.put(ID,newSensor);
        } else {
            var sensorFound = findSensor(ID);
            if (sensorFound == null) {
                this.sensors.put(ID, newSensor);
            } else {
                logger.info("El sensor ya existe.");
            }
        }
    }

    private void removeSensor(){
        var ID = getSensorId();
        if(!this.sensors.isEmpty()) {
            var sensorFound = findSensor(ID);
            if(sensorFound!=null){
                this.sensors.remove(ID);
            } else {
                logger.info("No se pudo encontrar el sensor.");
            }
        } else {
            logger.info("No hay sensores registrados.");
        }
    }

    private Sensor findSensor(Integer ID){
        if(this.sensors.containsKey(ID)){
            return this.sensors.get(ID);
        } else {
            return null;
        }
    }

    private void login(String usernameTemp, String passwordTemp){
        if(!this.users.isEmpty()) {
            if (this.users.containsKey(usernameTemp)) {
                if (users.get(usernameTemp).getPassword().equals(passwordTemp)) {
                    if (this.currentUser == null) {
                        this.currentUser = new User(usernameTemp, passwordTemp);
                    } else {
                        logger.info("Imposible iniciar sesión.");
                    }
                } else {
                    logger.info("Contraseña incorrecta.");
                }
            } else {
                logger.info("Usuario no encontrado.");
            }
        } else {
            logger.info("Ningún usuario encontrado.");
        }
    }

    private void register(String usernameTemp, User newUser){
        if(this.users.isEmpty()){
            this.users.put(usernameTemp, newUser);
            this.currentUser = newUser;
        } else{
            if(!users.containsKey(usernameTemp)) {
                this.users.put(usernameTemp, newUser);
                this.currentUser = newUser;
            } else {
                logger.info("Usuario ya existente.");
            }
        }
    }

    private void logout(){
        this.currentUser = null;
    }
}

