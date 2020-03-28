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
package com.app.recentlyvieweditemsfacades;

import de.hybris.platform.commercefacades.product.data.ProductData;

import java.util.List;

import org.springframework.http.ResponseEntity;

/**
 *
 */
public interface RecentViewedItemFacade
{
	/**
	 * Gets the recently viewed items.
	 *
	 * @return the recently viewed items
	 */
	ResponseEntity<List<ProductData>> getRecentlyViewedItems();

	/**
	 * Adds the recently viewed product.
	 *
	 * @param productCode
	 *           the product code
	 * @return the response entity
	 */
	ResponseEntity<String> addRecentlyViewedProduct(final String productCode);

}
