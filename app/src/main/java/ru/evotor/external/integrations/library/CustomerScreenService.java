package ru.evotor.external.integrations.library;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class CustomerScreenService extends Service {

    private Intent startIntent = null;

    private final IBinder binder = new IAppRequest.Stub() {
        @Override
        public String execute(String request, Intent intent) throws RemoteException {
            try {
                startIntent = intent;
                return CustomerScreenService.this.execute(request);
            } catch (Throwable t) {
                throw new RemoteException(t.getMessage());
            }
        }
    };

    @Override
    public final IBinder onBind(Intent intent) {
        return binder;
    }

    public final void startActivity(Intent intent) {
        Parcel p = Parcel.obtain();
        try {
            intent.writeToParcel(p, 0);
            p.setDataPosition(0);
            startIntent.readFromParcel(p);
        } finally {
            p.recycle();
        }
    }

    protected abstract String execute(String request) throws Throwable;

}
