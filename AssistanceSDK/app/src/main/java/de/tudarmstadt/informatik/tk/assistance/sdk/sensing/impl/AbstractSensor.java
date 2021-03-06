package de.tudarmstadt.informatik.tk.assistance.sdk.sensing.impl;

import android.content.Context;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import de.tudarmstadt.informatik.tk.assistance.sdk.event.UpdateSensorIntervalEvent;
import de.tudarmstadt.informatik.tk.assistance.sdk.model.enums.EPushType;
import de.tudarmstadt.informatik.tk.assistance.sdk.provider.DaoProvider;
import de.tudarmstadt.informatik.tk.assistance.sdk.sensing.ISensor;

/**
 * @author Unknown
 * @edited by Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 26.10.2015
 */
public abstract class AbstractSensor implements ISensor {

    protected Context context;

    private boolean isRunning;

    protected DaoSession mDaoSession;
    protected DaoProvider daoProvider;

    private boolean isDisabledByUser;
    private boolean isDisabledBySystem;

    public AbstractSensor(Context context) {
        this.context = context;

        if (mDaoSession == null) {
            mDaoSession = DaoProvider.getInstance(context).getDaoSession();
        }

        if (daoProvider == null) {
            daoProvider = DaoProvider.getInstance(context);
        }
    }

    @Override
    public void setDisabledBySystem(boolean isDisabled) {
        isDisabledBySystem = isDisabled;
    }

    @Override
    public boolean isDisabledBySystem() {
        return isDisabledBySystem;
    }

    @Override
    public void setDisabledByUser(boolean isDisabled) {
        isDisabledByUser = isDisabled;
    }

    @Override
    public boolean isDisabledByUser() {
        return isDisabledByUser;
    }

    @Override
    public EPushType getPushType() {
        return EPushType.WLAN_ONLY;
    }

    @Override
    public void setDaoSession(DaoSession daoSession) {
        this.mDaoSession = daoSession;
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public void setRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    /**
     * Update event for sensor sampling interval
     *
     * @param event
     */
    public void onEvent(UpdateSensorIntervalEvent event) {

        // only accept this sensor topic type
        if (event.getDtoType() != getType()) {
            return;
        }

        updateSensorInterval(event.getCollectionInterval());
    }
}