package com.iotekclass.common.duobeiyun;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.joda.time.DateTime;

import com.google.common.collect.Maps;
import com.iotekclass.common.util.Config;

public class DuobeiYunClient {

	private static final SimpleDateFormat SF = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public static final String COURSE_TYPE_1v1 = "1";
	public static final String COURSE_TYPE_1vN = "2";
	public static final String COURSE_HAS_VIDEO = "1";
	public static final String COURSE_NO_VIDEO = "0";
	public static final String ROLE_STUDENT = "2";
	public static final String ROLE_TEACHER = "1";
	
	public String createRoom(String title, Date startTime, int duration, boolean video, String roomType) {

		String startTimeStr = SF.format(startTime);
		
		Map<String, String> params = Maps.newHashMap();
		params.put("title", title);
		params.put("startTime", startTimeStr);
		params.put("duration", String.valueOf(duration));
		params.put("video", String.valueOf(video));
		params.put("roomType", roomType);

		String url = Config.getLiveServerAddress() + "/api/v3/room/create";
		String res = postRequestDuobeiYun(url, params);
		
		return res;
	}
	public String merchantDetail() {
		
		Map<String, String> params = Maps.newHashMap();
		String sign = AppBulidSign.buildMysign(params, Config.getLiveAppKey());
		params.put("sign", sign);
		String timestamp = String.valueOf(new DateTime().plusMinutes(1).getMillis());

		params.put("partner", Config.getLivePartnerId());
		params.put("timestamp", timestamp);
		String url = Config.getLiveServerAddress() + "/api/v3/merchant";
		params = prepareParameters(params);
		String res = WebUtils.post(url, params);
		return res;
	}
	
	public String updateRoomTitle(String roomId, String newTitle) {

		Map<String, String> params = Maps.newHashMap();
		params.put("title", newTitle);
		params.put("roomId", roomId);

		String url = Config.getLiveServerAddress() + "/api/v3/room/update/title";
		String res = postRequestDuobeiYun(url, params);
		
		return res;
	}
	
	public String updateRoomSchedule(String roomId, Date startTime, int duration) {
		String startTimeStr = SF.format(startTime);
		
		Map<String, String> params = Maps.newHashMap();
		params.put("roomId", roomId);
		params.put("startTime", startTimeStr);
		params.put("duration", String.valueOf(duration));

		String url = Config.getLiveServerAddress() + "/api/v3/room/update/time";
		String res = postRequestDuobeiYun(url, params);
		
		return res;
	}
	
	public String uploadDocument(String filename, File file) {
		
		Map<String, String> params = Maps.newHashMap();
		String url = Config.getLiveServerAddress() + "/api/v3/documents/upload";
		params = prepareParameters(params);
		String res = WebUtils.multipartPost(url, params, filename, file);
		return res;
	}

	public String attatchDocument(String roomId, String documentId) {
		
		Map<String, String> params = Maps.newHashMap();
		params.put("roomId", roomId);
		params.put("documentId", documentId);
		
		String url = Config.getLiveServerAddress() + "/api/v3/room/attachDocument";
		params = prepareParameters(params);
		String res = WebUtils.post(url, params);
		return res;
	}
	public String deteleDocument(String roomId, String documentId) {
		
		Map<String, String> params = Maps.newHashMap();
		params.put("roomId", roomId);
		params.put("documentId", documentId);
		
		String url = Config.getLiveServerAddress() + "/api/v3/room/removeDocument";
		params = prepareParameters(params);
		String res = WebUtils.post(url, params);
		return res;
	}

	public String getDocumentStatus(String documentId) {
		
		Map<String, String> params = Maps.newHashMap();
		params.put("documentId", documentId);
		
		String url = Config.getLiveServerAddress() + "/api/v3/documents/status";
		params = prepareParameters(params);
		String res = WebUtils.post(url, params);
		
		return res;
	}
	
	public String generateRoomEnterUrl(String uid, String nickname, String roomId, String userRole) {

		Map<String, String> params = Maps.newHashMap();
		params.put("uid", uid);
		params.put("nickname", nickname);
		params.put("roomId", roomId);
		params.put("userRole", userRole);

		String timestamp = String.valueOf(new DateTime().plusMinutes(1).getMillis());

		params.put("partner", Config.getLivePartnerId());
		params.put("timestamp", timestamp);
		String sign = AppBulidSign.buildMysign(params, Config.getLiveAppKey());
		params.put("sign", sign);

		try {
			String serverAddress = Config.getLiveServerAddress();
			if(serverAddress.toLowerCase().startsWith("https")) {
				serverAddress = "http"+serverAddress.substring(5);
			}
			String url = serverAddress + "/api/v3/room/enter";
			return url + "?"+WebUtils.buildQueryString(params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private String postRequestDuobeiYun(String url, Map<String, String> params) {
		Map<String, String> map = prepareParameters(params);
		String res = WebUtils.post(url, map);
		return res;
	}

	private Map<String, String> prepareParameters(Map<String, String> params) {
		Map<String, String> map = Maps.newHashMap(params);
		String timestamp = String.valueOf(new DateTime().plusMinutes(1).getMillis());

		map.put("partner", Config.getLivePartnerId());
		map.put("timestamp", timestamp);
		String sign = AppBulidSign.buildMysign(map, Config.getLiveAppKey());
		map.put("sign", sign);
		return map;
	}
}
