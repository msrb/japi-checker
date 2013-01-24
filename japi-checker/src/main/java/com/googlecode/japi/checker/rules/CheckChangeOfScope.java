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

import com.googlecode.japi.checker.Reporter;
import com.googlecode.japi.checker.Scope;
import com.googlecode.japi.checker.Reporter.Report;
import com.googlecode.japi.checker.Rule;
import com.googlecode.japi.checker.model.JavaItem;

// GENERAL
public class CheckChangeOfScope implements Rule {

    @Override
    public void checkBackwardCompatibility(Reporter reporter, JavaItem reference, JavaItem newItem) {
    	
    	// API type or public or protected member
        if ((reference.getOwner() == null && newItem.getOwner() == null) || 
        	(reference.getOwner().getVisibility().isMoreVisibleThan(Scope.PACKAGE) &&
        	reference.getOwner().getVisibility().isMoreVisibleThan(Scope.PACKAGE))) {
        	
        	if (reference.getVisibility().isMoreVisibleThan(Scope.PACKAGE)) {
        		// TODO this condition prevents to detect following incompatible change in this rule
        		// http://wiki.apidesign.org/wiki/InvisibleAbstractMethod
        		// It will be better to create a special rule for this change
        	
        		if (newItem.getVisibility().getValue() < reference.getVisibility().getValue()) {
        			// lower visibility
					reporter.report(new Report(Reporter.Level.ERROR,
							"The visibility of the " + reference.getItemType() + " "
							+ reference
							+ " has been changed from "
							+ reference.getVisibility() + " to "
							+ newItem.getVisibility(),
							reference, newItem));
        		} else if (newItem.getVisibility().getValue() == reference.getVisibility().getValue()) {
        			// same visibility
					reporter.report(new Report(Reporter.Level.INFO,
							"The visibility of the " + reference.getItemType() + " "
							+ reference + " has not changed",
							reference, newItem));
        		} else {
        			// higher visibility
					reporter.report(new Report(Reporter.Level.WARNING,
							"The visibility of the " + reference.getItemType() + " "
							+ reference
							+ " has been changed from "
							+ reference.getVisibility() + " to "
							+ newItem.getVisibility(), 
							reference, newItem));
        		}
        	}
        }
    }
}
