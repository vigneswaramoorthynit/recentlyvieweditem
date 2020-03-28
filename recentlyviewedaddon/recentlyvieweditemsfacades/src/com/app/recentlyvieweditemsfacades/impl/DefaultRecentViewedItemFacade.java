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
package com.app.recentlyvieweditemsfacades.impl;

import de.hybris.platform.commercefacades.product.ProductFacade;
import de.hybris.platform.commercefacades.product.ProductOption;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.util.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;

import com.app.recentlyvieweditemsfacades.RecentViewedItemFacade;
import com.app.recentlyvieweditemsfacades.RetrievalSource;
import com.app.recentlyvieweditemsfacades.strategies.RetrievalStrategy;

/**
 *
 */
public class DefaultRecentViewedItemFacade implements RecentViewedItemFacade
{
	private static final Logger LOG = Logger.getLogger(DefaultRecentViewedItemFacade.class);

	/** The Constant SUCCESS_MESSAGE. */
	private static final String SUCCESS_MESSAGE = "is stored as recently viewed product";

	/** The Constant ERROR_MESSAGE. */
	private static final String ERROR_MESSAGE = "Failed to store recently viewed product";

	/** The product facade. */
	@Resource(name = "productVariantFacade")
	private ProductFacade productFacade;

	/** The retrieval strategies map. */
	@Resource(name = "retrievalStrategiesMap")
	private Map<RetrievalSource, RetrievalStrategy> retrievalStrategiesMap;


	/**
	 * Gets the retrieval strategy.
	 *
	 * @return the retrieval strategy
	 */
	private RetrievalStrategy getRetrievalStrategy()
	{
		return retrievalStrategiesMap.get(RetrievalSource.valueOf(Config.getParameter("recentviewed.retrieval.type")));
	}


	@Override
	public ResponseEntity<List<ProductData>> getRecentlyViewedItems()
	{
		if (CollectionUtils.isNotEmpty(getRetrievalStrategy().getRecentViewedProducts()))
		{
			final List<String> recentlyViewedProductsCodeList = getRetrievalStrategy().getRecentViewedProducts();
			final List<ProductData> productDataList = new ArrayList<ProductData>(recentlyViewedProductsCodeList.size());
			for (final String recentlyViewedProductCode : recentlyViewedProductsCodeList)
			{
				final List<ProductOption> extraOptions = Arrays.asList(ProductOption.BASIC, ProductOption.PRICE, ProductOption.IMAGES);
				final ProductData productData = productFacade.getProductForCodeAndOptions(recentlyViewedProductCode, extraOptions);
				productDataList.add(productData);
			}
			return ResponseEntity.ok().body(productDataList);
		}
		return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR).body(null);
	}

	@Override
	public ResponseEntity<String> addRecentlyViewedProduct(final String productCode)
	{
		try
		{
			getRetrievalStrategy().productVisited(productCode, null);
			return ResponseEntity.ok().body(productCode + SUCCESS_MESSAGE);
		}
		catch (final Exception e)
		{
			LOG.error("Exception Occured:", e);
			return ResponseEntity.ok().body(ERROR_MESSAGE);
		}
	}

}
