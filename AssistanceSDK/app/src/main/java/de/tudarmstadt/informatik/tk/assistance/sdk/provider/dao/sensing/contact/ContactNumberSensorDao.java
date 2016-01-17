package de.tudarmstadt.informatik.tk.assistance.sdk.provider.dao.sensing.contact;

import java.util.List;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbContactNumberSensor;
import de.tudarmstadt.informatik.tk.assistance.sdk.provider.dao.sensing.common.CommonEventDao;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 24.11.2015
 */
public interface ContactNumberSensorDao extends CommonEventDao<DbContactNumberSensor> {

    List<DbContactNumberSensor> getAll(Long contactId);
}