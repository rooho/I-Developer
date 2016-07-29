// AIDLPollingRequestCallback.aidl
package cc.rooho.aidlidoyoudo.server;
import cc.rooho.aidlidoyoudo.server.PollingResult;
interface AIDLPollingRequestCallback {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
    void onPollingResult(int result);
     */
    void onPollingResult(in PollingResult result);
}
