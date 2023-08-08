package com.alinakimova.count_training.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String txt, ans1, ans2, ans3, ans4, right;
    public int lvl;

    public Question() {}

    protected Question(Parcel in) {
        id = in.readInt();
        txt = in.readString();
        ans1 = in.readString();
        ans2 = in.readString();
        ans3 = in.readString();
        ans4 = in.readString();
        right = in.readString();
        lvl = in.readInt();
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(txt);
        parcel.writeString(ans1);
        parcel.writeString(ans2);
        parcel.writeString(ans3);
        parcel.writeString(ans4);
        parcel.writeString(right);
        parcel.writeInt(lvl);
    }
}
