package com.kingdee.mobile.stat.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author SUNNY
 *
 */
public class DateUtil
{
	public static final long HOUR_MILLSECONDS = 3600*1000;
	public static final long DAY_MILLSECONDS = 24*HOUR_MILLSECONDS;
	public static final SimpleDateFormat fullFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
	
    /**
     * 获取目标时间与源时间相隔的天数,如20050901与20050930相隔29天
     * @param start 格式要求yyyy-MM-dd
     * @param end 格式要求yyyy-MM-dd
     * @return
     */
    public static int getIntervalDays(Date start, Date end)
    {
        long startMills = start.getTime();
        long endMills = end.getTime();
        int offset = 0;
        
        offset = (int)((endMills-startMills)/DAY_MILLSECONDS);

        return offset;
    }
    
	private DateUtil()
	{}
	
	/**
	 * 
	 * @param date
	 * @return
	 */
    public static int getDay_Of_Month(Date date)
    {
        GregorianCalendar gC = new GregorianCalendar();
        gC.setTime(date);
        return gC.get(GregorianCalendar.DAY_OF_MONTH);
    }
        
	/**
	 * get what hour is now
	 * @return
	 */
	public static int getCurrHour()
	{
		GregorianCalendar gC = new GregorianCalendar();
		return gC.get(GregorianCalendar.HOUR_OF_DAY);		
	}
  
     
    /**
     * 
     * @param start ��ʽҪ��yyyy-MM-dd
     * @param end ��ʽҪ��yyyy-MM-dd
     * @return
     */
    public static int getIntervalMonths(Date start, Date end)
    {
        GregorianCalendar startGC = new GregorianCalendar();
        GregorianCalendar endGC = new GregorianCalendar();
        startGC.setTime(start);
        endGC.setTime(end);
        
        int endY = endGC.get(GregorianCalendar.YEAR);
        int endM = endGC.get(GregorianCalendar.MONTH);
        int endD = endGC.get(GregorianCalendar.DAY_OF_MONTH);

        int startY = startGC.get(GregorianCalendar.YEAR);
        int startM = startGC.get(GregorianCalendar.MONTH);
        int startD = startGC.get(GregorianCalendar.DAY_OF_MONTH);   
        
        int offset = -1;
        
        if(endD==startD)
        {
        	offset = endM-startM+(endY-startY)*12;
        }
        
        return offset;
    }
    
    /**
     * 获取当前时间
     * @return Date
     */
    public static Date getNow()
    {
        return new Date();
    }
    
    /**
     * 获取明天日期
     * @return
     */
    public static Date getYesterday()
    {
        return addDays(-1);
    }
    
    /**
     * 当前日期后几天的日期
     * @param days int
     * @return Date
     */
    public static Date addDays(int days)
    {
        return addDays(new Date(), days);
    }

    /**
     * 计算日期后几天
     * @param srcDate Date
     * @param days int
     * @return Date
     */
    public static Date addDays(Date srcDate, int days)
    {
        GregorianCalendar gCanlendar = new GregorianCalendar();
        gCanlendar.setTime(srcDate);
        gCanlendar.add(gCanlendar.DAY_OF_MONTH,days);
        return gCanlendar.getTime();
    }

    /**
     * 
     * @param srcDate
     * @param months
     * @return
     */
    public static Date addMonths(Date srcDate, int months)
    {
        GregorianCalendar gCanlendar = new GregorianCalendar();
        gCanlendar.setTime(srcDate);
        gCanlendar.add(gCanlendar.MONTH, months);
        return gCanlendar.getTime();        
    }
    
    /**
     * 计算日期前几小时
     * @param srcDate Date
     * @param days int
     * @return Date
     */
    public static Date addTime(Date srcDate, int days)
    {
        GregorianCalendar gCanlendar = new GregorianCalendar();
        gCanlendar.setTime(srcDate);
        gCanlendar.add(gCanlendar.HOUR_OF_DAY,days);
        return gCanlendar.getTime();
    }
    
    
    /**
     * 
     * @param srcDate Date
     * @param year int
     * @return Date
     */
    public static Date addYears(Date srcDate, int year)
    {
        GregorianCalendar gCanlendar = new GregorianCalendar();
        gCanlendar.setTime(srcDate);
        gCanlendar.add(gCanlendar.YEAR,year);
        return gCanlendar.getTime();
    }

    /**
     * @param dest Date
     * @return int 0��ʾ��ȣ���1��ʾ��ǰʱ��ǰ��dest,1��ʾ��ǰʱ������dest
     */
    public static int compareTo(Date dest)
    {
        return compareTo(new Date(), dest);
    }

    /**
     * 比较两个日期
     * @param src Date
     * @param dest Date
     * @return int 
     */
    public static int compareTo(Date src, Date dest)
    {
        Date src1 = parse(format(src, "yyyy-MM-dd"),"yyyy-MM-dd");
        Date src2 = parse(format(dest,"yyyy-MM-dd"),"yyyy-MM-dd");
        int i = src1.compareTo(src2);
        return i;
    }
    
    public static int compareTo2(Date src, Date dest)
    {
        Date src1 = parse(format(src, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
        Date src2 = parse(format(dest,"yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss");
        int i = src1.compareTo(src2);
        return i;
    }

    /**
     * 格式化
     * @param d Date
     * @param pattern String
     * @return String
     */
    public static String format(Date d, String pattern)
    {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(d);
    }

    /**
     * 
     * @param date String
     * @param srcPattern String ԭ���ڸ�ʽ
     * @param destPattern String
     * @return String
     */
    public static String format(String date, String srcPattern, String destPattern)
    {
        Date d = parse(date, srcPattern);
        if(d == null)
        {
            return null;
        }

        return format(d, destPattern);
    }

    /**
     * 
     * @param date String
     * @param srcPattern String
     * @return Date
     */
    public static Date parse(String date, String pattern)
    {
        try
        {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            return format.parse(date);
        }
        catch(java.text.ParseException e)
        {
            return null;
        }
    }
    
    /**
     * @param date
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date modifyDate(Date date, int hour, int minute, int second)
    {
        GregorianCalendar gC = new GregorianCalendar();
        gC.set(gC.HOUR_OF_DAY, hour);
        gC.set(gC.MINUTE, minute);
        gC.set(gC.SECOND, second);
        return gC.getTime();
    }
    
    public static Date parse(String date)
    {
        return parse(date, "yyyy-MM-dd HH:mm:ss");
    }
    
    public static Date timeStampDate(String dateStr){
    	SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );

		Long timestamp = Long.parseLong(dateStr)*1000;
		String d = format.format(timestamp);

		Date date = new Date();
		try {
			date = format.parse(d);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
    }
    
   
    public static void main(String[] args)
    {
    	Date endDate = DateUtil.getNow();
		Date beginDate = DateUtil.getYesterday();
		String beginDateStr = DateUtil.format(beginDate, "yyyy-MM-dd");
		String endDateStr = DateUtil.format(endDate, "yyyy-MM-dd");
        System.out.println(beginDateStr);
        System.out.println(endDateStr);
    }
}
