package ge.sweeft.rickandmortyalbum.db.migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class Migration2to3: Migration(2,3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("DROP TABLE character")
    }
}