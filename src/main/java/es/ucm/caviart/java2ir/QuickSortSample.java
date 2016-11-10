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

import org.jetbrains.annotations.Contract;

import java.util.Arrays;

/**
 * @author Santiago Saavedra
 */
public class QuickSortSample {

    /**
     * QuickSort entry point.
     *
     * @param a   the array to be sorted
     * @param <T> the type of the array to sort, must be {@link Comparable}.
     * @precd a.length >= 0
     * @postcd sorted(a) && permut(a, 0, a.length)
     */
    public static <T extends Comparable<T>> void quicksort(T[] a) {
        qsort(a, 0, a.length - 1);
    }

    /**
     * @param v   the array to be sorted
     * @param i   the left index of the subarray to sort
     * @param j   the right index of the subarray to sort
     * @param <T> the type of the elements in the array to sort. Must be
     *            {@link Comparable}
     * @precd a.length >= 0
     * @postcd sorted_sub(a, i, j) && permut_sub(a, i, j)
     */
    private static <T extends Comparable<T>> void qsort(T[] v, int i, int j) {
        int p;

        if (i < j) {
            p = partition(v, i, j);
            qsort(v, i, p - 1);
            qsort(v, p + 1, j);
        }
    }

    @Contract(pure = true)
    private static <T extends Comparable<T>> int partition(
            T[] v, int i_min, int j
    ) {
        T piv = v[i_min];
        int pos_piv = i_min;

        for (int i = i_min + 1; i <= j; i++) {
            T vi = v[i];
            if (vi.compareTo(piv) <= 0) {
                pos_piv = pos_piv + 1;
                swap(v, i, pos_piv);
            }
        }
        swap(v, i_min, pos_piv);

        return pos_piv;
    }


    private static <T> void swap(T[] v, int i, int j) {
        if (i != j) {
            T tmp = v[i];
            v[i] = v[j];
            v[j] = tmp;
        }
    }

    private static <T> T[] swap2(T[] v, int i, int j) {
        if (i != j) {
            T tmp = v[i];
            T[] v2 = set(v, i, v[j]);
            T[] v3 = set(v2, j, tmp);
            return v3;
        }
        return v;
    }

    private static <T> T[] set(T[] v, int i, T t) {
        T[] v2 = Arrays.copyOf(v, v.length);
        v2[i] = t;
        return v2;
    }
}
