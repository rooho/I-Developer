package cc.rooho.aidlidoyoudo.server;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * package : com.androidtst.serviceapp
 * <p>
 * Description :
 *
 * @author Y.J.ZHOU
 * @date 2016/5/26 15:41.
 */
public final class PollingResult implements Parcelable{

    private int code;
    private String message;
    private JSONObject data;
    public PollingResult(){}

    public PollingResult(int code, String msg, JSONObject data){
        this.code = code;
        this.message = msg;
        this.data = data;
    }

    public PollingResult(Parcel in){
        readFromParcel(in);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public void readFromParcel(Parcel in) {
        this.code = in.readInt();
        this.message = in.readString();
        String jsonStr = in.readString();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            this.data = jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(code);
        dest.writeString(message);
        dest.writeString(data.toString());
    }

    public static final Creator<PollingResult> CREATOR = new
            Creator<PollingResult>() {
                public PollingResult createFromParcel(Parcel in) {
                    return new PollingResult(in);
                }

                public PollingResult[] newArray(int size) {
                    return new PollingResult[size];
                }
    };

    @Override
    public String toString() {
        return "[code : " + code + " , message : " + message + "  , data : " + data +"]";
    }
}
