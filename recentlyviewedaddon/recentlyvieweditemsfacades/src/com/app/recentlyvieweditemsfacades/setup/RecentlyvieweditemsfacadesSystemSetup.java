/*
 * [y] hybris Platform
 *
 * Copyright (c) 2018 SAP SE or an SAP affiliate company. All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.app.recentlyvieweditemsfacades.setup;

import static com.app.recentlyvieweditemsfacades.constants.RecentlyvieweditemsfacadesConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.core.initialization.SystemSetup;

import java.io.InputStream;

import com.app.recentlyvieweditemsfacades.constants.RecentlyvieweditemsfacadesConstants;
import com.app.recentlyvieweditemsfacades.service.RecentlyvieweditemsfacadesService;


@SystemSetup(extension = RecentlyvieweditemsfacadesConstants.EXTENSIONNAME)
public class RecentlyvieweditemsfacadesSystemSetup
{
	private final RecentlyvieweditemsfacadesService recentlyvieweditemsfacadesService;

	public RecentlyvieweditemsfacadesSystemSetup(final RecentlyvieweditemsfacadesService recentlyvieweditemsfacadesService)
	{
		this.recentlyvieweditemsfacadesService = recentlyvieweditemsfacadesService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		recentlyvieweditemsfacadesService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return RecentlyvieweditemsfacadesSystemSetup.class.getResourceAsStream("/recentlyvieweditemsfacades/sap-hybris-platform.png");
	}
}
