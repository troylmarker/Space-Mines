/***********************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                  *
 *  Class Name: com.troylmarkerenterprises.spacemines.model.TimeModel                                                                          *
 *  File Name: TimeModel.java                                                                                                                  *
 *  File Creation Date: 7/15/2022                                                                                                              *
 *  File Creation Time: 17:54:50                                                                                                               *
 *  File Creator: troylmarker                                                                                                                  *
 *                                                                                                                                             *
 *  Copyright 2022  by Troy L Marker Enterprises                                                                                               *
 *                                                                                                                                             *
 *  Licensed under the Apache License, Version 2.0 (the "License"); You may not use this file except in compliance with the License.           *
 *  You may obtain a copy of the License at                                                                                                    *
 *                                                                                                                                             *
 *       http://www.apache.org/licenses/LICENSE-2.0                                                                                            *
 *                                                                                                                                             *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,      *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.                                                                   *
 *  See the License for the specific language governing permissions and limitations under the License.                                         *
 ***********************************************************************************************************************************************/
package com.troylmarkerenterprises.spacemines.model;

import androidx.annotation.NonNull;

import java.util.Locale;

public class TimeModel {

    final int time;
    final int hour;
    final int minute;
    final int second;

    public TimeModel(int time) {
        this.time = time;
        hour = this.time / 3600;
        int temp1 = hour * 3600;
        int temp2 = this.time - temp1;
        minute = temp2 / 60;
        int temp3 = (hour *3600) + (minute * 60);
        second = this.time - temp3;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format(Locale.getDefault(),"%02d:%02d:%02d", hour, minute, second);
    }
}
