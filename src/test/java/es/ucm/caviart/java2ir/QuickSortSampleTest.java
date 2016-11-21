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

package es.ucm.caviart.java2ir;

import testfiles.QuickSortSample;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * @author Santiago Saavedra
 */
public class QuickSortSampleTest {
    @org.junit.Test
    public void quicksort() throws Exception {
        test_with_array(new String[]{"a", "b", "zzz", "AA", "9"});
        test_with_array(new Integer[]{1, 2, -5, 2});
        test_with_array(new Integer[]{0, 0, 1, 1, 6, -1, 0});
        test_with_array(new Integer[]{});
        test_with_array(new Integer[]{0});
    }

    public <T extends Comparable<T>> void test_with_array(T[] a) {
        T[] a2 = Arrays.copyOf(a, a.length);
        T[] a3 = Arrays.copyOf(a, a.length);

        // We sort with our implementation
        QuickSortSample.quicksort(a2);

        // We sort with Java's implementation
        Arrays.sort(a3);

        assertEquals(a2.length, a3.length);
        for (int i = 0; i < a2.length; i++) {
            assertEquals(a2[i], a3[i]);
        }
    }

}