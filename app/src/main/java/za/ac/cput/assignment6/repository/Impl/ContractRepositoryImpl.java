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
import za.ac.cput.assignment6.domain.Contract;
import za.ac.cput.assignment6.domain.ContractDetails;
import za.ac.cput.assignment6.repository.ContractRepository;

/**
 * Created by rcarstens on 2016/05/13.
 */
public class ContractRepositoryImpl extends SQLiteOpenHelper implements ContractRepository {
    public static final String TABLE_NAME = "Contract";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_IDCHECKNUM = "IdCheckNum";
    public static final String COLUMN_DETAILSCHECKNUM = "DetailsCheckNum";
    public static final String COLUMN_CONTRACTTYPE = "contractType";
    public static final String COLUMN_CONTRACTNUM= "contractNum";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IDCHECKNUM + " INTEGER NOT NULL , "
            + COLUMN_DETAILSCHECKNUM + " INTEGER NOT NULL , "
            + COLUMN_CONTRACTTYPE + " TEXT NOT NULL , "
            + COLUMN_CONTRACTNUM + " INTEGER NOT NULL );";


    public ContractRepositoryImpl(Context context) {
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    @Override
    public Contract findById(Long id) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_IDCHECKNUM,
                        COLUMN_DETAILSCHECKNUM,
                        COLUMN_CONTRACTTYPE,
                        COLUMN_CONTRACTNUM},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            final ContractDetails contractDetails = new ContractDetails.Builder()
                    .contractType(cursor.getString(cursor.getColumnIndex(COLUMN_CONTRACTTYPE)))
                    .contractNum(cursor.getInt(cursor.getColumnIndex(COLUMN_CONTRACTNUM)))
                    .build();

            final Contract contract = new Contract.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .IdCheckNum(cursor.getInt(cursor.getColumnIndex(COLUMN_IDCHECKNUM)))
                    .DetailsCheckNum(cursor.getInt(cursor.getColumnIndex(COLUMN_DETAILSCHECKNUM)))
                    .contractDetails(contractDetails)
                    .build();

            return contract;
        } else {
            return null;
        }
    }

    @Override
    public Contract save(Contract entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_IDCHECKNUM, entity.getIdCheckNum());
        values.put(COLUMN_DETAILSCHECKNUM, entity.getDetailsCheckNum());
        values.put(COLUMN_CONTRACTTYPE, entity.getContractDetails().getContractType());
        values.put(COLUMN_CONTRACTNUM, entity.getContractDetails().getContractNum());

        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Contract insertedEntity = new Contract.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Contract update(Contract entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_IDCHECKNUM, entity.getIdCheckNum());
        values.put(COLUMN_DETAILSCHECKNUM, entity.getDetailsCheckNum());
        values.put(COLUMN_CONTRACTTYPE, entity.getContractDetails().getContractType());
        values.put(COLUMN_CONTRACTNUM, entity.getContractDetails().getContractNum());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Contract delete(Contract entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Contract> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Contract> Contracts = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final ContractDetails contractDetails = new ContractDetails.Builder()
                        .contractType(cursor.getString(cursor.getColumnIndex(COLUMN_CONTRACTTYPE)))
                        .contractNum(cursor.getInt(cursor.getColumnIndex(COLUMN_CONTRACTNUM)))
                        .build();

                final Contract contract = new Contract.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .IdCheckNum(cursor.getInt(cursor.getColumnIndex(COLUMN_IDCHECKNUM)))
                        .DetailsCheckNum(cursor.getInt(cursor.getColumnIndex(COLUMN_DETAILSCHECKNUM)))
                        .contractDetails(contractDetails)
                        .build();

                Contracts.add(contract);
            } while (cursor.moveToNext());
        }
        return Contracts;
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
            //DBConstants.setDatabaseVersion(1);
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
