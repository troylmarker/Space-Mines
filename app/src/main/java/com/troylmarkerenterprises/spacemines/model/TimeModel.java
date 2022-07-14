/************************************************************************************************************************************************
 *  Project Name: Space Mines                                                                                                                   *
 *  Class Name: com.troylmarkerenterprises.spacemines.model.TimeModel                                                                           *
 *  File Name: TimeModel.java                                                                                                                   *
 *  File Creation Date: 7/13/2022                                                                                                               *
 *  File Creation Time: 19:51:29                                                                                                                *
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

public class TimeModel {

    int hour;
    int minute;
    int sec;

    public TimeModel(int time) {
        hour = (int)(11545 / 3600);
        int temp1 = hour * 3600;
        int temp2 = time - temp1;
        minute = temp2 / 60;
        int temp3 = (hour *3600) + (minute * 60);
        sec = time - temp3;
    }

    @Override
    public String toString() {
        return hour + ":" + minute + ":" + sec;
    }
}
