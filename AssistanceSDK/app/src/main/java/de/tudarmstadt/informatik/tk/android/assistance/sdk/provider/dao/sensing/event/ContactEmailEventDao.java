package de.tudarmstadt.informatik.tk.android.assistance.sdk.provider.dao.sensing.event;

import java.util.List;

import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DbContactEmailEvent;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.provider.dao.sensing.CommonEventDao;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 24.11.2015
 */
public interface ContactEmailEventDao extends CommonEventDao<DbContactEmailEvent> {

    List<DbContactEmailEvent> getAll(Long contactId);

}
