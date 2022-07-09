/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.database.Update                                                                           *
 *  File Name: Update.java                                                                                                                      *
 *  File Creation Date: 7/6/2022                                                                                                                *
 *  File Creation Time: 21:37:20                                                                                                                *
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
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Update extends SQLiteOpenHelper {

    SQLiteDatabase db;
    final Context context;
    final Utilities dbUtil;

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public Update(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context.getApplicationContext();
        this.dbUtil = new Utilities(this.context);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void updateTransitWorkers(int intPid, int intMinerW, int intMinerS, int intMaintW, int intMaintS, int intEnterW, int intEnterS) {
        db = getWritableDatabase();
        int recordNumber = dbUtil.countRecords(TABLE_NAME_ITWORKERS) + 1;
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_ID, recordNumber);
        values.put(COLUMN_NAME_PLANET_ID, intPid);
        values.put(COLUMN_NAME_MINERW, intMinerW);
        values.put(COLUMN_NAME_MINERS, intMinerS);
        values.put(COLUMN_NAME_MAINTW, intMaintW);
        values.put(COLUMN_NAME_MAINTS, intMaintS);
        values.put(COLUMN_NAME_ENTERW, intEnterW);
        values.put(COLUMN_NAME_ENTERS, intEnterS);
        db.insert(TABLE_NAME_ITWORKERS, null, values);
        db.close();
    }

    @SuppressLint("Recycle")
    public void recallWorkers(int pId) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = String.format("DELETE FROM %s WHERE %s = %s", TABLE_NAME_ITWORKERS, COLUMN_NAME_PLANET_ID, pId);
        db.execSQL(sql);
        db.close();
    }
}