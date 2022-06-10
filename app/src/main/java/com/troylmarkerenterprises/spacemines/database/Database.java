/******************************************************************************
 *   Project Name: Space Mines                                                *
 *   Class Name: com.troylmarkerenterprises.spacemines.database.Database      *
 *   File Name: Database.java                                                 *
 *   File Type: Interface file                                                *
 *   File Creation Date: 5/22/2022                                            *
 *   File Creator: tlmarker                                                   *
 *                                                                            *
 *   Copyright 2022  by Troy L Marker Enterprises                             *
 *                                                                            *
 *   Licensed under the Apache License, Version 2.0 (the "License");          *
 *   you may not use this file except in compliance with the License.         *
 *   You may obtain a copy of the License at                                  *
 *                                                                            *
 *       http://www.apache.org/licenses/LICENSE-2.0                           *
 *                                                                            *
 *   Unless required by applicable law or agreed to in writing, software      *
 *   distributed under the License is distributed on an "AS IS" BASIS,        *
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. *
 *   See the License for the specific language governing permissions and      *
 *   limitations under the License.                                           *
 ******************************************************************************/
package com.troylmarkerenterprises.spacemines.database;

import static com.troylmarkerenterprises.spacemines.constants.Db.COL_AG;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_AU;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_CU;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_CURRENT;

import static com.troylmarkerenterprises.spacemines.constants.Db.COL_ICON;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_ID;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_LEVEL;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_MINE;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_MINERS;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_MINERS_SUPERVISOR;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_MAINTENANCE;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_MAINTENANCE_SUPERVISOR;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_ENTERTAIN;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_ENTERTAIN_SUPERVISOR;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_M_AG;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_M_AU;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_M_CU;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_M_PD;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_M_PT;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_NAME;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_PD;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_POP;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_PT;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_P_AG;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_P_AU;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_P_CU;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_P_PD;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_P_PT;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_SIZE;
import static com.troylmarkerenterprises.spacemines.constants.Db.COL_TIME;
import static com.troylmarkerenterprises.spacemines.constants.Db.DB_NAME;
import static com.troylmarkerenterprises.spacemines.constants.Db.DB_PATH;
import static com.troylmarkerenterprises.spacemines.constants.Db.DB_VERSION;
import static com.troylmarkerenterprises.spacemines.constants.Db.IN_TRANSIT_WORKER_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Db.MINES_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Db.PLANETS_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Db.PRICING_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Db.WORKER_TABLE;
import static com.troylmarkerenterprises.spacemines.constants.Pref.COL_PREF;
import static com.troylmarkerenterprises.spacemines.constants.Pref.COL_VALUE;
import static com.troylmarkerenterprises.spacemines.constants.Pref.PREFS_TABLE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.troylmarkerenterprises.spacemines.helpers.General;
import com.troylmarkerenterprises.spacemines.helpers.Planet;
import com.troylmarkerenterprises.spacemines.model.PlanetModel;
import com.troylmarkerenterprises.spacemines.model.PricingModel;
import com.troylmarkerenterprises.spacemines.model.WorkerModel;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("unused")
public class Database extends SQLiteOpenHelper {

    SQLiteDatabase db;
    Planet ph = new Planet();
    General gh = new General();
    Context context;

    // SQL To create the Planet Table

    public final String PLANET_TABLE_SQL = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT NOT NULL," +
                    " %s INTEGER, %s INTEGER, %s REAL, %s REAL, %s REAL, %s REAL," +
                    " %s REAL, %s REAL, %s REAL, %s REAL, %s REAL, %S REAL," +
                    " %s INTEGER)",
            PLANETS_TABLE, COL_ID, COL_NAME, COL_SIZE, COL_POP, COL_CU, COL_AG,
            COL_AU, COL_PT, COL_PD, COL_M_CU, COL_M_AG, COL_M_AU, COL_M_PT,
            COL_M_PD, COL_ICON);

    // SQL To create the Pricing Table

    public final String PRICING_TABLE_SQL = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s REAL, %s REAL," +
                    " %s REAL, %s REAL, %s REAL)",
            PRICING_TABLE, COL_ID, COL_P_CU, COL_P_AG, COL_P_AU, COL_P_PT, COL_P_PD);

    //SQL To create the Worker Table

    public final String WORKER_TABLE_SQL = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s INTEGER, " +
                    " %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER)",
            WORKER_TABLE, COL_ID, COL_MINERS, COL_MINERS_SUPERVISOR, COL_MAINTENANCE,
            COL_MAINTENANCE_SUPERVISOR, COL_ENTERTAIN, COL_ENTERTAIN_SUPERVISOR);

    //SQL To create the In Transit Worker Table

    public final String TRANSIT_WORKER_TABLE_SQL = String.format(
            "CREATE TABLE %s (%S INTEGER PRIMARY KEY, %s INTEGER, " +
                    " %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT)",
            IN_TRANSIT_WORKER_TABLE, COL_ID, COL_MINERS, COL_MINERS_SUPERVISOR, COL_MAINTENANCE,
            COL_MAINTENANCE_SUPERVISOR, COL_ENTERTAIN, COL_ENTERTAIN_SUPERVISOR, COL_TIME);

    //SQL To create the Mines Table

    public final String MINE_TABLE_SQL = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY, %s TEXT," +
                    " %s INTEGER, %S INTEGER)",
            MINES_TABLE, COL_ID, COL_MINE, COL_LEVEL, COL_CURRENT);

    //SQL To create the Prefs Table

    public final String PREFS_TABLE_SQL = String.format(
            "CREATE TABLE %s (%s TEXT UNIQUE ON CONFLICT REPLACE, %s TEXT)",
            PREFS_TABLE, COL_PREF, COL_VALUE);

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context.getApplicationContext();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(PLANET_TABLE_SQL);
        db.execSQL(PRICING_TABLE_SQL);
        db.execSQL(WORKER_TABLE_SQL);
        db.execSQL(MINE_TABLE_SQL);
        db.execSQL(PREFS_TABLE_SQL);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void createDatabase() {
        createGalaxy();
        createPricing();
        initialWorkerCount();
    }

    @SuppressWarnings("IntegerDivisionInFloatingPointContext")
    public void createGalaxy() {
        db = getWritableDatabase();
        db.delete(PLANETS_TABLE, null, null);
        AtomicInteger i = new AtomicInteger();
        while (i.get() < 20) {
            int size = ph.getSize();
            double radius = size / 2;
            double surfaceArea = ph.getSurfaceArea(radius);
            double minableCrust = ph.getMinableCrust(surfaceArea);
            double crustMass = ph.getCrustMass(minableCrust);
            ContentValues values = new ContentValues();
            values.put(COL_ID, i.get());
            values.put(COL_NAME, ph.getName());
            values.put(COL_SIZE, size);
            values.put(COL_POP, 0);
            values.put(COL_CU, ph.getCU(crustMass));
            values.put(COL_AG, ph.getAG(crustMass));
            values.put(COL_AU, ph.getAU(crustMass));
            values.put(COL_PT, ph.getPT(crustMass));
            values.put(COL_PD, ph.getPD(crustMass));
            values.put(COL_M_CU, 0);
            values.put(COL_M_AG, 0);
            values.put(COL_M_AU, 0);
            values.put(COL_M_PT, 0);
            values.put(COL_M_PD, 0);
            values.put(COL_ICON, ph.getIcon());
            db.insert(PLANETS_TABLE, null, values);
            i.getAndIncrement();
        }
        db.close();
    }

    public ArrayList<PlanetModel> loadGalaxy() {
        ArrayList<PlanetModel> planets = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ORDER BY %s",
                PLANETS_TABLE, COL_ID);
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

    public void createPricing() {
        db = getWritableDatabase();
        db.delete(PRICING_TABLE, null, null);
        AtomicInteger i = new AtomicInteger();
        while (i.get() < 20) {
            ContentValues values = new ContentValues();
            values.put(COL_ID, i.get());
            values.put(COL_P_CU, gh.getCuPrice());
            values.put(COL_P_AG, gh.getAgPrice());
            values.put(COL_P_AU, gh.getAuPrice());
            values.put(COL_P_PT, gh.getPtPrice());
            values.put(COL_P_PD, gh.getPdPrice());
            db.insert(PRICING_TABLE, null, values);
            i.getAndIncrement();
        }
        db.close();
    }

    public void initialWorkerCount() {
        db = getWritableDatabase();
        db.delete(WORKER_TABLE, null, null);
        AtomicInteger i = new AtomicInteger();
        while (i.get() < 20) {
            ContentValues values = new ContentValues();
            values.put(COL_ID, i.get());
            values.put(COL_MINERS, 0);
            values.put(COL_MINERS_SUPERVISOR, 0);
            values.put(COL_MAINTENANCE, 0);
            values.put(COL_MAINTENANCE_SUPERVISOR, 0);
            values.put(COL_ENTERTAIN, 0);
            values.put(COL_ENTERTAIN_SUPERVISOR, 0);
            db.insert(WORKER_TABLE, null, values);
            i.getAndIncrement();
        }
        db.close();
    }

    public ArrayList<PricingModel> loadPricing() {
        ArrayList<PricingModel> pricing = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ORDER BY %s", PRICING_TABLE, COL_ID);
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
        String sql = String.format("SELECT * FROM %s ORDER BY %s", WORKER_TABLE, COL_ID);
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

    public boolean checkDataBase() {
        AtomicBoolean checkFlag = new AtomicBoolean(true);
        SQLiteDatabase checkDB;
        String testPath = DB_PATH + DB_NAME;

        try {
            checkDB = SQLiteDatabase.openDatabase(testPath, null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();
        } catch (SQLiteException e) {
            checkFlag.set(false);
        }
        return checkFlag.get();
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
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'", COL_VALUE, PREFS_TABLE, COL_PREF, preference);
        @SuppressLint("Recycle") Cursor result = db.rawQuery(query, null);
        result.moveToFirst();
        @SuppressLint("Range") String retValue = result.getString(result.getColumnIndex("value"));
        db.close();
        return retValue;
    }

    public void setPref(String pref, String val) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PREF, pref);
        values.put(COL_VALUE, val);
        db.insert(PREFS_TABLE, null, values);
        db.close();
    }
}
