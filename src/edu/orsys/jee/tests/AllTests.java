package edu.orsys.jee.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GestionPerimetresTest.class, AuthentificationTest.class, ConnecteurTest.class, EntityTest.class, ModelTest.class })
public class AllTests {

}
