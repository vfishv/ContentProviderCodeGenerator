package com.foxykeep.cpcodegenerator.sample.data.provider;

import com.foxykeep.cpcodegenerator.sample.data.provider.util.ColumnMetadata;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.net.Uri;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * This class was generated by the ContentProviderCodeGenerator project made by Foxykeep
 * <p>
 * (More information available https://github.com/foxykeep/ContentProviderCodeGenerator)
 */
public abstract class SampleContent {

    public static final Uri CONTENT_URI = Uri.parse("content://" + SampleProvider.AUTHORITY);

    private SampleContent() {
    }

    /**
     * Created in version 1
     */
    public static final class Person extends SampleContent {

        private static final String LOG_TAG = Person.class.getSimpleName();

        public static final String TABLE_NAME = "person";
        public static final String TYPE_ELEM_TYPE = "vnd.android.cursor.item/sample-person";
        public static final String TYPE_DIR_TYPE = "vnd.android.cursor.dir/sample-person";

        public static final Uri CONTENT_URI = Uri.parse(SampleContent.CONTENT_URI + "/" + TABLE_NAME);

        public static enum Columns implements ColumnMetadata {
            ID(BaseColumns._ID, "integer"),
            FIRST_NAME("firstName", "text"),
            LAST_NAME("lastName", "text"),
            BIRTHDAY("birthday", "integer"),
            GENDER("gender", "integer");

            private final String mName;
            private final String mType;

            private Columns(String name, String type) {
                mName = name;
                mType = type;
            }

            @Override
            public int getIndex() {
                return ordinal();
            }

            @Override
            public String getName() {
                return mName;
            }

            @Override
            public String getType() {
                return mType;
            }
        }

        public static final String[] PROJECTION = new String[] {
                Columns.ID.getName(),
                Columns.FIRST_NAME.getName(),
                Columns.LAST_NAME.getName(),
                Columns.BIRTHDAY.getName(),
                Columns.GENDER.getName()
        };

        private Person() {
            // No private constructor
        }

        public static void createTable(SQLiteDatabase db) {
            if (SampleProvider.ACTIVATE_ALL_LOGS) {
                Log.d(LOG_TAG, "Person | createTable start");
            }
            db.execSQL("CREATE TABLE " + TABLE_NAME + " (" + Columns.ID.getName() + " " + Columns.ID.getType() + ", " + Columns.FIRST_NAME.getName() + " " + Columns.FIRST_NAME.getType() + ", " + Columns.LAST_NAME.getName() + " " + Columns.LAST_NAME.getType() + ", " + Columns.BIRTHDAY.getName() + " " + Columns.BIRTHDAY.getType() + ", " + Columns.GENDER.getName() + " " + Columns.GENDER.getType() + ", PRIMARY KEY (" + Columns.ID.getName() + ")" + ");");

            db.execSQL("CREATE INDEX person_firstName on " + TABLE_NAME + "(" + Columns.FIRST_NAME.getName() + ");");
            db.execSQL("CREATE INDEX person_lastName on " + TABLE_NAME + "(" + Columns.LAST_NAME.getName() + ");");
            if (SampleProvider.ACTIVATE_ALL_LOGS) {
                Log.d(LOG_TAG, "Person | createTable end");
            }
        }

        // Version 1 : Creation of the table
        public static void upgradeTable(SQLiteDatabase db, int oldVersion, int newVersion) {
            if (SampleProvider.ACTIVATE_ALL_LOGS) {
                Log.d(LOG_TAG, "Person | upgradeTable start");
            }

            if (oldVersion < 1) {
                Log.i(LOG_TAG, "Upgrading from version " + oldVersion + " to " + newVersion
                        + ", data will be lost!");

                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
                createTable(db);
                return;
            }


            if (oldVersion != newVersion) {
                throw new IllegalStateException("Error upgrading the database to version "
                        + newVersion);
            }

            if (SampleProvider.ACTIVATE_ALL_LOGS) {
                Log.d(LOG_TAG, "Person | upgradeTable end");
            }
        }

        static String getBulkInsertString() {
            return new StringBuilder("INSERT INTO ").append(TABLE_NAME).append(" ( ").append(Columns.ID.getName()).append(", ").append(Columns.FIRST_NAME.getName()).append(", ").append(Columns.LAST_NAME.getName()).append(", ").append(Columns.BIRTHDAY.getName()).append(", ").append(Columns.GENDER.getName()).append(" ) VALUES (?, ?, ?, ?, ?)").toString();
        }

        static void bindValuesInBulkInsert(SQLiteStatement stmt, ContentValues values) {
            int i = 1;
            String value;
            stmt.bindLong(i++, values.getAsLong(Columns.ID.getName()));
            value = values.getAsString(Columns.FIRST_NAME.getName());
            stmt.bindString(i++, value != null ? value : "");
            value = values.getAsString(Columns.LAST_NAME.getName());
            stmt.bindString(i++, value != null ? value : "");
            stmt.bindLong(i++, values.getAsLong(Columns.BIRTHDAY.getName()));
            stmt.bindLong(i++, values.getAsLong(Columns.GENDER.getName()));
        }
    }
}
