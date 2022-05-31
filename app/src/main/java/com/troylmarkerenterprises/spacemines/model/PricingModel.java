/******************************************************************************
 *   Project Name: Space Mines                                                *
 *   Class Name: com.troylmarkerenterprises.spacemines.model.Pricing          *
 *   File Name: Pricing.java                                                  *
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
package com.troylmarkerenterprises.spacemines.model;

public class PricingModel {

    private int id;
    private double cu;
    private double ag;
    private double au;
    private double pt;
    private double pd;

    public PricingModel(int id, double cu, double ag, double au, double pt, double pd) {
        this.id = id;
        this.cu = cu;
        this.ag = ag;
        this.au = au;
        this.pt = pt;
        this.pd = pd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCu() {
        return cu;
    }

    public void setCu(double cu) {
        this.cu = cu;
    }

    public double getAg() {
        return ag;
    }

    public void setAg(double ag) {
        this.ag = ag;
    }

    public double getAu() {
        return au;
    }

    public void setAu(double au) {
        this.au = au;
    }

    public double getPt() {
        return pt;
    }

    public void setPt(double pt) {
        this.pt = pt;
    }

    public double getPd() {
        return pd;
    }

    public void setPd(double pd) {
        this.pd = pd;
    }
}
