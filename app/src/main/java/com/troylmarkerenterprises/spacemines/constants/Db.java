/******************************************************************************
 *   Project Name: Space Mines                                                *
 *   Class Name: com.troylmarkerenterprises.spacemines.constants.DbCon        *
 *   File Name: Db.java                                                       *
 *   File Type: Class file                                                    *
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
package com.troylmarkerenterprises.spacemines.constants;

import android.annotation.SuppressLint;

public class Db {
    public static final String DB_NAME = "spacemines.db";
    @SuppressLint ("SdCardPath")
    public static final String DB_PATH = "/data/data/com.troylmarkerenterprises.spacemines/databases/";
    public static final int DB_VERSION = 1;
    public static final String PLANETS_TABLE = "planets";
    public static final String PRICING_TABLE = "pricing";
    public static final String WORKER_TABLE = "workers";
    public static final String MINES_TABLE = "mines";
    public static final String IN_TRANSIT_WORKER_TABLE = "transit_workers";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_POP = "population";
    public static final String COL_SIZE = "size";
    public static final String COL_MINE = "mine";
    public static final String COL_LEVEL = "level";
    public static final String COL_CURRENT = "current";
    public static final String COL_MINERS = "miners";
    public static final String COL_MINERS_SUPERVISOR = "miners_supervisor";
    public static final String COL_MAINTENANCE = "maintenance";
    public static final String COL_MAINTENANCE_SUPERVISOR = "maintenance_supervisor";
    public static final String COL_ENTERTAIN = "entertain";
    public static final String COL_ENTERTAIN_SUPERVISOR = "entertain_supervisor";
    public static final String COL_CU = "t_cu";
    public static final String COL_AG = "t_ag";
    public static final String COL_AU = "t_au";
    public static final String COL_PT = "t_pt";
    public static final String COL_PD = "t_pd";
    public static final String COL_M_CU = "m_cu";
    public static final String COL_M_AG = "m_ag";
    public static final String COL_M_AU = "m_au";
    public static final String COL_M_PT = "m_pt";
    public static final String COL_M_PD = "m_pd";
    public static final String COL_ICON = "planet_icon";
    public static final String COL_P_CU = "p_cu";
    public static final String COL_P_AG = "p_ag";
    public static final String COL_P_AU = "p_au";
    public static final String COL_P_PT = "p_pt";
    public static final String COL_P_PD = "p_pd";
    public static final String COL_TIME = "time";
}
