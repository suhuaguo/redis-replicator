/*
 * Copyright 2016-2018 Leon Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.moilioncircle.redis.replicator.cmd.parser;

import java.math.BigDecimal;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author Leon Chen
 * @since 2.6.0
 */
public class CommandParsers {

    public static byte[] objToBytes(Object object) {
        return (byte[]) object;
    }
    
    public static String objToString(Object object) {
        if (object == null) return null;
        return new String(objToBytes(object), UTF_8);
    }
    
    public static int objToInt(Object object) {
        return new BigDecimal(objToString(object)).intValueExact();
    }
    
    public static long objToLong(Object object) {
        return new BigDecimal(objToString(object)).longValueExact();
    }

}
