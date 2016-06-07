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
import za.ac.cput.assignment6.domain.ContactDetails;
import za.ac.cput.assignment6.domain.FullNameDetails;
import za.ac.cput.assignment6.domain.Recruit;
import za.ac.cput.assignment6.repository.RecruitRepository;

/**
 * Created by sanXion on 2016/04/24.
 */
public class RecruitRepositoryImpl extends SQLiteOpenHelper implements RecruitRepository {
    public static final String TABLE_NAME = "recruit";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SOUTHAFRICANID = "southAfricanID";
    public static final String COLUMN_FIRSTNAME = "firstName";
    public static final String COLUMN_LASTNAME = "lastName";
    public static final String COLUMN_HOMENUMBER = "email";
    public static final String COLUMN_EMAIL = "homePhone";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SOUTHAFRICANID + " TEXT UNIQUE NOT NULL , "
            + COLUMN_FIRSTNAME + " TEXT NOT NULL , "
            + COLUMN_LASTNAME + " TEXT NOT NULL , "
            + COLUMN_HOMENUMBER + " TEXT NOT NULL , "
            + COLUMN_EMAIL + " TEXT NOT NULL );";


    public RecruitRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Recruit findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_SOUTHAFRICANID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
                        COLUMN_HOMENUMBER,
                        COLUMN_EMAIL},
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

            final ContactDetails contactDetails = new ContactDetails.Builder()
                    .emailAddress(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .homeNumber(cursor.getString(cursor.getColumnIndex(COLUMN_HOMENUMBER)))
                    .build();

            final Recruit recruit = new Recruit.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .contactDetails(contactDetails)
                    .fullNameDetails(fullnameDetails)
                    .southAfricanID(cursor.getString(cursor.getColumnIndex(COLUMN_SOUTHAFRICANID)))
                    .build();

            return recruit;
        } else {
            return null;
        }
    }
        @Override
        public Recruit save(Recruit entity) {
            open();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_SOUTHAFRICANID, entity.getSouthAfricanID());
            values.put(COLUMN_FIRSTNAME, entity.getFullNameDetails().getFirstName());
            values.put(COLUMN_LASTNAME, entity.getFullNameDetails().getLastName());
            values.put(COLUMN_HOMENUMBER, entity.getContactDetails().getHomeNumber());
            values.put(COLUMN_EMAIL, entity.getContactDetails().getEmailAddress());
            long id = db.insertOrThrow(TABLE_NAME, null, values);
            Recruit insertedEntity = new Recruit.Builder()
                    .copy(entity)
                    .id(new Long(id))
                    .build();
            return insertedEntity;
        }

        @Override
        public Recruit update(Recruit entity) {
            open();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, entity.getId());
            values.put(COLUMN_SOUTHAFRICANID, entity.getSouthAfricanID());
            values.put(COLUMN_FIRSTNAME, entity.getFullNameDetails().getFirstName());
            values.put(COLUMN_LASTNAME, entity.getFullNameDetails().getLastName());
            values.put(COLUMN_HOMENUMBER, entity.getContactDetails().getHomeNumber());
            values.put(COLUMN_EMAIL, entity.getContactDetails().getEmailAddress());
            db.update(
                    TABLE_NAME,
                    values,
                    COLUMN_ID + " =? ",
                    new String[]{String.valueOf(entity.getId())}
            );
            return entity;
        }

        @Override
        public Recruit delete(Recruit entity) {
            open();
            db.delete(
                    TABLE_NAME,
                    COLUMN_ID + " =? ",
                    new String[]{String.valueOf(entity.getId())});
            return entity;
        }

        @Override
        public Set<Recruit> findAll() {
            SQLiteDatabase db = this.getReadableDatabase();
            Set<Recruit> recruits = new HashSet<>();
            open();
            Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
            if (cursor.moveToFirst()) {
                do {
                    final FullNameDetails fullnameDetails = new FullNameDetails.Builder()
                            .firstName(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                            .lastName(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                            .build();

                    final ContactDetails contactDetails = new ContactDetails.Builder()
                            .emailAddress(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                            .homeNumber(cursor.getString(cursor.getColumnIndex(COLUMN_HOMENUMBER)))
                            .build();

                    final Recruit recruit = new Recruit.Builder()
                            .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                            .contactDetails(contactDetails)
                            .fullNameDetails(fullnameDetails)
                            .southAfricanID(cursor.getString(cursor.getColumnIndex(COLUMN_SOUTHAFRICANID)))
                            .build();

                    recruits.add(recruit);
                } while (cursor.moveToNext());
            }
            return recruits;
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
