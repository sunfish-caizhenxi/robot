package cn.molasoftware.core.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * 日期工具类 提供日期相关的公共方法
 * 
 * @author caizx
 * 
 */
public class DateUtil {
	public final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
	public final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	private static Log log = LogFactory.getLog(DateUtil.class);

	public static final long HOUR_MILLIS = 1000 * 60 * 60;
	public static final long DAY_MILLIS = HOUR_MILLIS * 24;
	public static final int EIGHT_HOUR_SECOND = 60 * 60 * 8;// 8小时
	public static final int DAY_SECOND = 60 * 60 * 24;// 24小时
	public static final long EIGHT_HOUR_MILLI_SECOND = EIGHT_HOUR_SECOND * 1000L;

	private static final String IS_DATE_REGEX = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2}$";
	private static final String IS_TIME_REGEX = "^[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}$";
	private static final String IS_DATETIME_REGEX = "^[0-9]{4}\\-[0-9]{1,2}\\-[0-9]{1,2} [0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}(\\.[0-9]{1,3})?$";

	
	/**
	 * 判断字符串是否为正确的日期格式
	 * 
	 * @param str
	 *            字符串日期
	 * @return 是否合法日期格式
	 */
	public static boolean isDate(final String date) {
		if (date == null) {
			return false;
		}

		return date.matches(IS_DATE_REGEX);
	}

	/**
	 * 判断字符串是否为正确的时间格式
	 * 
	 * @param time
	 *            格式:10:10:10
	 * @return 是否合法时间格式
	 */
	public static boolean isTime(final String time) {
		if (time == null) {
			return false;
		}
		return time.matches(IS_TIME_REGEX);
	}

	/**
	 * 判断字符串是否为正确的日期 + 时间格式
	 * 
	 * @param datetime
	 *            格式:2010-10-10 00:00:00
	 * @return 是否合法日期 + 时间格式
	 */
	public static boolean isDateTime(final String datetime) {
		if (datetime == null || datetime.length() == 0) {
			return false;
		}
		return datetime.matches(IS_DATETIME_REGEX);
	}

	/**
	 * 获取从1970年1月1日经历的天数</br>
	 * 
	 * @return 天数
	 */
	public static int getDayCount() {
		long daynum = (System.currentTimeMillis() + EIGHT_HOUR_MILLI_SECOND) / DAY_MILLIS;
		return (int) daynum;
	}

	/**
	 * 获取小时数量.</br>
	 * 
	 * @return 小时数
	 */
	public static int getHourCount() {
		long daynum = System.currentTimeMillis() / HOUR_MILLIS;
		return (int) daynum;
	}

	/**
	 * 获取天数
	 * 
	 * @param date
	 *            日期
	 * @return 天数
	 */
	public static int getDayCount(final Date date) {
		long daynum = (date.getTime() + EIGHT_HOUR_MILLI_SECOND) / DAY_MILLIS;
		return (int) daynum;
	}

	/**
	 * 获取天数
	 * 
	 * @param datetime
	 *            日期
	 * @return 天数
	 */
	public static int getDayCount(final String datetime) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = format.parse(datetime);
		} catch (ParseException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
		long daynum = (date.getTime() + EIGHT_HOUR_MILLI_SECOND) / DAY_MILLIS;
		return (int) daynum;

	}

	/**
	 * 获取当前日期
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return new Date(calendar.getTime().getTime());
	}

	/**
	 * 获取当前日期时间
	 * 
	 * @return
	 */
	public static Date getCurrentDateTime() {
		return new Date(Calendar.getInstance().getTime().getTime());
	}

	/**
	 * 获取当前日期时间 格式为 ：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateStr() {
		return format(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取当前日期时间 格式为 ：yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentDateStr(int delay) {
		long now = System.currentTimeMillis() + delay * 1000;
		Date tempDate = new Date(now);
		return format(tempDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 获取昨天的开始时间 如2014-04-22 00:00:00
	 * 
	 * @return
	 */
	public static Date getStartTimeOfYesterday() {
		Date date = new Date();
		date = DateUtil.addDate(date, -1);
		try {
			date = shortSdf.parse(shortSdf.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(date.getTime());
	}

	/**
	 * 获取昨天的这一天的开始时间 如2014-04-22 23:59:59
	 * 
	 * @return
	 */
	public static Date getEndTimeOfYesterday() {
		Date date = new Date();
		date = DateUtil.addDate(date, -1);
		try {
			date = longSdf.parse(shortSdf.format(date) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(date.getTime());
	}

	/**
	 * 获取当前日期的这一天的开始时间 如2014-04-22 00:00:00
	 * 
	 * @return
	 */
	public static Date getStartTimeOfToday() {
		Date now = new Date();
		try {
			now = shortSdf.parse(shortSdf.format(now));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(now.getTime());
	}

	/**
	 * 获取当前日期的这一天的开始时间 如2014-04-22 23:59:59
	 * 
	 * @return
	 */
	public static Date getEndTimeOfToday() {
		Date now = new Date();
		try {
			now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(now.getTime());
	}

	/**
	 * 获取指定日期的这一天的开始时间 如2014-04-22 00:00:00
	 * 
	 * @return
	 */
	public static Date getStartTimeOfDate(Date date) {
		Date now = date;
		try {
			now = shortSdf.parse(shortSdf.format(now));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(now.getTime());
	}

	/**
	 * 获取指定日期的这一天的开始时间 如2014-04-22 23:59:59
	 * 
	 * @return
	 */
	public static Date getEndTimeOfDate(Date date) {
		Date now = date;
		try {
			now = longSdf.parse(shortSdf.format(now) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(now.getTime());
	}

	/**
	 * 获取当前周的开始时间 如2014-04-22 00:00:00
	 * 
	 * @return
	 */
	public static Date getStartTimeOfCurrentWeek() {
		Calendar c = Calendar.getInstance();
		try {
			int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;
			c.add(Calendar.DATE, -weekday);
			c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date(c.getTime().getTime());
	}

	/**
	 * 获取当前周的开始时间 如2014-04-22 23:59:59
	 * 
	 * @return
	 */
	public static Date getEndTimeOfCurrentWeek() {
		Calendar c = Calendar.getInstance();
		try {
			int weekday = c.get(Calendar.DAY_OF_WEEK);
			c.add(Calendar.DATE, 8 - weekday);
			c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date(c.getTime().getTime());
	}

	/**
	 * 获取当前月的开始时间 如2014-01-01 00:00:00
	 * 
	 * @return
	 */
	public static Date getStartTimeOfCurrentMonth() {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.DATE, 1);
			now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(now.getTime());
	}

	/**
	 * 获取当前月的开始时间 如2014-01-31 23:59:59
	 * 
	 * @return
	 */
	public static Date getEndTimeOfCurrentMonth() {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.DATE, 1);
			c.add(Calendar.MONTH, 1);
			c.add(Calendar.DATE, -1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(now.getTime());
	}

	/**
	 * 当前季度的开始时间，即2012-01-1 00:00:00
	 * 
	 * @return
	 */
	public static Date getStartTimeOfCurrentQuarter() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9)
				c.set(Calendar.MONTH, 4);
			else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date(now.getTime());
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 * 
	 * @return
	 */
	public static Date getEndTimeOfCurrentQuarter() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3) {
				c.set(Calendar.MONTH, 2);
				c.set(Calendar.DATE, 31);
			} else if (currentMonth >= 4 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 8);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 10 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date(now.getTime());
	}

	/**
	 * 获取前/后半年的开始时间
	 * 
	 * @return
	 */
	public static Date getStartTimeOfHalfYear() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 0);
			} else if (currentMonth >= 7 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 6);
			}
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date(now.getTime());

	}

	/**
	 * 获取前/后半年的结束时间
	 * 
	 * @return
	 */
	public static Date getEndTimeOfHalfYear() {
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Date(now.getTime());
	}

	/**
	 * 获取当前年的开始时间 如2014-01-01 00:00:00
	 * 
	 * @return
	 */
	public static Date getStartTimeOfCurrentYear() {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.MONTH, 0);
			c.set(Calendar.DATE, 1);
			now = shortSdf.parse(shortSdf.format(c.getTime()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(now.getTime());
	}

	/**
	 * 获取当前年的开始时间 如2014-01-31 23:59:59
	 * 
	 * @return
	 */
	public static Date getEndTimeOfCurrentYear() {
		Calendar c = Calendar.getInstance();
		Date now = null;
		try {
			c.set(Calendar.MONTH, 11);
			c.set(Calendar.DATE, 31);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new Date(now.getTime());
	}

	/**
	 * 获取当前年份
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static int getCurrentMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取当前月份
	 * 
	 * @return
	 */
	public static int getCurrentDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前小时
	 * 
	 * @return 小时
	 */
	public static int getCurrentHour() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取当前分钟
	 * 
	 * @return 分钟
	 */
	public static int getCurrentMinute() {
		Calendar cld = Calendar.getInstance();
		return cld.get(Calendar.MINUTE);
	}

	/**
	 * 获取某一日期里的年份
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 获取某一日期所在的季度
	 * 
	 * @param date
	 * @return
	 */
	public static int getQuarter(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH) + 1;
		if (month <= 3) {
			return 1;
		} else if (month <= 6) {
			return 2;
		} else if (month <= 9) {
			return 3;
		} else {
			return 4;
		}
	}

	/**
	 * 获取某一日期里的月份
	 * 
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取某一日期里的号
	 * 
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 根据偏移天数、偏移月数，取得date对象
	 * 
	 * @param date
	 * @param offsetDay
	 * @param offsetMonth
	 * @return
	 */
	public static Date getDate(Date date, int offsetDay, int offsetMonth) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + offsetMonth);
		int curMonth = calendar.get(Calendar.MONTH);
		if (curMonth == 1) {// 二月
			if (calendar.get(Calendar.YEAR) % 4 == 0) {// 闰年
				if (offsetDay > 29) {
					calendar.set(Calendar.DAY_OF_MONTH, 29);
				} else {
					calendar.set(Calendar.DAY_OF_MONTH, offsetDay);
				}
			} else {
				if (offsetDay > 28) {
					calendar.set(Calendar.DAY_OF_MONTH, 28);
				} else {
					calendar.set(Calendar.DAY_OF_MONTH, offsetDay);
				}
			}
		} else if ((curMonth == 3) || (curMonth == 5) || (curMonth == 8) || (curMonth == 10)) {// 30天月
																								// 四、六、九、十一月
			if (offsetDay > 30) {
				calendar.set(Calendar.DAY_OF_MONTH, 30);
			} else {
				calendar.set(Calendar.DAY_OF_MONTH, offsetDay);
			}
		} else {// 31天月
			calendar.set(Calendar.DAY_OF_MONTH, offsetDay);
		}
		return calendar.getTime();
	}

	/**
	 * 取得每月的第一天date对象
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	/**
	 * 取得每月的第一天date对象
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(String date) {
		Date d = parse(date, "yyyy-MM");
		return getFirstDateOfMonth(d);
	}

	/**
	 * 获取某日期所在月的最后一天 如：2006-2 2006-2-1返回当月的最后一天的日期2006-02-28
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndDateOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	/**
	 * 获取某日期所在月的最后一天 如：2006-2 2006-2-1返回当月的最后一天的日期2006-02-28
	 * 
	 * @param date
	 * @return
	 */
	public static Date getEndDateOfMonth(String date) {
		Date d = parse(date, "yyyy-MM");
		return getEndDateOfMonth(d);
	}

	/**
	 * 获取某月的总天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(String date) {
		if (date == null) {
			return 0;
		}
		Date d = parse(date, "yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}

	/**
	 * 获取某月的总天数
	 * 
	 * @param date
	 * @return
	 */
	public static int getDaysOfMonth(Date date) {
		if (date == null) {
			return 0;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}

	/**
	 * 格式化日期 格式为：yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String format1(Date date) {
		return format(date, "yyyy-MM-dd");
	}

	/**
	 * 格式化日期 格式为：yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @return
	 */
	public static String format2(Date date) {
		return format(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 格式化日期
	 * 
	 * @param date
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		try {
			DateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * 把字符串转换成日期类型 格式为yyyy-MM-dd
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parse1(String dateStr) {
		return parse(dateStr, "yyyy-MM-dd");
	}

	/**
	 * 把字符串转换成日期类型 格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parse2(String dateStr) {
		return parse(dateStr, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 把字符串转换成日期类型 格式为HH:mm:ss
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date parse3(String dateStr) {
		return parse(dateStr, "HH:mm:ss");
	}

	/**
	 * 把字符串转换成日期类型
	 * 
	 * @param date
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static Date parse(String dateStr, String pattern) {
		if (StrUtil.isBlank(dateStr)) {
			return null;
		}
		try {
			DateFormat df = new SimpleDateFormat(pattern);
			return df.parse(dateStr);
		} catch (Exception ex) {
			log.error(ex.getMessage(), ex);
			return null;
		}
	}

	/**
	 * 将时间或者日期转换成int数组
	 * 
	 * @param datetime
	 *            时间
	 * @return int数组
	 */
	public static int[] parseToArray(String dateStr) {
		Date d = parse1(dateStr);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return new int[] { year, month, day };
	}

	/**
	 * 在某日期上增加一段时间
	 * 
	 * @param d
	 * @param time
	 * @return
	 */
	public static Date addTime(Date date, long time) {
		Date newDate = new Date(date.getTime() + time);
		return newDate;
	}

	/**
	 * 在某日期上增加天数
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + days);
		Date newDate = new Date(calendar.getTime().getTime());
		return newDate;
	}

	/**
	 * 在某个日期上增加月数.
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date date, int months) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + months);
		Date newDate = new Date(calendar.getTime().getTime());
		return newDate;
	}

	/**
	 * 获取两个日期之间相差的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDayDiff(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			throw new IllegalArgumentException("参数不能为null");
		}
		Date d1 = removeTimeOfDate(startDate);
		Date d2 = removeTimeOfDate(endDate);
		long diff = d2.getTime() - d1.getTime();
		return (int) (Math.round(diff / (1000 * 60 * 60 * 24d)));
	}

	/**
	 * 获取两个时间之间相差的分钟数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getMinuteDiff(String startTime, String endTime) {
		if (startTime == null || endTime == null) {
			throw new IllegalArgumentException("参数不能为null");
		}
		String[] startTimeArray = startTime.split(":");
		String[] endTimeArray = endTime.split(":");

		BigDecimal s1 = new BigDecimal(startTimeArray[0]);
		BigDecimal s2 = new BigDecimal(startTimeArray[1]);
		int startTimeMinutes = s1.multiply(new BigDecimal("60")).add(s2).intValue();

		BigDecimal e1 = new BigDecimal(endTimeArray[0]);
		BigDecimal e2 = new BigDecimal(endTimeArray[1]);
		int endTimeMinutes = e1.multiply(new BigDecimal("60")).add(e2).intValue();

		return new BigDecimal(Integer.toString(endTimeMinutes))
				.subtract(new BigDecimal(Integer.toString(startTimeMinutes))).intValue();

	}

	/**
	 * 把某日期中的时间部分去掉
	 * 
	 * @param date
	 * @return
	 */
	public static Date removeTimeOfDate(Date date) {
		if (date != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			return cal.getTime();
		}
		return null;
	}

	/**
	 * 创建时间
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static Date newDate(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal.getTime();
	}

	/**
	 * 创建时间
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static String newDateStr(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return format1(cal.getTime());
	}

	/**
	 * 根据日期返回毫秒数，格式为yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 *            日期
	 * @param time
	 *            时间
	 * @return 毫秒数
	 */
	public static long getTime(Date date, String time) {
		String[] timeArray = time.split(":");
		BigDecimal s1 = new BigDecimal(timeArray[0]);
		BigDecimal s2 = new BigDecimal(timeArray[1]);
		BigDecimal minutes = s1.multiply(new BigDecimal("60")).add(s2);
		long millis = minutes.multiply(new BigDecimal("60")).multiply(new BigDecimal("1000")).longValue();
		return date.getTime() + millis;
	}

	/**
	 * 根据字符串获取时间戳
	 * 
	 * 
	 * @param datetime
	 *            字符串时间
	 * @return
	 */
	public static long getTimestamp(final String datetime) {
		Calendar cal = Calendar.getInstance();

		int year = Integer.parseInt(datetime.substring(0, 4));
		int month = Integer.parseInt(datetime.substring(5, 7));
		int day = Integer.parseInt(datetime.substring(8, 10));

		int hour = Integer.parseInt(datetime.substring(11, 13));
		int minute = Integer.parseInt(datetime.substring(14, 16));
		int second = Integer.parseInt(datetime.substring(17, 19));

		cal.set(year, month - 1, day, hour, minute, second);
		if (datetime.length() > 19) {
			int mill = Integer.parseInt(datetime.substring(20));
			cal.set(Calendar.MILLISECOND, mill);
		} else {
			cal.set(Calendar.MILLISECOND, 0);
		}

		return cal.getTimeInMillis();
	}

	/**
	 * 获取当前时间戳
	 * 
	 * @return 当前时间，以毫秒为单位
	 */
	public static long getTimestamp() {
		Calendar cal = Calendar.getInstance();
		return cal.getTimeInMillis();
	}

	public static void main(String[] args) {
		System.out.println(longSdf.format(DateUtil.getStartTimeOfYesterday()));
		System.out.println(longSdf.format(DateUtil.getEndTimeOfYesterday()));

		System.out.println(longSdf.format(DateUtil.getStartTimeOfToday()));
		System.out.println(longSdf.format(DateUtil.getEndTimeOfToday()));

		System.out.println(longSdf.format(DateUtil.getStartTimeOfCurrentWeek()));
		System.out.println(longSdf.format(DateUtil.getEndTimeOfCurrentWeek()));

		System.out.println(longSdf.format(DateUtil.getStartTimeOfCurrentMonth()));
		System.out.println(longSdf.format(DateUtil.getEndTimeOfCurrentMonth()));

		System.out.println(longSdf.format(DateUtil.getStartTimeOfCurrentQuarter()));
		System.out.println(longSdf.format(DateUtil.getEndTimeOfCurrentQuarter()));

		System.out.println(longSdf.format(DateUtil.getStartTimeOfHalfYear()));
		System.out.println(longSdf.format(DateUtil.getEndTimeOfHalfYear()));

		System.out.println(longSdf.format(DateUtil.getStartTimeOfCurrentYear()));
		System.out.println(longSdf.format(DateUtil.getEndTimeOfCurrentYear()));

	}
}
