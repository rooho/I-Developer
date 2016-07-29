// AIDLPollingRequest.aidl
package cc.rooho.aidlidoyoudo.server;
// Declare any non-default types here with import statements
import cc.rooho.aidlidoyoudo.server.AIDLPollingRequestCallback;

interface AIDLPollingRequest {

    void pollingRequest(int code, in AIDLPollingRequestCallback listener);
}
