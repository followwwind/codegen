package com.wind.ftl;

import org.junit.Before;
import org.junit.Test;

import com.wind.util.ftl.HtmlUtil;

/**
 * 
 * @author wind
 *
 */
public class HtmlTest {
	
	@Before
    public void init(){

    }
	
	@Test
	public void genBaseForm(){
		HtmlUtil.genBaseForm();
	}

}
