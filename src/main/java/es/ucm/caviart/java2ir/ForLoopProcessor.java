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

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtExpression;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtStatement;

import java.util.List;

/**
 * @author Santiago Saavedra
 */
public class ForLoopProcessor extends AbstractProcessor<CtFor> {
    @Override
    public void process(CtFor element) {
        List<CtStatement> forInit = element.getForInit();
        List<CtStatement> forUpdate = element.getForUpdate();
        CtExpression<Boolean> expression = element.getExpression();

        // Change this into a recursive function

    }
}
