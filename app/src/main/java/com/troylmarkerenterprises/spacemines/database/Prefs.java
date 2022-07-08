/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.database.Prefs                                                                            *
 *  File Name: Prefs.java                                                                                                                       *
 *  File Creation Date: 7/6/2022                                                                                                                *
 *  File Creation Time: 22:0:58                                                                                                                 *
 *  File Creator: troylmarker                                                                                                                   *
 *                                                                                                                                              *
 *  Copyright 2022  by Troy L Marker Enterprises                                                                                                *
 *                                                                                                                                              *
 *  Licensed under the Apache License, Version 2.0 (the "License"); You may not use this file except in compliance with the License.            *
 *  You may obtain a copy of the License at                                                                                                     *
 *                                                                                                                                              *
 *       http://www.apache.org/licenses/LICENSE-2.0                                                                                             *
 *                                                                                                                                              *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,       *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                    *
 *  See the License for the specific language governing permissions and limitations under the License.                                          *
 ************************************************************************************************************************************************/
package com.troylmarkerenterprises.spacemines.database;

import static com.troylmarkerenterprises.spacemines.constants.Db.DB_NAME;
import static com.troylmarkerenterprises.spacemines.constants.Db.DB_VERSION;
import static com.troylmarkerenterprises.spacemines.constants.Pref.COLUMN_NAME_PREFERENCE;
import static com.troylmarkerenterprises.spacemines.constants.Pref.COLUMN_NAME_VALUE;
import static com.troylmarkerenterprises.spacemines.constants.Pref.TABLE_NAME_PREFERENCES;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Prefs  extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public Prefs(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public String getPref(String preference) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'", COLUMN_NAME_VALUE, TABLE_NAME_PREFERENCES, COLUMN_NAME_PREFERENCE,
                preference);
        @SuppressLint("Recycle") Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        @SuppressLint("Range") String retValue = result.getString(result.getColumnIndex("value"));
        db.close();
        return retValue;
    }

    public boolean checkPref(String preference) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'", COLUMN_NAME_VALUE, TABLE_NAME_PREFERENCES, COLUMN_NAME_PREFERENCE,
                preference);
        @SuppressLint("Recycle") Cursor result = db.rawQuery(query, null);
        return result.getCount() == 1;
    }

    @SuppressLint("Range")
    public String checkPrefOrCreate(String preference, String baseValue) {
        SQLiteDatabase db = getReadableDatabase();
        String returnValue;
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'", COLUMN_NAME_VALUE, TABLE_NAME_PREFERENCES, COLUMN_NAME_PREFERENCE,
                preference);
        @SuppressLint("Recycle") Cursor result = db.rawQuery(query, null);
        if (result.getCount() == 1) {
            result.moveToFirst();
            returnValue = result.getString(result.getColumnIndex("value"));
            db.close();
        } else {
            setPref(preference, baseValue);
            returnValue = baseValue;
        }
        return returnValue;
    }

    public void setPref(String pref, String val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_PREFERENCE, pref);
        values.put(COLUMN_NAME_VALUE, val);
        db.insert(TABLE_NAME_PREFERENCES, null, values);
        db.close();
    }
}
