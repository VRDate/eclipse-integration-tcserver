/*******************************************************************************
 *  Copyright (c) 2012 VMware, Inc.
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 *  Contributors:
 *      VMware, Inc. - initial API and implementation
 *******************************************************************************/
package com.vmware.vfabric.ide.eclipse.tcserver.tests.ui;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author Kaitlin Duck Sherwood
 */
public class AllTcServerUiTests {

	public static Test suite() {
		TestSuite suite = new TestSuite(AllTcServerUiTests.class.getName());
		suite.addTestSuite(TcServerNewServerWizardUiTest.class);
		return suite;
	}

}
