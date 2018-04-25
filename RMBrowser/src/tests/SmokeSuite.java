package tests;

import org.junit.experimental.categories.Categories;
import org.junit.experimental.categories.Categories.IncludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import TestCategories.Documents;
import TestCategories.Smoke;

@RunWith(Categories.class)
@IncludeCategory(Documents.class)
@SuiteClasses({ CreateDocument.class })
public class SmokeSuite {

	
}

