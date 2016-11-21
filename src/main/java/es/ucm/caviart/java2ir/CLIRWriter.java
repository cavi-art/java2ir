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


import es.ucm.gpd.irparser.ast.expr.Expression;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Santiago Saavedra
 */
public class CLIRWriter {
    private OutputStream out;

    public CLIRWriter(OutputStream out) {
        this.out = out;
    }

    public void writeNode(Expression node) {
        try {
            out.write(node.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
