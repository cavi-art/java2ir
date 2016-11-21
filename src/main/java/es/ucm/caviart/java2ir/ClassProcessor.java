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

import es.ucm.gpd.irparser.ast.ASTNode;
import es.ucm.gpd.irparser.ast.VerificationUnit;
import es.ucm.gpd.irparser.ast.tld.ToplevelDefinition;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtComment;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Santiago Saavedra
 */
public class ClassProcessor<T> extends AbstractProcessor<CtClass<T>> {
    @Override
    public void process(CtClass<T> clazz) {
        List<VerificationUnit> vus = new ArrayList<>();

        String className = clazz.getQualifiedName();
        Set<CtMethod<?>> classMethods = clazz.getAllMethods();

        for(CtMethod<?> m: classMethods) {
            vus.add(processMethod(m, className));
        }
    }

    private VerificationUnit processMethod(CtMethod<?> m, String scope) {
        // TODO: Check if there are any pre/post conditions
        List<CtComment> comments = m.getComments();

        CtBlock<?> body = m.getBody();

        // TODO: Parse the body

        // TODO: Return a proper verification unit
        String packageName = scope + ".." + m.getSimpleName();

        List<ToplevelDefinition> toplevelForms = null;


        List<String> usedPackages = Arrays.asList(
                "int.Int",
                "array.Array"
        );

        String documentation = "-- created by java2ir pre-alpha.";

        return new VerificationUnit(
                packageName,
                toplevelForms,
                usedPackages,
                documentation,
                null,
                null
        );
    }
}
