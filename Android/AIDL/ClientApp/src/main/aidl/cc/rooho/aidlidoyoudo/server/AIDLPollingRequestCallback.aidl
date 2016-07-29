// AIDLPollingRequestCallback.aidl
package cc.rooho.aidlidoyoudo.server;
import cc.rooho.aidlidoyoudo.server.PollingResult;
interface AIDLPollingRequestCallback {

    void onPollingResult(in PollingResult result);
}
