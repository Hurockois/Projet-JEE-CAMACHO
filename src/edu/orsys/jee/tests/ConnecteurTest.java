package edu.orsys.jee.tests;


import java.sql.Connection;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import edu.orsys.jee.Connecteur;

public class ConnecteurTest {
	 
	  @InjectMocks private Connecteur dbConnection;
	  @Mock private Connection mockConnection;
	  @Mock private Statement mockStatement;
	 
	  @Before
	  public void setUp() {
	    //MockitoAnnotations.initMocks(this);
	  }
	  
	  @Ignore
	  @Test
	  public void testMockDBConnection() throws Exception {
	    Mockito.when(mockConnection.createStatement()).thenReturn(mockStatement);
	    Mockito.when(mockConnection.createStatement().executeUpdate(Mockito.any())).thenReturn(1);
	    int value = dbConnection.executeQuery("");
	    Assert.assertEquals(value, 1);
	    Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
	  }
	}