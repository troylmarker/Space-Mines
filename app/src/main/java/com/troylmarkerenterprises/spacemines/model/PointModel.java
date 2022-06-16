/*********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                *
 *  Class Name: com.troylmarkerenterprises.spacemines.model.PointModel                                                                       *
 *  File Name: PointModel.java                                                                                                               *
 *  File Creation Date: 6/19/2022                                                                                                            *
 *  File Creation Time: 17:46:6                                                                                                              *
 *  File Creator: troylmarker                                                                                                                *
 *                                                                                                                                           *
 *  Copyright 2022  by Troy L Marker Enterprises                                                                                             *
 *                                                                                                                                           *
 *  Licensed under the Apache License, Version 2.0 (the "License"); You may not use this file except in compliance with the License.         *
 *  You may obtain a copy of the License at                                                                                                  *
 *                                                                                                                                           *
 *       http://www.apache.org/licenses/LICENSE-2.0                                                                                          *
 *                                                                                                                                           *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,    *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                 *
 *  See the License for the specific language governing permissions and limitations under the License.                                       *
 *********************************************************************************************************************************************/
package com.troylmarkerenterprises.spacemines.model;

@SuppressWarnings("unused")
public class PointModel {
    private int id;
    private double x_cord;
    private double y_cord;
    private double z_cord;

    public PointModel(int id, double x_cord, double y_cord, double z_cord) {
        this.id = id;
        this.x_cord = x_cord;
        this.y_cord = y_cord;
        this.z_cord = z_cord;
    }

    public PointModel() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX_cord() {
        return x_cord;
    }

    public void setX_cord(double x_cord) {
        this.x_cord = x_cord;
    }

    public double getY_cord() {
        return y_cord;
    }

    public void setY_cord(double y_cord) {
        this.y_cord = y_cord;
    }

    public double getZ_cord() {
        return z_cord;
    }

    public void setZ_cord(double z_cord) {
        this.z_cord = z_cord;
    }
}