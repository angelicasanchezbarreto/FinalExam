package cs.exam;
import java.util.logging.Logger;
import java.security.SecureRandom;

public class Sensor {
    private Integer id;
    private Integer co = 0;
    private Integer pm = 0;
    private Integer co2 = 0;
    private float latitude = 0;
    private float longitude = 0;
    private Integer date = 0;
    private Integer hour = 0;

    static final Logger logger = Logger.getLogger(Sensor.class.getName());

    public void displayInfo(){
        var lat = "Latitud: " + this.latitude;
        var lon = "Longitud: " + this.longitude;
        var dateTemp = "Fecha: " + this.date;
        var hourTemp = "Hora: " + this.hour;
        var coTemp = "Monóxido de Carbono (CO): " + this.co;
        var pmTemp = "Material Particulado (PM): " + this.pm;
        var co2Temp = "Dióxido de Carbono (CO2): " + this.co2;
        logger.info(lat);
        logger.info(lon);
        logger.info(dateTemp);
        logger.info(hourTemp);
        logger.info(coTemp);
        logger.info(pmTemp);
        logger.info(co2Temp);
        logger.info("\n");
    }

    public Sensor(Integer id){
        var rand = new SecureRandom();
        this.id = id;
        this.co = rand.nextInt(71);
        this.pm = rand.nextInt(150);
        this.co2 = rand.nextInt(1200 - 300) + 300;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

}
