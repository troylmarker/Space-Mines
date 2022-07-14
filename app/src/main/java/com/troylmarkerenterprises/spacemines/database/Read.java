/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.database.Read                                                                             *
 *  File Name: Read.java                                                                                                                        *
 *  File Creation Date: 7/6/2022                                                                                                                *
 *  File Creation Time: 21:17:13                                                                                                                *
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

import static com.troylmarkerenterprises.spacemines.constants.Db.COLUMN_NAME_ID;
import static com.troylmarkerenterprises.spacemines.constants.Db.COLUMN_NAME_PLANET_ID;
import static com.troylmarkerenterprises.spacemines.constants.Db.DB_NAME;
import static com.troylmarkerenterprises.spacemines.constants.Db.DB_VERSION;
import static com.troylmarkerenterprises.spacemines.constants.Db.TABLE_NAME_ITWORKERS;
import static com.troylmarkerenterprises.spacemines.constants.Db.TABLE_NAME_PLANETS;
import static com.troylmarkerenterprises.spacemines.constants.Db.TABLE_NAME_PRICING;
import static com.troylmarkerenterprises.spacemines.constants.Db.TABLE_NAME_WORKERS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.troylmarkerenterprises.spacemines.model.ITWorkersModel;
import com.troylmarkerenterprises.spacemines.model.PlanetModel;
import com.troylmarkerenterprises.spacemines.model.PricingModel;
import com.troylmarkerenterprises.spacemines.model.WorkerModel;

import java.util.ArrayList;

public class Read extends SQLiteOpenHelper {

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    public Read(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public WorkerModel readPlanetWorker(int pId) {
        WorkerModel returnModel = new WorkerModel();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s = %s", TABLE_NAME_WORKERS, COLUMN_NAME_ID, pId);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            returnModel.setId(cursor.getInt(0));
            returnModel.setMinerw(cursor.getInt(1));
            returnModel.setMiners(cursor.getInt(2));
            returnModel.setMaintw(cursor.getInt(3));
            returnModel.setMaints(cursor.getInt(4));
            returnModel.setEnterw(cursor.getInt(5));
            returnModel.setEnters(cursor.getInt(6));
        }
        db.close();
        return returnModel;
    }

    public ITWorkersModel readITWorkers(int pId) {
        ITWorkersModel returnModel = new ITWorkersModel();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s = %s", TABLE_NAME_ITWORKERS, COLUMN_NAME_PLANET_ID, pId);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        returnModel.clear();
        while (cursor.moveToNext()) {
            returnModel.setMinerw(returnModel.getMinerw() + cursor.getInt(2));
            returnModel.setMiners(returnModel.getMiners() + cursor.getInt(3));
            returnModel.setMaintw(returnModel.getMaintw() + cursor.getInt(4));
            returnModel.setMaints(returnModel.getMaints() + cursor.getInt(5));
            returnModel.setEnterw(returnModel.getEnterw() + cursor.getInt(6));
            returnModel.setEnters(returnModel.getEnters() + cursor.getInt(7));
        }
        db.close();
        return returnModel;
    }

    public ArrayList<PlanetModel> readGalaxy() {
        ArrayList<PlanetModel> planets = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ORDER BY %s", TABLE_NAME_PLANETS, COLUMN_NAME_ID);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int size = cursor.getInt(2);
            int population = cursor.getInt(3);
            double t_cu = cursor.getDouble(4);
            double t_ag = cursor.getDouble(5);
            double t_au = cursor.getDouble(6);
            double t_pt = cursor.getDouble(7);
            double t_pd = cursor.getDouble(8);
            double m_cu = cursor.getInt(9);
            double m_ag = cursor.getInt(10);
            double m_au = cursor.getInt(11);
            double m_pt = cursor.getInt(12);
            double m_pd = cursor.getInt(13);
            int icon = cursor.getInt(14);
            planets.add(new PlanetModel(id, name, size, population, t_cu, t_ag,
                    t_au, t_pt, t_pd, m_cu, m_ag, m_au, m_pt, m_pd, icon));
        }
        db.close();
        return planets;
    }

    public ArrayList<PricingModel> readPricing() {
        ArrayList<PricingModel> pricing = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ORDER BY %s", TABLE_NAME_PRICING, COLUMN_NAME_ID);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            double p_cu = cursor.getDouble(1);
            double p_ag = cursor.getDouble(2);
            double p_au = cursor.getDouble(3);
            double p_pt = cursor.getDouble(4);
            double p_pd = cursor.getDouble(5);
            pricing.add(new PricingModel(id, p_cu, p_ag, p_au, p_pt, p_pd));
        }
        db.close();
        return pricing;
    }

    public ArrayList<WorkerModel> readWorkers() {
        ArrayList<WorkerModel> workers = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ORDER BY %s", TABLE_NAME_WORKERS, COLUMN_NAME_ID);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int minw = cursor.getInt(1);
            int mins = cursor.getInt(2);
            int maiw = cursor.getInt(3);
            int mais = cursor.getInt(4);
            int entw = cursor.getInt(5);
            int ents = cursor.getInt(6);
            workers.add(new WorkerModel(id, minw, mins, maiw, mais, entw, ents));
        }
        db.close();
        return workers;
    }
}