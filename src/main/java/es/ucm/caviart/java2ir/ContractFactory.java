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

import spoon.reflect.code.CtComment;

import java.util.*;

/**
 * The ContractFactory creates {@link MethodContract}s. It has a single
 * static method, {@link ContractFactory#of(CtComment)}, which returns an
 * implementation of a {@link MethodContract}.
 * <p>
 * For information on qualifying comments, see {@link #qualifies(CtComment)}.
 *
 * @author Santiago Saavedra
 */
public class ContractFactory {
    private static final List<String> keywords = Arrays.asList(
            "precd",
            "postcd",
            "variant"
    );

    /**
     * Returns a MethodContract from a qualifying CtComment.
     *
     * @param comment the comment from which to make the MethodContract
     * @return a {@link MethodContract} for that method
     */
    public static MethodContract of(CtComment comment) {
        if (!qualifies(comment)) {
            throw new RuntimeException("Unexpected non-conforming comment: " +
                    comment);
        }

        String content = comment.getContent();
        Map<AssertionType, StringBuilder> formulae_builder = new HashMap<>();

        Arrays.stream(AssertionType.values()).forEach(a ->
                formulae_builder.put(a, new StringBuilder())
        );

        AssertionType lastType = null;

        for (final String line : content.split("\n")) {
            final String noat = line.replaceFirst("[\\s@]+", "").trim();

            if (line.matches("^/\\*@") || line.matches("\\*/\\s*$"))
                continue;

            final Optional<AssertionType> newType =
                    Optional.ofNullable(
                            AssertionType.of(noat.split(" ", 2)[0])
                    );

            lastType = newType.orElse(lastType);

            String text = newType
                    .map(_a -> noat.split(" ", 2)[1])
                    .orElseGet(() -> " " + noat);

            formulae_builder.get(lastType).append(text);

        }

        return new MethodContractImpl(formulae_builder);
    }

    /**
     * Tests whether any {@link CtComment} is a qualifying comment to
     * represent a {@link MethodContract}.
     * <p>
     * Qualifying comments are expected to begin with <code>/*@</code> and end
     * in <code>@*</code><code>/</code>. Code in the comment may be preceeded
     * by <code>@</code> signs in the inner lines, following the similar
     * Javadoc convention with <code>*</code>.
     */
    public static boolean qualifies(CtComment c) {
        return (c.getCommentType() == CtComment.CommentType.BLOCK
                && c.getContent().charAt(0) == '@');
    }

    public enum AssertionType {
        Precondition("precd"),
        Postcondition("postcd"),
        Variant("variant");


        public final String abbrev;

        AssertionType(String abbrev) {
            this.abbrev = abbrev;
        }

        public static AssertionType of(String name) {
            if ("precd".equalsIgnoreCase(name))
                return Precondition;
            if ("postcd".equalsIgnoreCase(name))
                return Postcondition;
            if ("variant".equalsIgnoreCase(name))
                return Variant;
            return null;
        }
    }

    private static class MethodContractImpl implements MethodContract {
        private final Map<AssertionType, Optional<String>> _m =
                new HashMap<>();

        MethodContractImpl(Map<AssertionType, StringBuilder> m) {
            m.entrySet().forEach(e -> {
                String s = e.getValue().toString().trim();

                if (s.isEmpty())
                    _m.put(e.getKey(), Optional.empty());
                else
                    _m.put(e.getKey(), Optional.of(s));
            });
        }

        @Override
        public Optional<String> getPrecondition() {
            return _m.get(AssertionType.Precondition);
        }

        @Override
        public Optional<String> getPostcondition() {
            return _m.get(AssertionType.Postcondition);
        }

        @Override
        public Optional<String> getVariant() {
            return _m.get(AssertionType.Variant);
        }
    }
}
