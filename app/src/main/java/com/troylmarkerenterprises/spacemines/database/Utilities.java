/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.database.Utilities                                                                        *
 *  File Name: Utilities.java                                                                                                                   *
 *  File Creation Date: 7/6/2022                                                                                                                *
 *  File Creation Time: 19:32:40                                                                                                                *
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

import static com.troylmarkerenterprises.spacemines.constants.Db.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.troylmarkerenterprises.spacemines.model.TimeModel;

import java.util.concurrent.atomic.AtomicInteger;

public class Utilities extends SQLiteOpenHelper {

    SQLiteDatabase db;

    public boolean doesTableExist(String tableName) {
        if (db == null || !db.isOpen()) {
            db = getReadableDatabase();
        }
        if (!db.isReadOnly()) {
            db.close();
            db = getReadableDatabase();
        }
        String query = String.format("SELECT DISTINCT * FROM %s", tableName);
        try {
            Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                if (cursor.getCount() > 0) {
                    cursor.close();
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Utilities(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public int countRecords(String tableName) {
        AtomicInteger returnValue = new AtomicInteger();
        if (db == null || !db.isOpen()) {
            db = getReadableDatabase();
        }
        if (!db.isReadOnly()) {
            db.close();
            db = getReadableDatabase();
        }
        String query = String.format("SELECT DISTINCT * FROM %s", tableName);
        try {
            @SuppressLint("Recycle") Cursor cursor = db.rawQuery(query, null);
            if (cursor != null) {
                returnValue.set(cursor.getCount());
            } else {
                returnValue.set(0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            returnValue.set(0);
        }
        return returnValue.get();
    }

    public String getTransitTime(int p1Id, int p2Id) {
        double distance = 0d;
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s = %s", TABLE_NAME_DISTANCE, COLUMN_NAME_ID, p1Id);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql,null);
        cursor.moveToFirst();
        distance = cursor.getDouble(p2Id + 1);
        int time1 = (int)(distance * 5.879e12) / 186000;
        String time = new TimeModel(time1).toString();
        return time;
    }
}