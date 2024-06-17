package com.dtek.automation;

import com.dtek.automation.pages.SearchPage;
import com.dtek.automation.utils.DriverFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MobileTests extends BaseTest {

	@Test
	@DisplayName("Test #1")
	void searchDTEK() {
		SearchPage searchPage = new SearchPage(DriverFactory.getDriver());
		searchPage.searchText("ДТЕК");
		Assertions.assertTrue(searchPage.linksContains("dtek") > 10);
		Assertions.assertTrue( searchPage.titlesContains("ДТЕК") > 10);
	}

}
