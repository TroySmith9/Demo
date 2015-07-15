package com.test.junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestAfterRule implements TestRule {

	@Retention( RetentionPolicy.RUNTIME )
	@Target( { ElementType.METHOD } )
	public static @interface DataRestore {
		String[] value();
	}

	@Override
	public Statement apply( final Statement statement, final Description description ) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable  {
				try{
					statement.evaluate();
				}finally{
					DataRestore data = description.getAnnotation( DataRestore.class );
					if( data != null ) {
						String[] dataSetFiles = data.value();
						for( String dataSetFile : dataSetFiles ) {
							String[] args = dataSetFile.split( "/" );
							SQLHelper.execute( args );
						}
					}
				}
				
			}
		};
	}

}
