/*
 * Copyright (c) 2016. The CAVI-ART Project Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.ucm.caviart.java2ir.results;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Santiago Saavedra
 */
public class ResultGathererSingleton {

    private static final ResultGathererSingleton instance;

    private Map<String, Object> data = new HashMap<>();

    static {
        instance = new ResultGathererSingleton();
    }

    private ResultGathererSingleton() {
    }

    /**
     * Returns the only instance of {@link ResultGathererSingleton} in the
     * classloader.
     *
     * @return the current ResultGathererSingleton instance
     */
    public static ResultGathererSingleton getInstance() {
        return instance;
    }

    /**
     * Re-initialize the singleton to process another unit.
     */
    public void reset() {
        data = new HashMap<>();
    }

    public Object get(String key) {
        return data.get(key);
    }

    public void put(String key, Object value) {
        data.put(key, value);
    }

    @SuppressWarnings("unchecked,unused")
    public <T> T get(String key, Class<T> resultingElement) {
        return (T) data.get(key);
    }


}
