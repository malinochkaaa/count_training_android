package com.alinakimova.count_training.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.alinakimova.count_training.db.Question;

import java.util.List;

@Dao
public interface QuestionDAO {
    @Query("SELECT * FROM question WHERE lvl = :level")
    List<Question> getAllByLevel(int level);

    @Insert
    void insert(Question question);
}
