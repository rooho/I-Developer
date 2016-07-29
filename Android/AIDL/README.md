# Android AIDL
AIDL  (Android Interface Definition Language) 主要是用于 Android 的 IPC 通信。典型的使用场景就是创建一个 Service 并且要允许其他不同的应用程序**同时**来访问你的 IPC 服务。这样意味着 AIDL 是**非线程安全**的，所以实现的时候需要保证代码的线程安全性。

**创建 AIDL 文件 :** (详情请参考工程)
通过 AIDLPollingRequest 来传入一个 AIDLPollingRequestCallback 回调接口，返回一个 PollingResult 类型的数据。
AIDLPollingRequest.aidl
```java 
package cc.rooho.aidlidoyoudo.server;
import cc.rooho.aidlidoyoudo.server.AIDLPollingRequestCallback;
interface AIDLPollingRequest {
    void pollingRequest(int code, in AIDLPollingRequestCallback listener);
}
```
AIDLPollingRequestCallback.aidl
```java 
package cc.rooho.aidlidoyoudo.server;
import cc.rooho.aidlidoyoudo.server.PollingResult;
interface AIDLPollingRequestCallback {
    void onPollingResult(in PollingResult result);
}
```
PollingResult.aidl
```java 
package cc.rooho.aidlidoyoudo.server;
parcelable PollingResult;
```
>服务器应用程序与客户端都需要有这些 AIDL 文件。在配置好AIDL后IDE会自动为这些接口生成相应的java文件。

**## 创建服务 Module :**
PollingRequestService.java
```java
public class PollingRequestService.java extends Service {
    private AIDLPollingRequest.Stub mPollingRequestBinder = new AIDLPollingRequest.Stub(){
        @Override
        public void pollingRequest(int code, AIDLPollingRequestCallback listener) throws RemoteException {
            startServiceWorker(listener);
        }
    };
    @Override
    public IBinder onBind(Intent intent) {
        // 返回绑定 AIDL 接口
        return mPollingRequestBinder;
    }
    ... ...
}
```
> 同时要将此服务设置为 android:process=":remote" ，以便让外部的app使用。

PollingResult.java (具体的回调类型，必须要实现 Parcelable 接口)
```java
public final class PollingResult implements Parcelable{
    private int code;
    private String message;
    private JSONObject data;
    public PollingResult(){}
    public void readFromParcel(Parcel in) {
        ...
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        ...
    }
    public static final Creator<PollingResult> CREATOR = new
            Creator<PollingResult>() { ... };
    ... ...
}
```


**## 创建客户端 Module :**

```java
public class ClientActivity extends Activity {
    // Service Action
    private static final String ACTION_BIND_SERVICE = "cc.rooho.aidlidoyoudo.server.polling_request_service";
    private AIDLPollingRequest mPollingRequestBinder;
    // bind service
    public void onStartPollingRequest(View view){
        Intent intentService = new Intent(ACTION_BIND_SERVICE);
        intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ClientActivity.this.bindService(intentService, mServiceConnect, BIND_AUTO_CREATE);
    }
    // 获取接口
    private ServiceConnection mServiceConnect = new ServiceConnection(){
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mPollingRequestBinder = AIDLPollingRequest.Stub.asInterface(service);
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {}
    };
    //调用接口
    public void onInvorkPollingRequest(View view){
        try {
            mPollingRequestBinder.pollingRequest(-1, new AIDLPollingRequestCallback.Stub(){
                @Override
                public void onPollingResult(PollingResult pollingResult) throws RemoteException {
                    // 接口回调
                }
            });
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
```