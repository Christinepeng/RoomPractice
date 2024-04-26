package com.example.roompractice.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Word::class], version = 2, exportSchema = false)
abstract class WordDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao

    // singleton
    companion object {
        @Volatile
        private var INSTANCE: WordDatabase? = null

        fun getDatabase(context: Context): WordDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "word_database"
                )
                    .addMigrations(MIGRATION_1_2)
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // 定義遷移
        private val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE word ADD COLUMN chinese_invisible INTEGER NOT NULL DEFAULT 0")
//                database.execSQL("UPDATE word SET definition = meaning")
//                database.execSQL("ALTER TABLE word RENAME TO word_temp")
//                database.execSQL("CREATE TABLE word (" +
//                        "id INTEGER PRIMARY KEY NOT NULL, " +
//                        "word TEXT, " +
//                        "definition TEXT)")
//                database.execSQL("INSERT INTO word (id, word, definition) " +
//                        "SELECT id, word, definition FROM word_temp")
//                database.execSQL("DROP TABLE word_temp")
            }
        }
    }
}