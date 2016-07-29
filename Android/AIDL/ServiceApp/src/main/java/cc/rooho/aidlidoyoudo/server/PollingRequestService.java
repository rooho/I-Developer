package cc.rooho.aidlidoyoudo.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * package : com.skydragon.service
 * <p/>
 * Description :
 *
 * @author Y.J.ZHOU
 * @date 2016/5/26 14:43.
 */
public class PollingRequestService extends Service {

    private AIDLPollingRequest.Stub mPollingRequestBinder = new AIDLPollingRequest.Stub(){
        @Override
        public void pollingRequest(int code, AIDLPollingRequestCallback listener) throws RemoteException {
            startServiceWorker(listener);
        }
    };

    public static void startServiceWorker(AIDLPollingRequestCallback listener){
        ServiceWorker serviceWorker = new ServiceWorker(listener);
        new Thread(serviceWorker).start();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mPollingRequestBinder;
    }

    static class ServiceWorker implements Runnable{

        private AIDLPollingRequestCallback mListener;
        public ServiceWorker(AIDLPollingRequestCallback listener){
            mListener = listener;
        }

        @Override
        public void run() {
            long counter = 0;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                jsonArray.put("2333");
                jsonArray.put("2333");
                jsonObject.put("jsonArray", jsonArray);
                jsonObject.put("code", 100);
                mListener.onPollingResult(new PollingResult(23333, "23333", jsonObject));
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };
}
