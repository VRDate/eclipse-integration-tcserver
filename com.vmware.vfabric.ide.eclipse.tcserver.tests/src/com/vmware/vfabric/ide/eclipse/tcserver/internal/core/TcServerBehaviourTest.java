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
package com.vmware.vfabric.ide.eclipse.tcserver.internal.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.debug.core.DebugPlugin;
import org.eclipse.debug.core.ILaunchConfigurationType;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.internal.Server;
import org.eclipse.wst.server.core.internal.ServerType;

import com.vmware.vfabric.ide.eclipse.tcserver.internal.core.TcServerBehaviour;
import com.vmware.vfabric.ide.eclipse.tcserver.tests.support.TcServerFixture;

/**
 * @author Steffen Pingel
 */
public class TcServerBehaviourTest extends TestCase {

	private IServer server;

	public void testRuntimeVMArgumentsAsf60() throws Exception {
		runtimeVMArguments(TcServerFixture.V_6_0, null, "tomcat-6.0.19.A", "tomcat-6.0.19.A");
	}

	public void testRuntimeVMArgumentsCombined21() throws Exception {
		runtimeVMArguments(TcServerFixture.V_2_1, TcServerFixture.INST_COMBINED, TcServerFixture.INST_COMBINED,
				TcServerFixture.INST_COMBINED);
	}

	public void testRuntimeVMArgumentsSeparate20() throws Exception {
		runtimeVMArguments(TcServerFixture.V_2_0, TcServerFixture.INST_INSIGHT, "tomcat-6.0.25.A-SR01",
				TcServerFixture.INST_INSIGHT);
	}

	public void testSetupLaunchConfigurationDefaultArgs() throws Exception {
		server = TcServerFixture.V_6_0.createServer(null);
		server.publish(Server.PUBLISH_FULL, null);

		ILaunchConfigurationWorkingCopy wc = createLaunchConfiguration();
		((Server) server).setupLaunchConfiguration(wc, null);
		String args = wc.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, (String) null);
		assertTrue("Expected -Xmx512m -Xss192k in '" + args + "'", args.contains("-Xmx512m -Xss192k"));

		wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, args.replace("-Xmx512m", "-Xmx123m"));
		((Server) server).setupLaunchConfiguration(wc, null);
		args = wc.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, (String) null);
		assertTrue("Expected -Xmx123m -Xss192k in '" + args + "'", args.contains("-Xmx123m -Xss192k"));

		wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, args.replace("-Xss192k", ""));
		((Server) server).setupLaunchConfiguration(wc, null);
		args = wc.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, (String) null);
		assertTrue("Expected -Xmx123m in '" + args + "'", args.contains("-Xmx123m"));
		assertTrue("Expected -Xss192k in '" + args + "'", args.contains("-Xss192k"));
	}

	public void testTomcatLocationAsfLayout60() throws Exception {
		server = TcServerFixture.V_6_0.createServer(null);
		server.publish(Server.PUBLISH_FULL, null);

		ILaunchConfigurationWorkingCopy wc = createLaunchConfiguration();
		wc.setAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, "-Xmx123m");
		((Server) server).setupLaunchConfiguration(wc, null);
		String args = wc.getAttribute(IJavaLaunchConfigurationConstants.ATTR_VM_ARGUMENTS, (String) null);
		assertTrue("Expected -Xmx123m in '" + args + "'", args.contains("-Xmx123m"));
		assertTrue("Expected -Xss192k in '" + args + "'", args.contains("-Xss192k"));
	}

	private ILaunchConfigurationWorkingCopy createLaunchConfiguration() throws CoreException {
		ILaunchManager launchManager = DebugPlugin.getDefault().getLaunchManager();
		ILaunchConfigurationType launchConfigType = ((ServerType) server.getServerType()).getLaunchConfigurationType();

		String launchName = launchManager.generateUniqueLaunchConfigurationNameFrom("Tc Server Launch");
		ILaunchConfigurationWorkingCopy wc = launchConfigType.newInstance(null, launchName);
		return wc;
	}

	private List<String> expectedArgs(String runtime, String instance) {
		List<String> args = new ArrayList<String>();
		IPath location = server.getRuntime().getLocation();
		args.add("-Dcatalina.base=\"" + location.append(instance).toOSString() + "\"");
		args.add("-Dcatalina.home=\"" + location.append(runtime).toOSString() + "\"");
		args.add("-Dwtp.deploy=\"" + location.append(instance).append("wtpwebapps").toOSString() + "\"");
		args.add("-Djava.endorsed.dirs=\"" + location.append(runtime).append("endorsed").toOSString() + "\"");
		return args;
	}

	private void runtimeVMArguments(TcServerFixture fixture, String instanceToCreate, String runtime, String instance)
			throws Exception {
		server = fixture.createServer(instanceToCreate);
		TcServerBehaviour behaviour = (TcServerBehaviour) server.loadAdapter(TcServerBehaviour.class, null);
		assertEquals(expectedArgs(runtime, instance), Arrays.asList(behaviour.getRuntimeVMArguments()));
	}

	@Override
	protected void tearDown() throws Exception {
		if (server != null) {
			TcServerFixture.deleteServerAndRuntime(server);
		}
	}

}