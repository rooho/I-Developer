package cc.rooho.aidlidoyoudo.client;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import cc.rooho.aidlidoyoudo.server.AIDLPollingRequest;
import cc.rooho.aidlidoyoudo.server.AIDLPollingRequestCallback;
import cc.rooho.aidlidoyoudo.server.PollingResult;

/**
 * package : com.skydragon.testandroid
 * <p/>
 * Description :
 *
 * @author Y.J.ZHOU
 * @date 2016/5/6 17:29.
 */
public class ClientActivity extends Activity {

    // Service Action
    private static final String ACTION_BIND_SERVICE = "cc.rooho.aidlidoyoudo.server.polling_request_service";
    private AIDLPollingRequest mPollingRequestBinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_page);
    }

    public void onStartPollingRequest(View view){
        Intent intentService = new Intent(ACTION_BIND_SERVICE);
        intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ClientActivity.this.bindService(intentService, mServiceConnect, BIND_AUTO_CREATE);
    }

    private ServiceConnection mServiceConnect = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPollingRequestBinder = AIDLPollingRequest.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };

    public void onInvorkPollingRequest(View view){
        try {
            Log.v("", "Client !! onInvorkPollingRequest !! ");
            mPollingRequestBinder.pollingRequest(-1, new AIDLPollingRequestCallback.Stub(){
                @Override
                public void onPollingResult(PollingResult pollingResult) throws RemoteException {
                    Log.v("", "Client !! result : " + pollingResult.toString());
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


}
