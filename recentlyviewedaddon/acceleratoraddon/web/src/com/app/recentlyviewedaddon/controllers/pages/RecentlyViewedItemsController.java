/**
 *
 */
package com.app.recentlyviewedaddon.controllers.pages;

import de.hybris.platform.addonsupport.controllers.page.AbstractAddOnPageController;
import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.product.ProductService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.app.recentlyvieweditemsfacades.RecentViewedItemFacade;


/**
 * @author Vigneswara.Moorthy
 *
 */
@Controller
@RequestMapping("/")
public class RecentlyViewedItemsController extends AbstractAddOnPageController
{
	/**
	 * The product service.
	 **/
	@Resource(name = "productService")
	private ProductService productService;


	/**
	 * The recent viewed item facade.
	 **/
	@Resource(name = "recentViewedItemFacade")
	private RecentViewedItemFacade recentViewedItemFacade;


	/**
	 * Gets the recently viewed items.
	 *
	 * @return the recently viewed items
	 */
	@RequestMapping(value = "/get-recentlyviewed-items", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<ProductData>> getRecentlyViewedItems()
	{
		return recentViewedItemFacade.getRecentlyViewedItems();
	}


	@RequestMapping(value = "/add-recentlyviewed-item", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<String> addRecentlyViewedProduct(
			@RequestParam(name = "productCode") final String productCode)
	{
		return recentViewedItemFacade.addRecentlyViewedProduct(productCode);
	}

}
