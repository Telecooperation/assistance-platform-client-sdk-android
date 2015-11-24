package de.tudarmstadt.informatik.tk.android.assistance.sdk.model.sensing.impl.periodic;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import de.tudarmstadt.informatik.tk.android.assistance.sdk.model.api.dto.DtoType;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.model.sensing.AbstractPeriodicEvent;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.model.sensing.ISensor;


public class RunningProcessesReaderEvent extends AbstractPeriodicEvent implements ISensor {

    private ActivityManager m_activityManager;
    // private Query<SensorRunningProcesses> m_query;
    private ArrayList<String> m_liLastProcesses = new ArrayList<String>();

    public RunningProcessesReaderEvent(Context context) {
        super(context);
        setDataIntervallInSec(30);
        m_activityManager = (ActivityManager) this.context.getSystemService(Context.ACTIVITY_SERVICE);
        // m_query =
        // mDaoSession.getSensorRunningProcessesDao().queryBuilder().where(Properties.RunningProcesses.eq("")).build();
    }

    @Override
    public void dumpData() {

    }

    @Override
    public int getType() {
        return DtoType.RUNNING_PROCESSES;
    }

    @Override
    public void reset() {

    }

    @Override
    protected void getData() {

        List<RunningAppProcessInfo> liProcesses = m_activityManager.getRunningAppProcesses();

        ArrayList<String> liProcessNames = new ArrayList<String>(liProcesses.size());
        boolean bProcessesChanged = liProcesses.size() != m_liLastProcesses.size();
        for (RunningAppProcessInfo process : liProcesses) {
            liProcessNames.add(process.processName);
            if (!bProcessesChanged && !m_liLastProcesses.contains(process.processName))
                bProcessesChanged = true;
        }

        if (bProcessesChanged) {
            m_liLastProcesses = liProcessNames;

            long longTimestamp = Calendar.getInstance().getTimeInMillis();
            for (String processName : liProcessNames) {
                // m_query.setParameter(0, processName);
                // List<SensorRunningProcesses> li = m_query.list();
                // if (li == null || li.size() == 0)
                // {
//				SensorRunningProcesses sensor = new SensorRunningProcesses();
//				sensor.setRunningProcesses(processName);
//				sensor.setTimestamp(longTimestamp);
//				handleDBEntry(sensor, false, false, true);
                // }
            }
        }
    }

//	@Override
//	public MessageType getMessageType() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}