package com.alinakimova.count_training.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Question.class}, version = 2)
public abstract class myDatabase extends RoomDatabase {
    public abstract QuestionDAO question_dao();
}
