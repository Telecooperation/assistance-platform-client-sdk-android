package de.tudarmstadt.informatik.tk.android.kraken.model.sensor.impl.periodic.incomplete;

import android.content.Context;

import de.tudarmstadt.informatik.tk.android.kraken.model.enums.ESensorType;
import de.tudarmstadt.informatik.tk.android.kraken.model.sensor.AbstractPeriodicEvent;
import de.tudarmstadt.informatik.tk.android.kraken.model.sensor.ISensor;

public class AlarmClockReaderEvent extends AbstractPeriodicEvent implements ISensor {

    public AlarmClockReaderEvent(Context context) {
        super(context);

        // http://stackoverflow.com/questions/11492517/how-can-i-get-the-clock-alarms-in-android
        // geht nicht :(
    }

    @Override
    protected void dumpData() {

    }

    @Override
    public ESensorType getSensorType() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reset() {

    }

    @Override
    protected void getData() {
        // TODO Auto-generated method stub

    }

    @Override
    protected int getDataIntervallInSec() {
        return 3600;
    }

//	@Override
//	public MessageType getMessageType() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}