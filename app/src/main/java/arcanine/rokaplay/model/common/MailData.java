package arcanine.rokaplay.model.common;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Guru karthik on 06-12-2016.
 */

public class MailData implements Parcelable {

    private int id;
    private String subject;
    private String time;
    private String messageBody;

    public MailData(int id, String subject, String time, String messageBody) {
        this.id = id;
        this.subject = subject;
        this.time = time;
        this.messageBody = messageBody;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    protected MailData(Parcel in) {
        id = in.readInt();
        subject = in.readString();
        time = in.readString();
        messageBody = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(subject);
        dest.writeString(time);
        dest.writeString(messageBody);
    }

    @SuppressWarnings("unused")
    public static final Creator<MailData> CREATOR = new Creator<MailData>() {
        @Override
        public MailData createFromParcel(Parcel in) {
            return new MailData(in);
        }

        @Override
        public MailData[] newArray(int size) {
            return new MailData[size];
        }
    };
}
