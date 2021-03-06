package de.tudarmstadt.informatik.tk.assistance.sdk.provider.dao.sensing.contact;

import android.support.annotation.Nullable;

import org.greenrobot.greendao.Property;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbContactEmailSensor;
import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbContactEmailSensorDao.Properties;
import de.tudarmstadt.informatik.tk.assistance.sdk.model.api.SensorDto;
import de.tudarmstadt.informatik.tk.assistance.sdk.provider.dao.sensing.CommonEventDaoImpl;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 24.11.2015
 */
@Singleton
public final class ContactEmailSensorDaoImpl extends
        CommonEventDaoImpl<DbContactEmailSensor> implements
        ContactEmailSensorDao {

    private static final String TAG = ContactEmailSensorDaoImpl.class.getSimpleName();

    @Inject
    public ContactEmailSensorDaoImpl(DaoSession daoSession) {
        super(daoSession.getDbContactEmailSensorDao());
    }

    @Nullable
    @Override
    public SensorDto convertObject(DbContactEmailSensor sensor) {

        if (sensor == null) {
            return null;
        }

//        ContactEmailEventDto result = new ContactEmailEventDto();
//
//        result.setId(sensor.getId());
//        result.setContactId(sensor.getContactId());
//        result.setMailId(sensor.getMailId());
//        result.setAddress(sensor.getAddress());
//        result.setEventType(sensor.getType());
//        result.setIsNew(sensor.getIsNew());
//        result.setIsUpdated(sensor.getIsUpdated());
//        result.setIsDeleted(sensor.getIsDeleted());
//        result.setType(SensorApiType.CONTACT_EMAIL);
//        result.setTypeStr(SensorApiType.getApiName(SensorApiType.CONTACT_EMAIL));
//        result.setCreated(sensor.getCreated());

        return null;
    }

    @Override
    public List<DbContactEmailSensor> getAll(Long contactId, long deviceId) {

        if (contactId == null || deviceId < 0) {
            return Collections.emptyList();
        }

        for (Property property : properties) {
            if (property.name.equals(DEVICE_ID_FIELD_NAME)) {
                return dao
                        .queryBuilder()
                        .where(Properties.ContactId.eq(contactId))
                        .where(property.eq(deviceId))
                        .build()
                        .list();
            }
        }

        return Collections.emptyList();
    }
}