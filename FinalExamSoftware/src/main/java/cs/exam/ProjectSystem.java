package cs.exam;
import java.util.*;
import java.util.logging.Logger;

public class ProjectSystem {
    private static ProjectSystem instance = null;
    public User currentUser = null;
    private ArrayList<Integer> coInfo = new ArrayList<>();
    private ArrayList<Integer> pmInfo = new ArrayList<>();
    private ArrayList<Integer> co2Info = new ArrayList<>();
    private final Map<String,User> users = new HashMap<>();
    public final Map<String,Sensor> sensors = new HashMap<>();

    static final Logger logger = Logger.getLogger(ProjectSystem.class.getName());
    static Scanner scannerObj = new Scanner(System.in);

    public static ProjectSystem getInstance(){
        if (instance == null){
            instance = new ProjectSystem();
        }
        return instance;
    }

    public Integer userMenu() throws PalindromeException{
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
            getSensorId();
            var id = scannerObj.nextLine();
            printSensor(id);
        } else if(Objects.equals(option,2)){
            getInfoCO();
        } else if(Objects.equals(option,3)){
            getInfoPM();
        } else if(Objects.equals(option,4)){
            getInfoCO2();
        } else if(Objects.equals(option,5)) {
            getSensorId();
            var id = scannerObj.nextLine();
            addSensor(id);
        } else if(Objects.equals(option,6)) {
            getSensorId();
            var id = scannerObj.nextLine();
            removeSensor(id);
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

    void getSensorId(){
        logger.info("Ingresa los datos del sensor:");
        logger.info("id:");
    }

    public Sensor printSensor(String id){
        var sensorFound = findSensor(id);
        if(sensorFound!=null){
            sensorFound.displayInfo();
            return sensorFound;
        } else {
            logger.info("No se pudo encontrar el sensor.");
            return null;
        }
    }

    private void updateCO(Sensor current){
        if(current.getCO() < this.coInfo.get(0)){
            this.coInfo.set(0,current.getCO());
        }
        if(current.getCO() > this.coInfo.get(1)){
            this.coInfo.set(1,current.getCO());
        }
    }

    private void updatePM(Sensor current){
        if(current.getPM() < this.pmInfo.get(0)){
            this.pmInfo.set(0,current.getPM());
        }
        if(current.getPM() > this.pmInfo.get(1)){
            this.pmInfo.set(1,current.getPM());
        }
    }

    private void updateCO2(Sensor current){
        if(current.getCO2() < this.co2Info.get(0)){
            this.co2Info.set(0,current.getCO2());
        }
        if(current.getCO2() > this.co2Info.get(1)){
            this.co2Info.set(1,current.getCO2());
        }
    }

    private void updateInfo(){
        for (Map.Entry<String,Sensor> entry : this.sensors.entrySet()){
            updateCO(entry.getValue());
            updatePM(entry.getValue());
            updateCO2(entry.getValue());
        }
    }

    private void setInfo(Sensor current){
        if(coInfo.isEmpty()){
            coInfo.add(current.getCO());
            coInfo.add(current.getCO());
        }
        if(pmInfo.isEmpty()){
            pmInfo.add(current.getPM());
            pmInfo.add(current.getPM());
        }
        if(co2Info.isEmpty()){
            co2Info.add(current.getCO2());
            co2Info.add(current.getCO2());
        }
    }

    private void getInfoCO(){
        if(this.coInfo.isEmpty()){
            logger.info("No hay información suficiente sobre el CO.");
        }
    }

    private void getInfoPM(){
        if(this.pmInfo.isEmpty()){
            logger.info("No hay información suficiente sobre el PM.");
        }
    }

    private void getInfoCO2(){
        if(this.co2Info.isEmpty()){
            logger.info("No hay información suficiente sobre el CO2.");
        }
    }

    public void addSensor(String id){
        var newSensor = new Sensor(id);
        if(this.sensors.isEmpty()) {
            this.sensors.put(id,newSensor);
            setInfo(newSensor);
        } else {
            var sensorFound = findSensor(id);
            if (sensorFound == null) {
                this.sensors.put(id, newSensor);
                updateInfo();
            } else {
                logger.info("El sensor ya existe.");
            }
        }
    }

    public void removeSensor(String id){
        if(!this.sensors.isEmpty()) {
            var sensorFound = findSensor(id);
            if(sensorFound!=null){
                this.sensors.remove(id);
                updateInfo();
            } else {
                logger.info("No se pudo encontrar el sensor.");
            }
        } else {
            logger.info("No hay sensores registrados.");
        }
    }

    private Sensor findSensor(String id){
        if(this.sensors.containsKey(id)){
            return this.sensors.get(id);
        } else {
            return null;
        }
    }

    public void login(String usernameTemp, String passwordTemp) throws PalindromeException{
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

    public void register(String usernameTemp, User newUser){
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

    public void logout(){
        this.currentUser = null;
    }
}

