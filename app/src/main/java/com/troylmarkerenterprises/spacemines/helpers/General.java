/******************************************************************************
 *   Project Name: Space Mines                                                *
 *   Class Name: com.troylmarkerenterprises.spacemines.helpers.General        *
 *   File Name: General.java                                                  *
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

import android.annotation.SuppressLint;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class General {
    @SuppressLint("DefaultLocale")
    public String getNamedNumber(double number) {
        AtomicReference<String> numberString;
        numberString = new AtomicReference<>("0 Mil");
        if (number > 1e+60) {
            numberString.set(String.format("%.2f Novemdecillion", number / 1e+60));
        }  else if (number >  1e+57 ) {
            numberString.set(String.format("%.2f Octodecillion", number / 1e+57));
        }  else if (number >  1e+54 ) {
            numberString.set(String.format("%.2f Septen-decillion", number / 1e+54));
        }  else if (number >  1e+51 ) {
            numberString.set(String.format("%.2f Sexdecillion", number / 1e+51));
        }  else if (number >  1e+48 ) {
            numberString.set(String.format("%.2f Quindecillion", number / 1e+48));
        }  else if (number >  1e+45 ) {
            numberString.set(String.format("%.2f Quattuordecillion", number / 1e+45));
        }  else if (number >  1e+42 ) {
            numberString.set(String.format("%.2f Tredecillion", number / 1e+42));
        }  else if (number >  1e+39 ) {
            numberString.set(String.format("%.2f Duodecillion", number / 1e+39));
        }  else if (number >  1e+36 ) {
            numberString.set(String.format("%.2f Undecillion", number / 1e+36));
        }  else if (number >  1e+33 ) {
            numberString.set(String.format("%.2f Decillion", number / 1e+33));
        }  else if (number >  1e+30 ) {
            numberString.set(String.format("%.2f Nonillion", number / 1e+30));
        }  else if (number >  1e+27 ) {
            numberString.set(String.format("%.2f Octillion", number / 1e+27));
        }  else if (number >  1e+24 ) {
            numberString.set(String.format("%.2f Septillion", number / 1e+24));
        }  else if (number >  1e+21 ) {
            numberString.set(String.format("%.2f Sextillion", number / 1e+21));
        }  else if (number >  1e+18 ) {
            numberString.set(String.format("%.2f Quintillion", number / 1e+18));
        }  else if (number >  1e+15 ) {
            numberString.set(String.format("%.2f Quadrillion", number / 1e+15));
        }  else if (number >  1e+12 ) {
            numberString.set(String.format("%.2f Trillion", number / 1e+12));
        }  else if (number >  1e+9 ) {
            numberString.set(String.format("%.2f Billion", number / 1e+9));
        }  else if (number >  1e+6 ) {
            numberString.set(String.format("%.2f Million", number / 1e+6));
        }  else if (number >  1e+3 ) {
            numberString.set(String.format("%.2f Thousand", number / 1e+3));
        } else
            numberString.set(String.format("%.2f", number));
        return numberString.get();
    }

    public double getCuPrice() {
        int cuPrice = new Random().nextInt((266875 - 18125) + 18125) + 18125;
        return cuPrice / 100000d;
    }

    public double getAgPrice() {
        int agPrice = new Random().nextInt((4288 - 397) + 397) + 397;
        return agPrice /100d;
    }

    public double getAuPrice() {
        int auPrice = new Random().nextInt((197114 - 34851) + 34851) + 34851;
        return auPrice / 100d;
    }

    public double getPtPrice() {
        int ptPrice = new Random().nextInt((204774 - 34245) + 34245) + 34245;
        return ptPrice / 100d;
    }

    public double getPdPrice() {
        int pdPrice = new Random().nextInt((287710 - 8570) + 8570) + 8570;
        return pdPrice / 100d;
    }
}
