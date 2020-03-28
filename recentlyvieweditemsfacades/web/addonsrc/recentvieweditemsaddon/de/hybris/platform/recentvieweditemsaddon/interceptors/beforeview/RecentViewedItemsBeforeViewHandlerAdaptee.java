/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE or an SAP affiliate company.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package de.hybris.platform.recentvieweditemsaddon.interceptors.beforeview;

import de.hybris.platform.acceleratorservices.data.RequestContextData;
import de.hybris.platform.acceleratorservices.util.SpringHelper;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.pages.AbstractPageController;
import de.hybris.platform.addonsupport.interceptors.BeforeViewHandlerAdaptee;
import de.hybris.platform.category.model.CategoryModel;

import de.hybris.platform.cms2.model.pages.AbstractPageModel;
import de.hybris.platform.cms2.model.pages.CategoryPageModel;
import de.hybris.platform.cms2.model.pages.ProductPageModel;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.recentvieweditemsservices.RecentViewedItemsService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.ui.ModelMap;

/**
 * Capture the page (category page, product page) beforeView event, and check if there is product ID or category ID that 
 * can be added to the recently viewed items queues 
 * 
 *
 */
public class RecentViewedItemsBeforeViewHandlerAdaptee implements BeforeViewHandlerAdaptee
{

	private RecentViewedItemsService recentViewedItemsService;

	@Override
	public String beforeView(final HttpServletRequest request, final HttpServletResponse response, final ModelMap model,
			final String viewName) throws Exception
	{
		final AbstractPageModel pageModel = (AbstractPageModel) model.get(AbstractPageController.CMS_PAGE_MODEL);
		if (pageModel != null)
		{
			final RequestContextData requestContextData = SpringHelper.getSpringBean(request, "requestContextData", RequestContextData.class, true);
   		
			if( pageModel instanceof ProductPageModel )
			{      		
      		final ProductModel product = requestContextData.getProduct();
      		String productCode = product.getCode();
      		String categoryCode = null;
      		final List<CategoryModel> categories = (List<CategoryModel>) product.getSupercategories();
				if (categories != null)
				{
					categoryCode = categories.get(0).getCode();
				}
      		recentViewedItemsService.productVisited(productCode, categoryCode);
			}
		
			if ( pageModel instanceof CategoryPageModel)
			{
				final CategoryModel category = requestContextData.getCategory();
				String categoryCode = category.getCode();
				recentViewedItemsService.productVisited(null, categoryCode);
			}
		}
		return viewName;
	}

	@Required
	public void setRecentViewedItemsService(final RecentViewedItemsService recentViewedItemsService)
	{
		this.recentViewedItemsService = recentViewedItemsService;
	}
}