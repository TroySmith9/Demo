package junit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class TestBeforeRule implements TestRule {

	@Retention( RetentionPolicy.RUNTIME )
	@Target( { ElementType.METHOD } )
	public static @interface DataPrepare {
		String[] value();
	}
	
	@Override
	public Statement apply( final Statement statement, final Description description ) {
		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				//先根据DataRestore注解，清除已有的数据
//				DataRestore restore = description.getAnnotation(  DataRestore.class  );
//				if( restore != null ) {
//					String[] dataSetFiles = restore.value();
//					for( String dataSetFile : dataSetFiles ) {
//						String[] args = dataSetFile.split( "/" );
//						SQLHelper.execute( args[0], args[1], args[2] );
//					}
//				}
				System.out.println(description.getClassName() + "." + description.getMethodName() + "测试开始！");
				DataPrepare prepare = description.getAnnotation( DataPrepare.class );
				if( prepare != null ) {
					String[] dataSetFiles = prepare.value();
					for( String dataSetFile : dataSetFiles ) {
						String[] args = dataSetFile.split( "/" );
						SQLHelper.execute( args );
					}
				}else{
					
				}
				statement.evaluate();
			}
		};
	}

}
