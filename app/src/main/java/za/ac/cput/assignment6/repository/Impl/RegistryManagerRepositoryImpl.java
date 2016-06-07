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
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.LoginDetails;
import za.ac.cput.assignment6.domain.RegistryManager;
import za.ac.cput.assignment6.repository.RegistryManagerRepository;

/**
 * Created by sanXion on 2016/04/24.
 */
public class RegistryManagerRepositoryImpl extends SQLiteOpenHelper implements RegistryManagerRepository {
    public static final String TABLE_NAME = "RegistryManager";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SOUTHAFRICANID = "southAfricanID";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SOUTHAFRICANID + " TEXT NOT NULL , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_EMAIL + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL );";

    public RegistryManagerRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public RegistryManager findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SOUTHAFRICANID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_EMAIL,
                        COLUMN_PASSWORD},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final FullNameDetails fullnameDetails = new FullNameDetails.Builder()
                    .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .build();

            final LoginDetails loginDetails = new LoginDetails.Builder()
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .build();

            final RegistryManager registryManager = new RegistryManager.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .southAfricanID(cursor.getString(cursor.getColumnIndex(COLUMN_SOUTHAFRICANID)))
                    .fullNameDetails(fullnameDetails)
                    .loginDetails(loginDetails)
                    .build();

            return registryManager;
        } else {
            return null;
        }
    }
    @Override
    public RegistryManager save(RegistryManager entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SOUTHAFRICANID, entity.getSouthAfricanID());
        values.put(COLUMN_FIRSTNAME, entity.getFullNameDetails().getFirstName());
        values.put(COLUMN_LASTNAME, entity.getFullNameDetails().getLastName());
        values.put(COLUMN_EMAIL, entity.getLoginDetails().getEmail());
        values.put(COLUMN_PASSWORD, entity.getLoginDetails().getPassword());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        RegistryManager insertedEntity = new RegistryManager.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public RegistryManager update(RegistryManager entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_SOUTHAFRICANID, entity.getSouthAfricanID());
        values.put(COLUMN_FIRSTNAME, entity.getFullNameDetails().getFirstName());
        values.put(COLUMN_LASTNAME, entity.getFullNameDetails().getLastName());
        values.put(COLUMN_EMAIL, entity.getLoginDetails().getEmail());
        values.put(COLUMN_PASSWORD, entity.getLoginDetails().getPassword());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public RegistryManager delete(RegistryManager entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<RegistryManager> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<RegistryManager> registryManagers = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final FullNameDetails fullnameDetails = new FullNameDetails.Builder()
                        .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .build();

                final LoginDetails loginDetails = new LoginDetails.Builder()
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .build();

                final RegistryManager registryManager = new RegistryManager.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .fullNameDetails(fullnameDetails)
                        .loginDetails(loginDetails)
                        .southAfricanID(cursor.getString(cursor.getColumnIndex(COLUMN_SOUTHAFRICANID)))
                        .build();
                registryManagers.add(registryManager);
            } while (cursor.moveToNext());
        }
        return registryManagers;
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
