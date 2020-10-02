package com.jjoonleo.mycalendar;

import android.os.Parcelable;
import android.widget.TextView;

public class Parcel implements Parcelable {
    TextView textView;

    public Parcel(TextView textView){
        this.textView = textView;
    }

    protected Parcel(android.os.Parcel in) {
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Parcel> CREATOR = new Creator<Parcel>() {
        @Override
        public Parcel createFromParcel(android.os.Parcel in) {
            return new Parcel(in);
        }

        @Override
        public Parcel[] newArray(int size) {
            return new Parcel[size];
        }
    };

    public TextView getTextView(){
        return textView;
    }
}
