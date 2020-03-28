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
package com.app.recentlyvieweditemsfacades.strategies;

import java.util.List;


/**
 *
 */
public interface RetrievalStrategy
{
	/**
	 * Register productCode and/or categoryCode visited.
	 *
	 * @param productCode
	 *           product code visited.
	 * @param categoryCode
	 *           category code visited.
	 */
	void productVisited(String productCode, String categoryCode);

	/**
	 * @return {@link List} of visited product codes.
	 */
	List<String> getRecentViewedProducts();

	/**
	 * @return {@link List} of visited category codes.
	 */
	List<String> getRecentViewedCategories();
}
