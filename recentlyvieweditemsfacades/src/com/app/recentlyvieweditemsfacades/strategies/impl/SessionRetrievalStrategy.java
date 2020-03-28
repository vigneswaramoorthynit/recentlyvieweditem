/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2020 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package com.app.recentlyvieweditemsfacades.strategies.impl;

import de.hybris.platform.recentvieweditemsservices.RecentViewedItemsService;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.recentlyvieweditemsfacades.strategies.RetrievalStrategy;


/**
 *
 */
public class SessionRetrievalStrategy implements RetrievalStrategy
{

	@Resource(name = "recentViewedItemsService")
	private RecentViewedItemsService recentViewedItemsService;

	private static final Logger logger = LoggerFactory.getLogger(SessionRetrievalStrategy.class);


	@Override
	public void productVisited(final String productCode, final String categoryCode)
	{
		logger.debug("SessionRetrievalStrategy: Product code {} is recently viewed", productCode);
		recentViewedItemsService.productVisited(productCode, categoryCode);
	}

	@Override
	public List<String> getRecentViewedProducts()
	{
		return recentViewedItemsService.getRecentViewedProducts();
	}

	@Override
	public List<String> getRecentViewedCategories()
	{
		return recentViewedItemsService.getRecentViewedCategories();
	}

}
