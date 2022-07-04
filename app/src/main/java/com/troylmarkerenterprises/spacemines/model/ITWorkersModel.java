/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.model.ITWorkersModel                                                                      *
 *  File Name: ITWorkersModel.java                                                                                                              *
 *  File Creation Date: 7/1/2022                                                                                                                *
 *  File Creation Time: 22:1:52                                                                                                                 *
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
package com.troylmarkerenterprises.spacemines.model;

@SuppressWarnings("unused")
public class ITWorkersModel {
    private int id;
    private int pid;
    private int intMinerWorker;
    private int intMinerSupervisor;
    private int maintenance_worker;
    private int maintenance_supervisor;
    private int entertain_worker;
    private int entertain_supervisor;

    public ITWorkersModel(int id, int pid, int intMinerWorker, int intMinerSupervisor, int maintenance_worker, int maintenance_supervisor,
                          int entertain_worker, int entertain_supervisor) {
        this.id = id;
        this.pid = pid;
        this.intMinerWorker = intMinerWorker;
        this.intMinerSupervisor = intMinerSupervisor;
        this.maintenance_worker = maintenance_worker;
        this.maintenance_supervisor = maintenance_supervisor;
        this.entertain_worker = entertain_worker;
        this.entertain_supervisor = entertain_supervisor;
    }

    public ITWorkersModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() { return pid; }

    public void setPid(int pid) { this.pid = pid; }

    public int getMinerw() {
        return intMinerWorker;
    }

    public void setMinerw(int intMinerWorker) {
        this.intMinerWorker = intMinerWorker;
    }

    public int getMiners() {
        return intMinerSupervisor;
    }

    public void setMiners(int intMinerSupervisor) {
        this.intMinerSupervisor = intMinerSupervisor;
    }

    public int getMaintw() {
        return maintenance_worker;
    }

    public void setMaintw(int maintenance_worker) {
        this.maintenance_worker = maintenance_worker;
    }

    public int getMaints() {
        return maintenance_supervisor;
    }

    public void setMaints(int maintenance_supervisor) {
        this.maintenance_supervisor = maintenance_supervisor;
    }

    public int getEnterw() {
        return entertain_worker;
    }

    public void setEnterw(int entertain_worker) {
        this.entertain_worker = entertain_worker;
    }

    public int getEnters() {
        return entertain_supervisor;
    }

    public void setEnters(int entertain_supervisor) {
        this.entertain_supervisor = entertain_supervisor;
    }

    public void clear() {
        this.intMinerWorker = 0;
        this.intMinerSupervisor = 0;
        this.maintenance_worker = 0;
        this.maintenance_supervisor = 0;
        this.entertain_worker = 0;
        this.entertain_supervisor = 0;
    }
}