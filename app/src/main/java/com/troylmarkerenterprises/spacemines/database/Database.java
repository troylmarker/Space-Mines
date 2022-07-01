/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.database.Database                                                                         *
 *  File Name: Database.java                                                                                                                    *
 *  File Creation Date: 6/30/2022                                                                                                               *
 *  File Creation Time: 20:1:28                                                                                                                 *
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
import static com.troylmarkerenterprises.spacemines.constants.Pref.*;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.troylmarkerenterprises.spacemines.helpers.General;
import com.troylmarkerenterprises.spacemines.helpers.Planet;
import com.troylmarkerenterprises.spacemines.model.PlanetModel;
import com.troylmarkerenterprises.spacemines.model.PointModel;
import com.troylmarkerenterprises.spacemines.model.PricingModel;
import com.troylmarkerenterprises.spacemines.model.WorkerModel;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class Database extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Planet ph = new Planet();
    General gh = new General();
    Context context;

    // SQL To create the Planet Table

    public final String PLANET_TABLE_SQL = String.format(
        "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT NOT NULL, %s INTEGER, %s INTEGER, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL," +
            " %s REAL, %s REAL, %s REAL, %s REAL, %S REAL, %s INTEGER)",
        TABLE_NAME_PLANETS, COLUMN_NAME_ID, COLUMN_NAME_NAME, COLUMN_NAME_SIZE, COLUMN_NAME_POPULATION, COLUMN_NAME_TOTAL_COPPER,
            COLUMN_NAME_TOTAL_SILVER, COLUMN_NAME_TOTAL_GOLD, COLUMN_NAME_TOTAL_PLATINUM, COLUMN_NAME_TOTAL_PALLADIUM,
            COLUMN_NAME_MINED_COPPER, COLUMN_NAME_MINED_SILVER, COLUMN_NAME_MINED_GOLD, COLUMN_NAME_MINED_PLATINUM,
            COLUMN_NAME_MINED_PALLADIUM, COLUMN_NAME_ICON);

    //SQL To create the points table for planet location

    public final String TABLE_NAME_POINTS_SQL = String.format(
        "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s REAL, %s REAL, %s REAL)", TABLE_NAME_POINTS, COLUMN_NAME_ID, COLUMN_NAME_XCORD,
            COLUMN_NAME_YCORD, COLUMN_NAME_ZCORD);

    // SQL To create the Pricing Table

    public final String TABLE_NAME_PRICING_SQL = String.format(
        "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL)",
        TABLE_NAME_PRICING, COLUMN_NAME_ID, COLUMN_NAME_PRICE_COPPER, COLUMN_NAME_PRICE_SILVER, COLUMN_NAME_PRICE_GOLD,
            COLUMN_NAME_PRICE_PLATINUM, COLUMN_NAME_PRICE_PALLADIUM);

    //SQL To create the Worker Table

    public final String TABLE_NAME_WORKERS_SQL = String.format(
        "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)",
        TABLE_NAME_WORKERS, COLUMN_NAME_ID, COLUMN_NAME_MINERW, COLUMN_NAME_MINERS, COLUMN_NAME_MAINTW, COLUMN_NAME_MAINTS, COLUMN_NAME_ENTERW,
            COLUMN_NAME_ENTERS);

    //SQL To create the In Transit Worker Table

    public final String TABLE_NAME_ITWORKERS_SQL = String.format(
        "CREATE TABLE %s (%S INTEGER PRIMARY KEY, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, " +
                "%s TIMESTAMP DEFAULT CURRENT_TIMESTAMP)",
        TABLE_NAME_ITWORKERS, COLUMN_NAME_ID, COLUMN_NAME_PLANET_ID, COLUMN_NAME_MINERW, COLUMN_NAME_MINERS, COLUMN_NAME_MAINTW,
            COLUMN_NAME_MAINTS, COLUMN_NAME_ENTERW, COLUMN_NAME_ENTERS, COLUMN_NAME_TIME);

    //SQL To create the Mines Table

    public final String MINE_TABLE_SQL = String.format(
        "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT, %s INTEGER, %S INTEGER)", TABLE_NAME_MINES, COLUMN_NAME_ID, COLUMN_NAME_MINE,
            COLUMN_NAME_LEVEL, COLUMN_NAME_CURRENT);

    //SQL To create the Prefs Table

    public final String TABLE_NAME_PREFERENCES_SQL = String.format(
        "CREATE TABLE %s (%s TEXT UNIQUE ON CONFLICT REPLACE, %s TEXT)", TABLE_NAME_PREFERENCES, COLUMN_NAME_PREFERENCE, COLUMN_NAME_VALUE);

    //SQL To create the distance table

    public final String TABLE_NAME_DISTANCE_SQL = String.format(
        "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL," +
            " %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %s REAL)",
        TABLE_NAME_DISTANCE, COLUMN_NAME_ID, COLUMN_NAME_PLANET_0, COLUMN_NAME_PLANET_1, COLUMN_NAME_PLANET_2, COLUMN_NAME_PLANET_3,
            COLUMN_NAME_PLANET_4, COLUMN_NAME_PLANET_5, COLUMN_NAME_PLANET_6, COLUMN_NAME_PLANET_7, COLUMN_NAME_PLANET_8, COLUMN_NAME_PLANET_9,
            COLUMN_NAME_PLANET_10, COLUMN_NAME_PLANET_11, COLUMN_NAME_PLANET_12, COLUMN_NAME_PLANET_13, COLUMN_NAME_PLANET_14,
            COLUMN_NAME_PLANET_15, COLUMN_NAME_PLANET_16, COLUMN_NAME_PLANET_17, COLUMN_NAME_PLANET_18, COLUMN_NAME_PLANET_19);

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context.getApplicationContext();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PLANET_TABLE_SQL);
        db.execSQL(TABLE_NAME_POINTS_SQL);
        db.execSQL(TABLE_NAME_PRICING_SQL);
        db.execSQL(TABLE_NAME_WORKERS_SQL);
        db.execSQL(TABLE_NAME_ITWORKERS_SQL);
        db.execSQL(MINE_TABLE_SQL);
        db.execSQL(TABLE_NAME_PREFERENCES_SQL);
        db.execSQL(TABLE_NAME_DISTANCE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createDatabase() {
        if (!doesTableExist(TABLE_NAME_PLANETS)) {
            createGalaxy();
            createPoints();
            createPricing();
            createWorkerCount();
            createDistances();
        }
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void createGalaxy() {
        db = getWritableDatabase();
        db.delete(TABLE_NAME_PLANETS, null, null);
        AtomicInteger i = new AtomicInteger();
        while (i.get() < 20) {
            int size = ph.getSize();
            double radius = size / 2;
            double surfaceArea = ph.getSurfaceArea(radius);
            double minableCrust = ph.getMinableCrust(surfaceArea);
            double crustMass = ph.getCrustMass(minableCrust);
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ID, i.get());
            values.put(COLUMN_NAME_NAME, ph.getName());
            values.put(COLUMN_NAME_SIZE, size);
            values.put(COLUMN_NAME_POPULATION, 0);
            values.put(COLUMN_NAME_TOTAL_COPPER, ph.getCU(crustMass));
            values.put(COLUMN_NAME_TOTAL_SILVER, ph.getAG(crustMass));
            values.put(COLUMN_NAME_TOTAL_GOLD, ph.getAU(crustMass));
            values.put(COLUMN_NAME_TOTAL_PLATINUM, ph.getPT(crustMass));
            values.put(COLUMN_NAME_TOTAL_PALLADIUM, ph.getPD(crustMass));
            values.put(COLUMN_NAME_MINED_COPPER, 0);
            values.put(COLUMN_NAME_MINED_SILVER, 0);
            values.put(COLUMN_NAME_MINED_GOLD, 0);
            values.put(COLUMN_NAME_MINED_PLATINUM, 0);
            values.put(COLUMN_NAME_MINED_PALLADIUM, 0);
            values.put(COLUMN_NAME_ICON, ph.getIcon());
            db.insert(TABLE_NAME_PLANETS, null, values);
            i.getAndIncrement();
        }
        db.close();
    }

    public void createPoints() {
        db = getWritableDatabase();
        db.delete(TABLE_NAME_POINTS, null, null);
        AtomicInteger i = new AtomicInteger();
        while (i.get() <20) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ID, i.get());
            values.put(COLUMN_NAME_XCORD, ph.getCord());
            values.put(COLUMN_NAME_YCORD, ph.getCord());
            values.put(COLUMN_NAME_ZCORD, ph.getCord());
            db.insert(TABLE_NAME_POINTS,null,values);
            i.getAndIncrement();
        }
        db.close();
    }

    public void createPricing() {
        db = getWritableDatabase();
        db.delete(TABLE_NAME_PRICING, null, null);
        AtomicInteger i = new AtomicInteger();
        while (i.get() < 20) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ID, i.get());
            values.put(COLUMN_NAME_PRICE_COPPER, gh.getCuPrice());
            values.put(COLUMN_NAME_PRICE_SILVER, gh.getAgPrice());
            values.put(COLUMN_NAME_PRICE_GOLD, gh.getAuPrice());
            values.put(COLUMN_NAME_PRICE_PLATINUM, gh.getPtPrice());
            values.put(COLUMN_NAME_PRICE_PALLADIUM, gh.getPdPrice());
            db.insert(TABLE_NAME_PRICING, null, values);
            i.getAndIncrement();
        }
        db.close();
    }

    public void createWorkerCount() {
        db = getWritableDatabase();
        db.delete(TABLE_NAME_WORKERS, null, null);
        AtomicInteger i = new AtomicInteger();
        while (i.get() < 20) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ID, i.get());
            values.put(COLUMN_NAME_MINERS, 0);
            values.put(COLUMN_NAME_MINERS, 0);
            values.put(COLUMN_NAME_MAINTW, 0);
            values.put(COLUMN_NAME_MAINTS, 0);
            values.put(COLUMN_NAME_ENTERW, 0);
            values.put(COLUMN_NAME_ENTERS, 0);
            db.insert(TABLE_NAME_WORKERS, null, values);
            i.getAndIncrement();
        }
        db.close();
    }

    public void createDistances() {
        db = getWritableDatabase();
        db.delete(TABLE_NAME_DISTANCE, null, null);
        double[] distance = new double[20];
        AtomicInteger i= new AtomicInteger();
        while (i.get() < 20) {
            PointModel planet1 = getPlanetPoint(i.get());
            int j=0;
            while (j < 20) {
                PointModel planet2 = getPlanetPoint(j);

                if (i.get() == j) {
                    distance[j] = ph.calcDistFromHome(planet1);
                } else {
                    distance[j] = ph.calcDistBetweenPlanets(planet1, planet2);
                }
                j++;
            }
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_ID, i.get());
            values.put(COLUMN_NAME_PLANET_0, distance[0]);
            values.put(COLUMN_NAME_PLANET_1, distance[1]);
            values.put(COLUMN_NAME_PLANET_2, distance[2]);
            values.put(COLUMN_NAME_PLANET_3, distance[3]);
            values.put(COLUMN_NAME_PLANET_4, distance[4]);
            values.put(COLUMN_NAME_PLANET_5, distance[5]);
            values.put(COLUMN_NAME_PLANET_6, distance[6]);
            values.put(COLUMN_NAME_PLANET_7, distance[7]);
            values.put(COLUMN_NAME_PLANET_8, distance[8]);
            values.put(COLUMN_NAME_PLANET_9, distance[9]);
            values.put(COLUMN_NAME_PLANET_10, distance[10]);
            values.put(COLUMN_NAME_PLANET_11, distance[11]);
            values.put(COLUMN_NAME_PLANET_12, distance[12]);
            values.put(COLUMN_NAME_PLANET_13, distance[13]);
            values.put(COLUMN_NAME_PLANET_14, distance[14]);
            values.put(COLUMN_NAME_PLANET_15, distance[15]);
            values.put(COLUMN_NAME_PLANET_16, distance[16]);
            values.put(COLUMN_NAME_PLANET_17, distance[17]);
            values.put(COLUMN_NAME_PLANET_18, distance[18]);
            values.put(COLUMN_NAME_PLANET_19, distance[19]);
            db.insert(TABLE_NAME_DISTANCE, null, values);
            i.getAndIncrement();
        }
        db.close();
    }

    public void setTransitWorkers(int intPid, int intMinerW, int intMinerS, int intMaintW, int intMaintS, int intEnterW, int intEnterS) {
        db = getWritableDatabase();
        int recordNumber = countRecords(TABLE_NAME_ITWORKERS) + 1;
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

    public ArrayList<PlanetModel> loadGalaxy() {
        ArrayList<PlanetModel> planets = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ORDER BY %s", TABLE_NAME_PLANETS, COLUMN_NAME_ID);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql,
                null);
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

    public ArrayList<PricingModel> loadPricing() {
        ArrayList<PricingModel> pricing = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ORDER BY %s", TABLE_NAME_PRICING, COLUMN_NAME_ID);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql,
                null);
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

    public ArrayList<WorkerModel> loadWorkers() {
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

    public PointModel getPlanetPoint(int planetId) {
        PointModel returnModel = new PointModel();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s = %s", TABLE_NAME_POINTS, COLUMN_NAME_ID, planetId);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            returnModel.setId(cursor.getInt(0));
            returnModel.setX_cord(cursor.getDouble(1));
            returnModel.setY_cord(cursor.getDouble(2));
            returnModel.setZ_cord(cursor.getDouble(3));
        }
        return returnModel;
    }

    public WorkerModel getPlanetWorker(int planetId) {
        WorkerModel returnModel = new WorkerModel();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s = %s", TABLE_NAME_WORKERS, COLUMN_NAME_ID, planetId);
        @SuppressLint("Recycle") Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            returnModel.setId(cursor.getInt(0));
            returnModel.setMiners(cursor.getInt(1));
            returnModel.setMiners_supervisor(cursor.getInt(2));
            returnModel.setMaintenance(cursor.getInt(3));
            returnModel.setMaintenance_supervisor(cursor.getInt(4));
            returnModel.setEntertain(cursor.getInt(5));
            returnModel.setEntertain_supervisor(cursor.getInt(6));
        }
        return returnModel;
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