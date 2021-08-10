import java.util.logging.Logger;

public class Sensor {
    private Integer ID;
    private Integer CO = 0;
    private Integer PM = 0;
    private Integer CO2 = 0;
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
        var COTemp = "Monóxido de Carbono (CO): " + this.CO;
        var PMTemp = "Material Particulado (PM): " + this.PM;
        var CO2Temp = "Dióxido de Carbono (CO2): " + this.CO2;
        logger.info(lat);
        logger.info(lon);
        logger.info(dateTemp);
        logger.info(hourTemp);
        logger.info(COTemp);
        logger.info(PMTemp);
        logger.info(CO2Temp);
        logger.info("\n");
    }

    public Sensor(Integer ID){
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

}
