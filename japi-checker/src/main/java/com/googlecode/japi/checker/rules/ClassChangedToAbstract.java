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
package com.googlecode.japi.checker.rules;

import com.googlecode.japi.checker.DifferenceType;
import com.googlecode.japi.checker.Reporter;
import com.googlecode.japi.checker.model.JavaItem;

// CLASS
public class ClassChangedToAbstract implements Rule {

    @Override
    public void checkBackwardCompatibility(Reporter reporter, JavaItem reference, JavaItem newItem) {

        if (!reference.isAbstract() && newItem.isAbstract()) {
			reporter.report(reference, newItem,
					DifferenceType.CLASS_CHANGED_TO_ABSTRACT, reference);
        }
    }

}
