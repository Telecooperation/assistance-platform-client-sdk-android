package de.tudarmstadt.informatik.tk.android.assistance.sdk.provider.dao.sensing.sensor;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DaoSession;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DbAccelerometerSensor;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DbAccelerometerSensorDao;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.interfaces.IDbSensor;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.model.api.dto.DtoType;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.model.api.dto.sensor.AccelerometerSensorDto;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.model.api.dto.SensorDto;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.provider.dao.sensing.CommonEventDaoImpl;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 30.10.2015
 */
public class AccelerometerSensorDaoImpl extends
        CommonEventDaoImpl implements
        AccelerometerSensorDao {

    private static final String TAG = AccelerometerSensorDaoImpl.class.getSimpleName();

    private static AccelerometerSensorDao INSTANCE;

    private DbAccelerometerSensorDao dao;

    private AccelerometerSensorDaoImpl(DaoSession daoSession) {

        if (dao == null) {
            dao = daoSession.getDbAccelerometerSensorDao();
        }
    }

    public static AccelerometerSensorDao getInstance(DaoSession mDaoSession) {

        if (INSTANCE == null) {
            INSTANCE = new AccelerometerSensorDaoImpl(mDaoSession);
        }

        return INSTANCE;
    }

    /**
     * Converts db object to request object
     *
     * @param dbSensor
     * @return
     */
    @Override
    public AccelerometerSensorDto convertObject(DbAccelerometerSensor dbSensor) {

        if (dbSensor == null) {
            return null;
        }

        AccelerometerSensorDto result = new AccelerometerSensorDto();

        result.setId(dbSensor.getId());
        result.setX(dbSensor.getX());
        result.setY(dbSensor.getY());
        result.setZ(dbSensor.getZ());
        result.setAccuracy(dbSensor.getAccuracy());
        result.setType(DtoType.ACCELEROMETER);
        result.setTypeStr(DtoType.getApiName(DtoType.ACCELEROMETER));
        result.setCreated(dbSensor.getCreated());

        return result;
    }

    /**
     * Converts list of db objects to request objects
     *
     * @param dbSensors
     * @return
     */
    @Override
    public List<SensorDto> convertObjects(List<? extends IDbSensor> dbSensors) {

        List<SensorDto> result = new ArrayList<>();

        if (dbSensors != null && !dbSensors.isEmpty()) {

            for (DbAccelerometerSensor dbSensor : (List<DbAccelerometerSensor>) dbSensors) {
                result.add(convertObject(dbSensor));
            }
        }

        return result;
    }

    @Override
    public List<? extends IDbSensor> getAll() {
        return dao
                .queryBuilder()
                .build()
                .list();
    }

    @Override
    public List<? extends IDbSensor> getFirstN(int amount) {

        if (amount <= 0) {
            return Collections.emptyList();
        }

        return dao
                .queryBuilder()
                .limit(amount)
                .build()
                .list();
    }

    @Override
    public List<? extends IDbSensor> getLastN(int amount) {

        if (amount <= 0) {
            return Collections.emptyList();
        }

        return dao
                .queryBuilder()
                .orderDesc(DbAccelerometerSensorDao.Properties.Id)
                .limit(amount)
                .build()
                .list();
    }

    /**
     * @param sensor
     * @return
     */
    @Override
    public long insert(IDbSensor sensor) {

        if (sensor == null) {
            return -1l;
        }

        Log.d(TAG, "Dumping data to db...");

        long result = dao.insertOrReplace((DbAccelerometerSensor) sensor);

        Log.d(TAG, "Finished dumping data");

        return result;
    }

    @Override
    public void delete(List<? extends IDbSensor> events) {

        if (events == null || events.isEmpty()) {
            return;
        }

        dao.deleteInTx((Iterable<DbAccelerometerSensor>) events);
    }
}