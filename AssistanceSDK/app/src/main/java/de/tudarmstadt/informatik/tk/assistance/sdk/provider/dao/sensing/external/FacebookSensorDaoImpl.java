package de.tudarmstadt.informatik.tk.assistance.sdk.provider.dao.sensing.external;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbFacebookSensor;
import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbFacebookSensorDao.Properties;
import de.tudarmstadt.informatik.tk.assistance.sdk.model.api.SensorDto;
import de.tudarmstadt.informatik.tk.assistance.sdk.model.api.sensing.sensor.external.FacebookSensorDto;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 17.01.2016
 */
@Singleton
public final class FacebookSensorDaoImpl extends
        CommonSocialEventDaoImpl<DbFacebookSensor> implements
        FacebookSensorDao {

    private static final String TAG = FacebookSensorDaoImpl.class.getSimpleName();

    @Inject
    public FacebookSensorDaoImpl(DaoSession daoSession) {
        super(daoSession.getDbFacebookSensorDao());
    }

    @Nullable
    @Override
    public SensorDto convertObject(DbFacebookSensor sensor) {

        if (sensor == null) {
            return null;
        }

        Set<String> perms = new HashSet<>();

        if (sensor.getPermissions() != null) {
            Gson gson = new Gson();
            perms = gson.fromJson(sensor.getPermissions(), new TypeToken<Set<String>>() {
            }.getType());
        }

        FacebookSensorDto result = new FacebookSensorDto(
                sensor.getOauthToken(),
                perms,
                sensor.getCreated()
        );

        return result;
    }

    @Nullable
    @Override
    public DbFacebookSensor getForUserId(Long userId) {

        if (userId == null) {
            return null;
        }

        return dao
                .queryBuilder()
                .where(Properties.UserId.eq(userId))
                .limit(1)
                .build()
                .unique();
    }

    @Nullable
    @Override
    public DbFacebookSensor getIfChangedForUserId(Long userId) {

        if (userId == null) {
            return null;
        }

        return dao
                .queryBuilder()
                .where(Properties.UserId.eq(userId))
                .where(Properties.WasChanged.eq(Boolean.TRUE))
                .limit(1)
                .build()
                .unique();
    }
}