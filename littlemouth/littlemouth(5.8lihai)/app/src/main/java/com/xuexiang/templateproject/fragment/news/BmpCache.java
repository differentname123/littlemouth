/*
 * Copyright (C) 2020 xuexiangjys(xuexiangjys@163.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.xuexiang.templateproject.fragment.news;

import android.content.Context;
import android.graphics.Bitmap;

public class BmpCache {
    public static Bitmap getHead() {
        return Head;
    }

    public static void setHead(Bitmap head) {
        Head = head;
    }

    public static Bitmap getContent() {
        return Content;
    }

    public static void setContent(Bitmap content) {
        Content = content;
    }

    private static Bitmap Head;
    private static Bitmap Content;
    private static String name;
    private static String contenttext;
    public static void clearBmpCache(){
        Head=null;
        contenttext=null;
        name=null;
        Content=null;
    }
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        BmpCache.name = name;
    }

    public static String getContenttext() {
        return contenttext;
    }

    public static void setContenttext(String contenttext) {
        BmpCache.contenttext = contenttext;
    }
}
