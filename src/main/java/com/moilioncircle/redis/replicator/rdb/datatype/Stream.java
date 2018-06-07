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

package com.moilioncircle.redis.replicator.rdb.datatype;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;

/**
 * @author Leon Chen
 * @since 2.6.0
 */
public class Stream implements Serializable {
    private static final long serialVersionUID = 1L;
    private ID lastId;
    private NavigableMap<ID, Entry> entries;
    private List<Group> groups;

    public Stream() {

    }

    public Stream(ID lastId, NavigableMap<ID, Entry> entries, List<Group> groups) {
        this.lastId = lastId;
        this.entries = entries;
        this.groups = groups;
    }

    public ID getLastId() {
        return lastId;
    }

    public void setLastId(ID lastId) {
        this.lastId = lastId;
    }

    public NavigableMap<ID, Entry> getEntries() {
        return entries;
    }

    public void setEntries(NavigableMap<ID, Entry> entries) {
        this.entries = entries;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public static class Entry implements Serializable {
        private static final long serialVersionUID = 1L;
        private ID id;
        private Map<String, String> fields;
        private Map<byte[], byte[]> rawFields;

        public Entry() {

        }

        public Entry(ID id, Map<String, String> fields) {
            this(id, fields, null);
        }

        public Entry(ID id, Map<String, String> fields, Map<byte[], byte[]> rawFields) {
            this.id = id;
            this.fields = fields;
            this.rawFields = rawFields;
        }

        public ID getId() {
            return id;
        }

        public void setId(ID id) {
            this.id = id;
        }

        public Map<String, String> getFields() {
            return fields;
        }

        public void setFields(Map<String, String> fields) {
            this.fields = fields;
        }

        public Map<byte[], byte[]> getRawFields() {
            return rawFields;
        }

        public void setRawFields(Map<byte[], byte[]> rawFields) {
            this.rawFields = rawFields;
        }
    }

    public static class Group implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private ID id;
        private NavigableMap<ID, Nack> globalPendingEntryMap;
        private List<Consumer> consumers;
        private byte[] rawName;

        public Group() {

        }

        public Group(String name, ID id, NavigableMap<ID, Nack> globalPendingEntryMap, List<Consumer> consumers) {
            this(name, id, globalPendingEntryMap, consumers, null);
        }

        public Group(String name, ID id, NavigableMap<ID, Nack> globalPendingEntryMap, List<Consumer> consumers, byte[] rawName) {
            this.name = name;
            this.id = id;
            this.globalPendingEntryMap = globalPendingEntryMap;
            this.consumers = consumers;
            this.rawName = rawName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ID getId() {
            return id;
        }

        public void setId(ID id) {
            this.id = id;
        }

        public NavigableMap<ID, Nack> getGlobalPendingEntryMap() {
            return globalPendingEntryMap;
        }

        public void setGlobalPendingEntryMap(NavigableMap<ID, Nack> globalPendingEntryMap) {
            this.globalPendingEntryMap = globalPendingEntryMap;
        }

        public List<Consumer> getConsumers() {
            return consumers;
        }

        public void setConsumers(List<Consumer> consumers) {
            this.consumers = consumers;
        }

        public byte[] getRawName() {
            return rawName;
        }

        public void setRawName(byte[] rawName) {
            this.rawName = rawName;
        }
    }

    public static class Consumer implements Serializable {
        private static final long serialVersionUID = 1L;
        private String name;
        private long seenTime;
        private NavigableMap<ID, Nack> pendingEntryMap;
        private byte[] rawName;

        public Consumer() {

        }

        public Consumer(String name, long seenTime, NavigableMap<ID, Nack> pendingEntryMap) {
            this(name, seenTime, pendingEntryMap, null);
        }

        public Consumer(String name, long seenTime, NavigableMap<ID, Nack> pendingEntryMap, byte[] rawName) {
            this.name = name;
            this.seenTime = seenTime;
            this.pendingEntryMap = pendingEntryMap;
            this.rawName = rawName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getSeenTime() {
            return seenTime;
        }

        public void setSeenTime(long seenTime) {
            this.seenTime = seenTime;
        }

        public NavigableMap<ID, Nack> getPendingEntryMap() {
            return pendingEntryMap;
        }

        public void setPendingEntryMap(NavigableMap<ID, Nack> pendingEntryMap) {
            this.pendingEntryMap = pendingEntryMap;
        }

        public byte[] getRawName() {
            return rawName;
        }

        public void setRawName(byte[] rawName) {
            this.rawName = rawName;
        }
    }

    public static class Nack implements Serializable {
        private static final long serialVersionUID = 1L;
        private ID id;
        private Consumer consumer;
        private long deliveryTime;
        private long deliveryCount;

        public Nack() {

        }

        public Nack(ID id, Consumer consumer, long deliveryTime, long deliveryCount) {
            this.id = id;
            this.consumer = consumer;
            this.deliveryTime = deliveryTime;
            this.deliveryCount = deliveryCount;
        }

        public ID getId() {
            return id;
        }

        public void setId(ID id) {
            this.id = id;
        }

        public Consumer getConsumer() {
            return consumer;
        }

        public void setConsumer(Consumer consumer) {
            this.consumer = consumer;
        }

        public long getDeliveryTime() {
            return deliveryTime;
        }

        public void setDeliveryTime(long deliveryTime) {
            this.deliveryTime = deliveryTime;
        }

        public long getDeliveryCount() {
            return deliveryCount;
        }

        public void setDeliveryCount(long deliveryCount) {
            this.deliveryCount = deliveryCount;
        }
    }

    public static class ID implements Serializable, Comparable<ID> {
        private static final long serialVersionUID = 1L;
        private long ms;
        private long seq;

        public ID() {

        }

        public ID(long ms, long seq) {
            this.ms = ms;
            this.seq = seq;
        }

        public long getMs() {
            return ms;
        }

        public void setMs(long ms) {
            this.ms = ms;
        }

        public long getSeq() {
            return seq;
        }

        public void setSeq(long seq) {
            this.seq = seq;
        }

        @Override
        public String toString() {
            return ms + "-" + seq;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ID id = (ID) o;
            return ms == id.ms && seq == id.seq;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ms, seq);
        }

        @Override
        public int compareTo(ID that) {
            if (this.ms > that.ms) return 1;
            else if (this.ms < that.ms) return -1;
            else {
                if (this.seq > that.seq) return 1;
                else if (this.seq < that.seq) return -1;
                else return 0;
            }
        }

        public static Comparator<ID> comparator() {
            return new Comparator<ID>() {
                @Override
                public int compare(ID o1, ID o2) {
                    return o1.compareTo(o2);
                }
            };
        }
    }
}
