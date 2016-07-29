// AIDLPollingRequest.aidl
package cc.rooho.aidlidoyoudo.server;
// Declare any non-default types here with import statements
import cc.rooho.aidlidoyoudo.server.AIDLPollingRequestCallback;

interface AIDLPollingRequest {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void pollingRequest(int code, in AIDLPollingRequestCallback listener);
}
