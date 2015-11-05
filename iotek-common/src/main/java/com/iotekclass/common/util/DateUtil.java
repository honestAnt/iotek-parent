package com.iotekclass.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.iotekclass.common.constants.Constants;

public final class DateUtil {

	// private static final int SECOND_TO_DAY = 86400;
	private static final int SECOND_TO_HOUR = 3600;
	private static final int SECOND_TO_MINUTE = 60;

	private DateUtil() {

	}

	/**
	 * 
	 * @Description: 获取当前时间
	 * @return
	 * @throws
	 */
	public static Date getNow() {
		return new Date();
	}

	/**
	 * 
	 * @Description: 获取当前时间(精确到毫秒)
	 * @return
	 * @throws
	 */
	public static String getNowTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String dateStr = format.format(getNow());
		return dateStr;
	}

	/**
	 * 
	 * Description: 根据秒钟计算时分秒 如(1小时20分16秒)
	 * 
	 * @param duration
	 * @return
	 * @throws
	 */
	public static String getShowTime(int duration) {
		int calcSeconds = duration;
		StringBuffer times = new StringBuffer();
		int hours = calcSeconds / SECOND_TO_HOUR;// 得到小时数
		if (hours > 0) {
			times.append(hours).append("小时");
			calcSeconds = calcSeconds % SECOND_TO_HOUR;
		}
		int minutes = calcSeconds / SECOND_TO_MINUTE;// 得到分钟数
		if (minutes > 0) {
			times.append(minutes).append("分");
			calcSeconds = calcSeconds % SECOND_TO_MINUTE;
		}
		int seconds = calcSeconds;
		times.append(seconds).append("秒");
		return times.toString();
	}

	/**
	 * 计算截止到当天的天数间隔
	 * 
	 * @param clacTime
	 * @return
	 */
	public static int clacDayPeriod(Date clacTime) {
		return clacTwoDaysPeriod(clacTime, new Date());// 间隔天数;
	}

	/**
	 * 计算两个天数的间隔
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int clacTwoDaysPeriod(Date start, Date end) {
		DateTimeFormatter format = DateTimeFormat.forPattern(Constants.SHORT_DATE_FORMAT);// 只精确到哪一天就好啦
		DateTime endTime = DateTime.parse(new DateTime(end).toString(format), format);// 当天
		DateTime startTime = DateTime.parse(new DateTime(start).toString(format), format);// 课程开放日
		Period p = new Period(startTime, endTime, PeriodType.days());
		int days = p.getDays();
		return days;// 间隔天数;
	}

	/**
	 * 计算两个天数的间隔(精确到秒)
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int clacTwoDaysPeriodSecond(Date start, Date end) {
		DateTimeFormatter format = DateTimeFormat.forPattern(Constants.LONG_DATE_FORMAT);// 只精确到哪一天就好啦
		DateTime endTime = DateTime.parse(new DateTime(end).toString(format), format);// 当天
		DateTime startTime = DateTime.parse(new DateTime(start).toString(format), format);// 课程开放日
		Period p = new Period(startTime, endTime, PeriodType.days());
		int days = p.getDays();
		return days;// 间隔天数;
	}

	/**
	 * 根据当前时间计算多久以前
	 * 
	 * @param currentTime
	 * @return
	 */
	public static String getTimeAge(Date currentTime) {
		DateTime start = new DateTime(currentTime);
		DateTime end = new DateTime();
		Period p = new Period(start, end, PeriodType.months());
		int months = p.getMonths();
		if (months > 0) {
			return months + "月前更新";
		} else {
			p = new Period(start, end, PeriodType.weeks());
			int weeks = p.getWeeks();
			if (weeks > 0) {
				return weeks + "周前更新";
			} else {
				p = new Period(start, end, PeriodType.days());
				int days = p.getDays();
				if (days > 0) {
					return days + "天前更新";
				} else {
					p = new Period(start, end, PeriodType.hours());
					int hours = p.getHours();
					if (hours > 0) {
						return hours + "小时前更新";
					} else {
						p = new Period(start, end, PeriodType.minutes());
						int minutes = p.getMinutes();
						if (minutes > 0) {
							return minutes + "分钟前更新";
						} else {
							return "刚刚更新";
						}
					}
				}
			}
		}
	}

	/**
	 * 
	 * @Description: 获取当前天的字符串 如:20080202
	 * @return
	 * @throws
	 */
	public static String getCurrentDateString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String format = df.format(new Date());
		return format;
	}

	/**
	 * 
	 * @Description: 获取当前天的字符串 如:20080202123455
	 * @return
	 * @throws
	 */
	public static String getCurrentDateSString() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String format = df.format(new Date());
		return format;
	}

	public static final Map<String, SimpleDateFormat> DATE_FORMATTER_MAP = new HashMap<String, SimpleDateFormat>();

	public static String getTimeInterval(Date date) {
		DateTime dateTime = new DateTime(date);
		int hour = dateTime.getHourOfDay();
		if ((hour >= 0) && (hour < 6)) {
			return "凌晨";
		} else if ((hour >= 6) && (hour < 12)) {
			return "上午";
		} else if ((hour >= 12) && (hour < 14)) {
			return "中午";
		} else if ((hour >= 14) && (hour < 18)) {
			return "下午";
		} else {
			return "晚上";
		}
	}

	public static String formatDate(Date date, String format) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat sdf = DATE_FORMATTER_MAP.get(format);
		if (null == sdf) {
			sdf = new SimpleDateFormat(format, Locale.US);
			sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
			DATE_FORMATTER_MAP.put(format, sdf);
		}
		synchronized (sdf) {
			// SimpleDateFormat is not thread safe
			return sdf.format(date);
		}

	}

	public static String date2String(Date date, String format) {
		return new SimpleDateFormat(format).format(date);
	}

	public static Date string2Date(String str) {
		Date date = null;
		SimpleDateFormat df_long = new SimpleDateFormat(Constants.LONG_DATE_FORMAT);
		SimpleDateFormat df_short = new SimpleDateFormat(Constants.SHORT_DATE_FORMAT);
		try {
			date = df_long.parse(str);
		} catch (ParseException e) {
			try {
				date = df_short.parse(str);
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
		}
		return date;
	}

	/**
	 * @Description: 获取格式化的时间：当前一小时内，显示为xx分钟前，一天内，显示为xx小时前，超过一天，一年内，为xx月xx日，超过一年，xx年xx月xx日
	 * @param occurTime和当前对比的时间字符串。为“yyyy-MM-dd HH:mm:ss”格式
	 * @author 
	 */
	public static String getTimeForListView(String occurTime) {
		if (occurTime == null) {
			return "";
		}
		SimpleDateFormat df = new SimpleDateFormat(Constants.LONG_DATE_FORMAT);
		Date date, now;
		Calendar cal = Calendar.getInstance();
		int year, now_year;
		String friendlyTime;
		try {
			date = df.parse(occurTime);
			cal.setTime(date);
			year = cal.get(Calendar.YEAR);
			now = new Date();
		} catch (ParseException e) {
			e.printStackTrace();
			return occurTime;
		}
		cal.clear();
		cal.setTime(now);
		now_year = cal.get(Calendar.YEAR);

		long l = now.getTime() - date.getTime();
		long month = l / (30 * 24 * 60 * 60 * 1000);
		long day = l / (24 * 60 * 60 * 1000);
		long hour = ((l / (60 * 60 * 1000)) - (day * 24));
		long min = ((l / (60 * 1000)) - (day * 24 * 60) - (hour * 60));
		long s = ((l / 1000) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (min * 60));

		if (year != now_year) {
			friendlyTime = new SimpleDateFormat(Constants.SHORT_DATE_FORMAT).format(date);
		} else if (day >= 1) {
			friendlyTime = new SimpleDateFormat(Constants.MONTH_DAY_ZH).format(date);
		} else if (day < 1) { // 一天内
			if (hour < 1) {
				if (min < 1) {
					friendlyTime = s + "秒钟前";
				} else {
					friendlyTime = min + "分钟前";
				}
			} else {
				friendlyTime = hour + "小时前";
			}
		} else {
			friendlyTime = new SimpleDateFormat("yyyy-MM-dd").format(date);
		}
		return friendlyTime;
	}

	private static void printMsg(String msg) {
		System.out.println("---> " + msg);
	}

	public static boolean bigOrSmall(Date oneDate, Date twoDate) {

		SimpleDateFormat f = new SimpleDateFormat("hhmmss"); //格式化为 hhmmss
		int d1Number = Integer.parseInt(f.format(oneDate).toString()); //将第一个时间格式化后转为int
		int d2Number = Integer.parseInt(f.format(twoDate).toString()); //将第二个时间格式化后转为int
		if (d1Number > d2Number) {

			return true; //oneDate更大
		} else {

			return false; //twoDate更大

		}

	}

	/**
	 * 计算当天是否处于指定的两个时间段之间
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isInterjacent(Date start, Date end) {
		if ((start != null) && (end != null)) {
			Date now = DateUtil.getNow();
			if (now.before(end) && (now.after(start))) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
//		String time = "2015-01-21 11:14:24";
//		String time_str = "2011-03-05";
//		Date date = string2Date(time_str);
//		printMsg(date2String(date, Constants.LONG_DATE_FORMAT));
//		String friendlyTime = getTimeForListView(time);
//		printMsg(friendlyTime);
		getYears(10);
	}

	public static String changeTime(int second) {
		int h = 0;
		int d = 0;
		int s = 0;
		int temp = second % 3600;
		if (second > 3600) {
			h = second / 3600;
			if (temp != 0) {
				if (temp > 60) {
					d = temp / 60;
					if ((temp % 60) != 0) {
						s = temp % 60;
					}
				} else {
					s = temp;
				}
			}
		} else {
			d = second / 60;
			if ((second % 60) != 0) {
				s = second % 60;
			}
		}

		return h + "小时" + d + "分钟" + s + "秒";
	}

	/**
	 * @Description: 秒换算时间——达到1分钟显示分钟、达到1小时显示小时
	 * @param second
	 * @return
	 * @throws
	 */
	public static String formatTime(int second) {
		String result = "";
		int hour = 0;
		int minute = 0;
		if (second > 3600) {
			hour = second / 3600;
			minute = (second % 3600) / 60;
			second = (second % 3600) % 60;
			result = hour + "小时" + minute + "分钟" + second + "秒";
		} else if (second > 60) {
			minute = second / 60;
			second = second % 60;
			result = minute + "分钟" + second + "秒";
		} else {
			result = second + "秒";
		}

		return result;
	}
	
	/**
	 * 
	 * @Description: 获取最近count年的年份
	 * @Author：gufeifei
	 * @Date：2015年8月6日 下午3:13:53
	 * @param count
	 * @return
	 * @throws
	 */
	public static List<Integer> getYears(int count) {
		List<Integer> list = new ArrayList<Integer>();
		Calendar date = Calendar.getInstance();
		for(int i=0 ; i<count ; i++){
			int year = date.get(Calendar.YEAR)-i;
			list.add(year);
		}
		return list;
	}
	
}
