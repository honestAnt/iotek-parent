package com.iotekclass.model.course;

import java.io.Serializable;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.iotekclass.cms.model.IdExtEntity;

/** 
 * @ClassName: Course   
 * @Description： 实体类 
 * @Author：zhangwenlei
 * @Date：2014年10月15日 下午1:25:36
 * @version
 */
public class Course extends IdExtEntity implements Serializable {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -5017017669136874895L;

	
	

	public static final String tableName = "tb_course";
	
	/**
	 * 课程编码
	 */
	private String courseCode;
	/**
	 * 课程名称
	 */
	private String courseName;
	/**
	 * 课程简称
	 */
	private String courseLabel;
	/**
	 * 课程描述
	 */
	private String courseDesc;
	/**
	 * 课程图片地址
	 */
	private String pictureUrl;
	/**
	 * 学时数
	 */
	private int courseHours;
	/**
	 * 课时数
	 */
	private int courseCount;
	/**
	 * 教学目标
	 */
	private String objective;
	/**
	 * 任课老师ID
	 */
	private String teacherId;
	/**
	 * 任课老师姓名
	 */
	private String teacherName;
	/**
	 * 分类
	 */
	private int categoryId;
	/**
	 * 状态
	 */
	private int status;
	
	private int isMain;
	
	private int mainCourseId;
	
	public Course() {

	}

	public Course(int id) {
		super();
		this.id = id;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseLabel() {
		return courseLabel;
	}

	public void setCourseLabel(String courseLabel) {
		this.courseLabel = courseLabel;
	}

	public String getCourseDesc() {
		return courseDesc;
	}

	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public int getCourseHours() {
		return courseHours;
	}

	public void setCourseHours(int courseHours) {
		this.courseHours = courseHours;
	}

	public int getCourseCount() {
		return courseCount;
	}

	public void setCourseCount(int courseCount) {
		this.courseCount = courseCount;
	}

	public String getObjective() {
		return objective;
	}

	public void setObjective(String objective) {
		this.objective = objective;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}



	public static String getTablename() {
		return tableName;
	}

	/**
	 * @Description: TODO
	 * @return
	 * @throws
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectionToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/**
	 * @return the isMain
	 */
	public int getIsMain() {
		return isMain;
	}

	/**
	 * @param isMain the isMain to set
	 */
	public void setIsMain(int isMain) {
		this.isMain = isMain;
	}

	/**
	 * @return the mainCourseId
	 */
	public int getMainCourseId() {
		return mainCourseId;
	}

	/**
	 * @param mainCourseId the mainCourseId to set
	 */
	public void setMainCourseId(int mainCourseId) {
		this.mainCourseId = mainCourseId;
	}

}
