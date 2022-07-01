/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.model.PlanetModel                                                                         *
 *  File Name: PlanetModel.java                                                                                                                 *
 *  File Creation Date: 6/19/2022                                                                                                               *
 *  File Creation Time: 17:10:16                                                                                                                *
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
public class PlanetModel {
    private int id;
    private String name;
    private int size;
    private int population;
    private double t_CU;
    private double t_AG;
    private double t_AU;
    private double t_PT;
    private double t_PD;
    private double m_CU;
    private double m_AG;
    private double m_AU;
    private double m_PT;
    private double m_PD;
    private int icon;

    public PlanetModel(int id, String name, int size, int population, double t_CU, double t_AG, double t_AU, double t_PT, double t_PD,
                       double m_CU, double m_AG, double m_AU, double m_PT, double m_PD, int icon) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.population = population;
        this.t_CU = t_CU;
        this.t_AG = t_AG;
        this.t_AU = t_AU;
        this.t_PT = t_PT;
        this.t_PD = t_PD;
        this.m_CU = m_CU;
        this.m_AG = m_AG;
        this.m_AU = m_AU;
        this.m_PT = m_PT;
        this.m_PD = m_PD;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getT_CU() {
        return t_CU;
    }

    public void setT_CU(double t_CU) {
        this.t_CU = t_CU;
    }

    public double getT_AG() {
        return t_AG;
    }

    public void setT_AG(double t_AG) {
        this.t_AG = t_AG;
    }

    public double getT_AU() {
        return t_AU;
    }

    public void setT_AU(double t_AU) {
        this.t_AU = t_AU;
    }

    public double getT_PT() {
        return t_PT;
    }

    public void setT_PT(double t_PT) {
        this.t_PT = t_PT;
    }

    public double getT_PD() {
        return t_PD;
    }

    public void setT_PD(double t_PD) {
        this.t_PD = t_PD;
    }

    public double getM_CU() {
        return m_CU;
    }

    public void setM_CU(double m_CU) {
        this.m_CU = m_CU;
    }

    public double getM_AG() {
        return m_AG;
    }

    public void setM_AG(double m_AG) {
        this.m_AG = m_AG;
    }

    public double getM_AU() {
        return m_AU;
    }

    public void setM_AU(double m_AU) {
        this.m_AU = m_AU;
    }

    public double getM_PT() {
        return m_PT;
    }

    public void setM_PT(double m_PT) {
        this.m_PT = m_PT;
    }

    public double getM_PD() {
        return m_PD;
    }

    public void setM_PD(double m_PD) {
        this.m_PD = m_PD;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}