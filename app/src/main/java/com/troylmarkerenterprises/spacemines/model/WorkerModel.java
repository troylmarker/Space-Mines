/******************************************************************************
 *   Project Name: Space Mines                                                *
 *   Class Name: com.troylmarkerenterprises.spacemines.model.WorkerModel      *
 *   File Name: WorkerModel.java                                              *
 *   File Creation Date: 6/9/2022                                             *
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
package com.troylmarkerenterprises.spacemines.model;

public class WorkerModel {
    private int id;
    private int miners;
    private int miners_supervisor;
    private int maintenance;
    private int maintenance_supervisor;
    private int entertain;
    private int entertain_supervisor;

    public WorkerModel(int id, int miners, int miners_supervisor, int maintenance, int maintenance_supervisor, int entertain, int entertain_supervisor) {
        this.id = id;
        this.miners = miners;
        this.miners_supervisor = miners_supervisor;
        this.maintenance = maintenance;
        this.maintenance_supervisor = maintenance_supervisor;
        this.entertain = entertain;
        this.entertain_supervisor = entertain_supervisor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMiners() {
        return miners;
    }

    public void setMiners(int miners) {
        this.miners = miners;
    }

    public int getMiners_supervisor() {
        return miners_supervisor;
    }

    public void setMiners_supervisor(int miners_supervisor) {
        this.miners_supervisor = miners_supervisor;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    public int getMaintenance_supervisor() {
        return maintenance_supervisor;
    }

    public void setMaintenance_supervisor(int maintenance_supervisor) {
        this.maintenance_supervisor = maintenance_supervisor;
    }

    public int getEntertain() {
        return entertain;
    }

    public void setEntertain(int entertain) {
        this.entertain = entertain;
    }

    public int getEntertain_supervisor() {
        return entertain_supervisor;
    }

    public void setEntertain_supervisor(int entertain_supervisor) {
        this.entertain_supervisor = entertain_supervisor;
    }
}
