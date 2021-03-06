package de.tudarmstadt.informatik.tk.assistance.sdk.model.api.sensing.sensor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import de.tudarmstadt.informatik.tk.assistance.sdk.model.api.SensorDto;
import de.tudarmstadt.informatik.tk.assistance.sdk.model.api.sensing.SensorApiType;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 30.08.2015
 */
public class AccelerometerSensorDto implements SensorDto {

    @SerializedName("x")
    @Expose
    private Double x;

    @SerializedName("y")
    @Expose
    private Double y;

    @SerializedName("z")
    @Expose
    private Double z;

    /**
     * Not-null value.
     */
    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("accuracy")
    @Expose
    private Integer accuracy;

    @SerializedName("type")
    @Expose
    private String typeStr;

    private int type;

    public AccelerometerSensorDto() {
        this.type = SensorApiType.ACCELEROMETER;
        this.typeStr = SensorApiType.getApiName(this.type);
    }

    public AccelerometerSensorDto(Double x, Double y, Double z, String created, Integer accuracy) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.created = created;
        this.accuracy = accuracy;
        this.type = SensorApiType.ACCELEROMETER;
        this.typeStr = SensorApiType.getApiName(this.type);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getZ() {
        return z;
    }

    public void setZ(Double z) {
        this.z = z;
    }

    /**
     * Not-null value.
     */
    @Override
    public String getCreated() {
        return created;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    @Override
    public int getType() {
        return type;
    }

    public String getTypeStr() {
        return this.typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    @Override
    public String toString() {
        return "AccelerometerSensorRequest{" +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", created='" + created + '\'' +
                ", accuracy=" + accuracy +
                ", typeStr='" + typeStr + '\'' +
                ", type=" + type +
                '}';
    }
}
