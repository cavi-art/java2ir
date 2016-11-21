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

import org.junit.Test;
import spoon.reflect.code.CtComment;
import spoon.support.reflect.code.CtCommentImpl;

import static org.junit.Assert.*;

/**
 * @author Santiago Saavedra
 */
public class ContractFactoryTest {
    @Test
    public void of1() throws Exception {
        CtComment comment = new CtCommentImpl();
        comment.setContent("/*@\n" +
                " @ @precd true \n" +
                " @ @postcd true \n" +
                "*/");

        MethodContract contract = ContractFactory.of(comment);

        assertTrue(contract.getPrecondition().isPresent());
        assertTrue(contract.getPostcondition().isPresent());
        assertFalse(contract.getVariant().isPresent());

        assertEquals("true", contract.getPrecondition().get());
    }

    @Test
    public void of2() throws Exception {
        CtComment comment = new CtCommentImpl();
        comment.setContent("/*@\n" +
                " @ @precd true ->\n" +
                " @        true \n" +
                " @ @postcd true \n" +
                "*/");

        MethodContract contract = ContractFactory.of(comment);

        assertTrue(contract.getPrecondition().isPresent());
        assertTrue(contract.getPostcondition().isPresent());
        assertFalse(contract.getVariant().isPresent());

        assertEquals("true -> true", contract.getPrecondition().get());
    }

}