package de.tudarmstadt.informatik.tk.assistance.sdk.provider.dao.sensing;

import android.support.annotation.Nullable;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbLightSensor;
import de.tudarmstadt.informatik.tk.assistance.sdk.model.api.sensing.sensor.LightSensorDto;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 30.10.2015
 */
public final class LightSensorDaoImpl extends
        CommonEventDaoImpl<DbLightSensor> implements
        LightSensorDao {

    private static final String TAG = LightSensorDaoImpl.class.getSimpleName();

    private static LightSensorDao INSTANCE;

    private LightSensorDaoImpl(DaoSession daoSession) {
        super(daoSession.getDbLightSensorDao());
    }

    public static LightSensorDao getInstance(DaoSession mDaoSession) {

        if (INSTANCE == null) {
            INSTANCE = new LightSensorDaoImpl(mDaoSession);
        }

        return INSTANCE;
    }

    @Nullable
    @Override
    public LightSensorDto convertObject(DbLightSensor sensor) {

        if (sensor == null) {
            return null;
        }

        LightSensorDto result = new LightSensorDto();

        result.setAccuracy(sensor.getAccuracy());
        result.setValue(sensor.getValue());
        result.setCreated(sensor.getCreated());

        return result;
    }
}