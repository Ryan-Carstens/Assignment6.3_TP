package za.ac.cput.assignment6.repository.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

import za.ac.cput.assignment6.conf.databases.DBConstants;
import za.ac.cput.assignment6.domain.DetailsCheck;
import za.ac.cput.assignment6.domain.Recruit;
import za.ac.cput.assignment6.repository.DetailsCheckRepository;


/**
 * Created by sanXion on 2016/04/24.
 */
public class DetailsCheckRepositoryImpl extends SQLiteOpenHelper implements DetailsCheckRepository {
    public static final String TABLE_NAME = "detailscheck";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_RESPONSE = "response";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_RESPONSE + " TEXT NOT NULL );";

    public DetailsCheckRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public DetailsCheck findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_RESPONSE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final DetailsCheck detailsCheck = new DetailsCheck.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .response(cursor.getString(cursor.getColumnIndex(COLUMN_RESPONSE)))
                    .build();

            return detailsCheck;
        } else {
            return null;
        }
    }
    @Override
    public DetailsCheck save(DetailsCheck entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_RESPONSE, entity.getResponse());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        DetailsCheck insertedEntity = new DetailsCheck.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public DetailsCheck update(DetailsCheck entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_RESPONSE, entity.getResponse());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public DetailsCheck delete(DetailsCheck entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<DetailsCheck> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<DetailsCheck> detailsChecks = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final DetailsCheck detailsCheck = new DetailsCheck.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .response(cursor.getString(cursor.getColumnIndex(COLUMN_RESPONSE)))
                        .build();
                detailsChecks.add(detailsCheck);
            } while (cursor.moveToNext());
        }
        return detailsChecks;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(DATABASE_CREATE);
            //DBConstants.DATABASE_VERSION += 1;
        }
        catch(Exception e)
        {
            return;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
