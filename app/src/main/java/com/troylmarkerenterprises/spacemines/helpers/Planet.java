/******************************************************************************
 *   Project Name: Space Mines                                                *
 *   Class Name: com.troylmarkerenterprises.spacemines.helpers.Planet         *
 *   File Name: Planet.java                                                   *
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
package com.troylmarkerenterprises.spacemines.helpers;

import java.util.Random;

public class Planet {

    public Planet() {

    }

    public String getName() {
        String retString;
        String firstLetter = Character.toString((char) ((char) new Random().nextInt(
                (77 - 65) + 1) + 65));
        String secondLetter = Character.toString((char) ((char) new Random().nextInt(
                (57 - 48) + 1) + 48));
        String thirdLetter = Character.toString((char) ((char) new Random().nextInt(
                (90 - 78) + 1) + 78));
        String forthLetter = Character.toString((char) ((char) new Random().nextInt(
                (57 - 48) + 1) + 48));
        String fifthLetter = Character.toString((char) ((char) new Random().nextInt(
                (57 - 48) + 1) + 48));
        String sixthLetter = Character.toString((char) ((char) new Random().nextInt(
                (57 - 48) + 1) + 48));

        retString = firstLetter + secondLetter + thirdLetter + "-" + forthLetter + fifthLetter +
                sixthLetter;
        return retString;
    }

    public Integer getSize() {
        return new Random().nextInt((90000 - 1000) + 1) + 1000;
    }

    public double getSurfaceArea(double r) {
        return 4.1762 * (Math.pow(r,3));
    }

    public double getMinableCrust(double sArea) {
        return sArea * .25;
    }

    public double getCrustMass(double mCrust) {
        return mCrust * 1471979520;
    }

    public double getCU(double cMass) {
        return cMass * .005;
    }

    public double getAG(double cMass) {
        return cMass * .0000075;
    }

    public double getAU(double cMass) {
        return cMass * .0000005;
    }

    public double getPT(double cMass) {
        return cMass * .0000004;
    }

    public double getPD(double cMass) {
        return cMass * .0000001;
    }

    public Integer getIcon() {
        return new Random().nextInt((9  - 1) + 1) + 1;
    }
}