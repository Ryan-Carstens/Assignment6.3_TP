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
import za.ac.cput.assignment6.domain.RegistrationEntry;
import za.ac.cput.assignment6.repository.RegistrationEntryRepository;

/**
 * Created by sanXion on 2016/04/24.
 */
public class RegistrationEntryRepositoryImpl extends SQLiteOpenHelper implements RegistrationEntryRepository {
    public static final String TABLE_NAME = "registrationEntry";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SOUTHAFRICANID = "southAfricanID";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_PLACEMENTCHOICE = "placementChoice";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SOUTHAFRICANID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_GENDER + " TEXT NOT NULL , "
            + COLUMN_PLACEMENTCHOICE + " TEXT NOT NULL );";


    public RegistrationEntryRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public RegistrationEntry findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SOUTHAFRICANID,
                        COLUMN_GENDER,
                        COLUMN_PLACEMENTCHOICE},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final RegistrationEntry registrationEntry = new RegistrationEntry.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                    .placementChoice(cursor.getString(cursor.getColumnIndex(COLUMN_PLACEMENTCHOICE)))
                    .southAfricanID(cursor.getString(cursor.getColumnIndex(COLUMN_SOUTHAFRICANID)))
                    .build();

            return registrationEntry;
        } else {
            return null;
        }
    }
    @Override
    public RegistrationEntry save(RegistrationEntry entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SOUTHAFRICANID, entity.getSouthAfricanID());
        values.put(COLUMN_GENDER, entity.getGender());
        values.put(COLUMN_PLACEMENTCHOICE, entity.getPlacementChoice());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        RegistrationEntry insertedEntity = new RegistrationEntry.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public RegistrationEntry update(RegistrationEntry entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SOUTHAFRICANID, entity.getSouthAfricanID());
        values.put(COLUMN_GENDER, entity.getGender());
        values.put(COLUMN_PLACEMENTCHOICE, entity.getPlacementChoice());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public RegistrationEntry delete(RegistrationEntry entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<RegistrationEntry> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<RegistrationEntry> registrationEntrys = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final RegistrationEntry registrationEntry = new RegistrationEntry.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .gender(cursor.getString(cursor.getColumnIndex(COLUMN_GENDER)))
                        .placementChoice(cursor.getString(cursor.getColumnIndex(COLUMN_PLACEMENTCHOICE)))
                        .southAfricanID(cursor.getString(cursor.getColumnIndex(COLUMN_SOUTHAFRICANID)))
                        .build();
                registrationEntrys.add(registrationEntry);
            } while (cursor.moveToNext());
        }
        return registrationEntrys;
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
