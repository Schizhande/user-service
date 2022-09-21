package com.schizhande.userservice.suite;


import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.schizhande.userservice")
@ExcludePackages({"com.schizhande.userservice.suite", "com.schizhande.userservice.config"})
public class TestSuite {
}
