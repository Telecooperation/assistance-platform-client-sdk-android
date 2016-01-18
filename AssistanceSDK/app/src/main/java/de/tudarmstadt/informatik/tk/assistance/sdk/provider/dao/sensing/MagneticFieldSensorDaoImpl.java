package de.tudarmstadt.informatik.tk.assistance.sdk.provider.dao.sensing;

import android.support.annotation.Nullable;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbMagneticFieldSensor;
import de.tudarmstadt.informatik.tk.assistance.sdk.model.api.sensing.sensor.MagneticFieldSensorDto;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 30.10.2015
 */
public class MagneticFieldSensorDaoImpl extends
        CommonEventDaoImpl<DbMagneticFieldSensor> implements
        MagneticFieldSensorDao {

    private static final String TAG = MagneticFieldSensorDaoImpl.class.getSimpleName();

    private static MagneticFieldSensorDao INSTANCE;

    private MagneticFieldSensorDaoImpl(DaoSession daoSession) {
        super(daoSession.getDbMagneticFieldSensorDao());
    }

    public static MagneticFieldSensorDao getInstance(DaoSession mDaoSession) {

        if (INSTANCE == null) {
            INSTANCE = new MagneticFieldSensorDaoImpl(mDaoSession);
        }

        return INSTANCE;
    }

    @Nullable
    @Override
    public MagneticFieldSensorDto convertObject(DbMagneticFieldSensor sensor) {

        if (sensor == null) {
            return null;
        }

        MagneticFieldSensorDto result = new MagneticFieldSensorDto();

        result.setX(sensor.getX());
        result.setY(sensor.getY());
        result.setZ(sensor.getZ());
        result.setAccuracy(sensor.getAccuracy());
        result.setxUncalibratedNoHardIron(sensor.getXUncalibratedNoHardIron());
        result.setyUncalibratedNoHardIron(sensor.getYUncalibratedNoHardIron());
        result.setzUncalibratedNoHardIron(sensor.getZUncalibratedNoHardIron());
        result.setxUncalibratedEstimatedIronBias(sensor.getXUncalibratedEstimatedIronBias());
        result.setyUncalibratedEstimatedIronBias(sensor.getYUncalibratedEstimatedIronBias());
        result.setzUncalibratedEstimatedIronBias(sensor.getZUncalibratedEstimatedIronBias());
        result.setCreated(sensor.getCreated());

        return result;
    }
}