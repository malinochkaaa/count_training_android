package com.alinakimova.count_training;

import android.app.Application;

import androidx.room.Room;

import com.alinakimova.count_training.db.myDatabase;

public class App extends Application {

    public static App instance;
    private myDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        /*InputStream input = null;
        try {
            input = getAssets().open("data.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        File file = new File("data.json");
        try (OutputStream output = new FileOutputStream(file)) {
            IOUtils.copyStream(input, output);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        Log.e("LOGTAG", String.valueOf(file.exists()));*/
        database = Room
                .databaseBuilder(getApplicationContext(), myDatabase.class, "question")
                .createFromAsset("databases/question.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    public static App getInstance() {
        return instance;
    }

    public myDatabase getDatabase() {
        return database;
    }
}
