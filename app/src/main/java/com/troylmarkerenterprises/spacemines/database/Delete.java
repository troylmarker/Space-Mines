/***********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                  *
 *  Class Name: com.troylmarkerenterprises.spacemines.database.Delete                                                                          *
 *  File Name: Delete.java                                                                                                                     *
 *  File Creation Date: 7/19/2022                                                                                                              *
 *  File Creation Time: 4:24:15                                                                                                                *
 *  File Creator: troylmarker                                                                                                                  *
 *                                                                                                                                             *
 *  Copyright 2022  by Troy L Marker Enterprises                                                                                               *
 *                                                                                                                                             *
 *  Licensed under the Apache License, Version 2.0 (the "License"); You may not use this file except in compliance with the License.           *
 *  You may obtain a copy of the License at                                                                                                    *
 *                                                                                                                                             *
 *       http://www.apache.org/licenses/LICENSE-2.0                                                                                            *
 *                                                                                                                                             *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,      *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                   *
 *  See the License for the specific language governing permissions and limitations under the License.                                         *
 ***********************************************************************************************************************************************/
package com.troylmarkerenterprises.spacemines.database;

import static com.troylmarkerenterprises.spacemines.constants.Db.COLUMN_NAME_PLANET_ID;
import static com.troylmarkerenterprises.spacemines.constants.Db.DB_NAME;
import static com.troylmarkerenterprises.spacemines.constants.Db.DB_VERSION;
import static com.troylmarkerenterprises.spacemines.constants.Db.TABLE_NAME_ITWORKERS;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Delete  extends SQLiteOpenHelper {

    SQLiteDatabase db;
    final Context context;

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public Delete(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void clearInTransit(int pId) {
        db = getWritableDatabase();
        db.delete(TABLE_NAME_ITWORKERS, COLUMN_NAME_PLANET_ID + "=" + pId,null);
    }
}
