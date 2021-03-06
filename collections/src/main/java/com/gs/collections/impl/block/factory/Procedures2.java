/*
 * Copyright 2013 Goldman Sachs.
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

package com.gs.collections.impl.block.factory;

import java.util.Collection;

import com.gs.collections.api.block.procedure.Procedure;
import com.gs.collections.api.block.procedure.Procedure2;

/**
 * Contains factory methods for creating {@link Procedure2} instances.
 */
public final class Procedures2
{
    public static final Procedure2<?, ?> ADD_TO_COLLECTION = new AddToCollection<Object>();

    private Procedures2()
    {
        throw new AssertionError("Suppress default constructor for noninstantiability");
    }

    public static <T, P> Procedure2<T, P> fromProcedure(Procedure<? super T> procedure)
    {
        return new ProcedureAdapter<T, P>(procedure);
    }

    public static <T> Procedure2<T, Collection<T>> addToCollection()
    {
        return (Procedure2<T, Collection<T>>) ADD_TO_COLLECTION;
    }

    private static final class ProcedureAdapter<T, P> implements Procedure2<T, P>
    {
        private static final long serialVersionUID = 1L;
        private final Procedure<? super T> procedure;

        private ProcedureAdapter(Procedure<? super T> procedure)
        {
            this.procedure = procedure;
        }

        public void value(T each, P parameter)
        {
            this.procedure.value(each);
        }
    }

    private static class AddToCollection<T> implements Procedure2<T, Collection<T>>
    {
        private static final long serialVersionUID = 1L;

        public void value(T each, Collection<T> target)
        {
            target.add(each);
        }
    }
}
