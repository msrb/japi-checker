/*
 * Copyright 2011 William Bernardet
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
package com.googlecode.japi.checker;

import java.util.ArrayList;
import java.util.List;

import com.googlecode.japi.checker.model.JavaItem;

public class MuxReporter implements Reporter {

    private List<Reporter> reporters = new ArrayList<Reporter>();
    
    @Override
    public void report(Difference difference) {
        for (Reporter reporter : reporters) {
            reporter.report(difference);
        }
    }
    
    @Override
    public void report(JavaItem referenceItem, JavaItem newItem,
    		DifferenceType differenceType, Object... args) {
    	// direct instantiation of Difference
    	Difference difference = new Difference(referenceItem, newItem, differenceType, args);
    	report(difference);
    }

    public void add(Reporter reporter) {
        reporters.add(reporter);
    }
    
}
