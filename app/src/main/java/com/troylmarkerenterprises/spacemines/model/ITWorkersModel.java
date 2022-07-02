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
    private int minerw;
    private int miners;
    private int maintw;
    private int maints;
    private int enterw;
    private int enters;

    public ITWorkersModel(int id, int pid, int minerw, int miners, int maintw, int maints, int enterw, int enters) {
        this.id = id;
        this.pid = pid;
        this.minerw = minerw;
        this.miners = miners;
        this.maintw = maintw;
        this.maints = maints;
        this.enterw = enterw;
        this.enters = enters;
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
        return minerw;
    }

    public void setMinerw(int minerw) {
        this.minerw = minerw;
    }

    public int getMiners() {
        return miners;
    }

    public void setMiners(int miners) {
        this.miners = miners;
    }

    public int getMaintw() {
        return maintw;
    }

    public void setMaintw(int maintw) {
        this.maintw = maintw;
    }

    public int getMaints() {
        return maints;
    }

    public void setMaints(int maints) {
        this.maints = maints;
    }

    public int getEnterw() {
        return enterw;
    }

    public void setEnterw(int enterw) {
        this.enterw = enterw;
    }

    public int getEnters() {
        return enters;
    }

    public void setEnters(int enters) {
        this.enters = enters;
    }
}
