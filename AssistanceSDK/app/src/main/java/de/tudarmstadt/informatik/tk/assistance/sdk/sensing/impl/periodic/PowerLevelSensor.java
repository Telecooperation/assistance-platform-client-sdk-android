package de.tudarmstadt.informatik.tk.assistance.sdk.sensing.impl.periodic;

import android.content.Context;

import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbPowerLevelSensor;
import de.tudarmstadt.informatik.tk.assistance.sdk.model.api.sensing.SensorApiType;
import de.tudarmstadt.informatik.tk.assistance.sdk.provider.PreferenceProvider;
import de.tudarmstadt.informatik.tk.assistance.sdk.sensing.impl.AbstractPeriodicSensor;
import de.tudarmstadt.informatik.tk.assistance.sdk.util.BatteryUtils;
import de.tudarmstadt.informatik.tk.assistance.sdk.util.DateUtils;
import de.tudarmstadt.informatik.tk.assistance.sdk.util.logger.Log;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 08.12.2015
 */
@Singleton
public final class PowerLevelSensor extends AbstractPeriodicSensor {

    private static final String TAG = PowerLevelSensor.class.getSimpleName();

    private int UPDATE_INTERVAL_IN_SEC = 900;

    private float lastPercentValue;

    @Inject
    public PowerLevelSensor(Context context) {
        super(context);

        setDataIntervalInSec(UPDATE_INTERVAL_IN_SEC);
    }

    @Override
    protected void getData() {

        Log.d(TAG, "getData invoked");

        lastPercentValue = BatteryUtils.getBatteryPercentage(context);

        dumpData();
    }

    @Override
    public int getType() {
        return SensorApiType.POWER_LEVEL;
    }

    @Override
    public void reset() {

        this.lastPercentValue = 0.0f;
    }

    @Override
    public void dumpData() {

        long deviceId = PreferenceProvider.getInstance(context).getCurrentDeviceId();

        DbPowerLevelSensor powerLevelEvent = new DbPowerLevelSensor();

        powerLevelEvent.setPercent(lastPercentValue);
        powerLevelEvent.setCreated(DateUtils.dateToISO8601String(new Date(), Locale.getDefault()));
        powerLevelEvent.setDeviceId(deviceId);

        Log.d(TAG, "Insert entry");

        daoProvider.getPowerLevelSensorDao().insert(powerLevelEvent);

        Log.d(TAG, "Finished");
    }

    /**
     * Update intervals
     */
    @Override
    public void updateSensorInterval(Double collectionInterval) {

        Log.d(TAG, "onUpdate interval");
        Log.d(TAG, "Old update interval: " + UPDATE_INTERVAL_IN_SEC + " sec");

        int newUpdateIntervalInSec = collectionInterval.intValue();

        Log.d(TAG, "New update interval: " + newUpdateIntervalInSec + " sec");

        this.UPDATE_INTERVAL_IN_SEC = newUpdateIntervalInSec;
        setDataIntervalInSec(newUpdateIntervalInSec);
    }
}