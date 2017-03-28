package cn;

public class CompareVersion {

	public static void main( String[] args ) {
//		System.out.println
//				( value( "10.1.2.0" ) > value( "11.0.0.0" ) );
		System.out.println(convertToFloat(  "10.1.2.0.1"  ));
		System.out.println(convertToFloat(  "10.0"  ));
//		String version=  "10.1.2.0";
//		System.out.println(version.replaceAll( "\\.", "" ));
		
	}

	/**
	 * 将字符串转换为小数格式
	 * @param version
	 * @return
	 */
	private static float convertToFloat( String version ) {
		System.out.println("version:"+version);
		//版本号小于3位或只存在1个小数点，直接转化为float
		if( version.length() <= 3 || version.split( "\\." ).length <=2  )
			return Float.parseFloat( version );
		
		int  index=version.indexOf( "." );
		//存在两个以上"." 将第二个后的小数点全删除
		String subversion = version.substring( index, version.length() ).replaceAll( "\\.", "" );
		
		version=version.substring( 0, index+1)+subversion;
		// version.
		return Float.parseFloat( version );
	}

	public static long value( final String str ) {
		if( str.contains( "." ) ) {
			final int index = str.lastIndexOf( "." );
			return value( str.substring( 0, index ) ) * 100	+ value( str.substring( index + 1 ) );
		} else
			return Long.valueOf( str );
	}
}