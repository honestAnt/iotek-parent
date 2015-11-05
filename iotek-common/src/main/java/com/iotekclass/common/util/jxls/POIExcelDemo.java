package com.iotekclass.common.util.jxls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class POIExcelDemo {
	public static class VM {
		private int id;
		private String name;
		private String scale;
		private double price;
		private Date created;

		public VM() {
			super();
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getScale() {
			return scale;
		}

		public void setScale(String scale) {
			this.scale = scale;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}

		public Date getCreated() {
			return created;
		}

		public void setCreated(Date created) {
			this.created = created;
		}
	}

	public static class ItemCount {
		private String name;
		private List<Integer> counts;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Integer> getCounts() {
			return counts;
		}

		public void setCounts(List<Integer> counts) {
			this.counts = counts;
		}

	}

	public static void main(String[] args) throws Exception {
		// testExcelUtils();
		// testExcelUtilsCpx();
	}

	public static void testExcelUtils() throws Exception {
		List<VM> vms = new ArrayList<VM>();
		for (int i = 0; i < 21; ++i) {
			VM vma = new VM();
			vma.setId(i);
			vma.setName("我的CENTOS" + i);
			vma.setPrice(103);
			vma.setScale("2CPU, 2G MEM, 2T DISK");
			vma.setCreated(new Date());
			vms.add(vma);
		}
		new ExcelGenerator().generateExcelByTemplate("E:\\tmp\\ex-sample.xlsx", "E:\\tmp\\template-simple.xlsx", vms,
				"vms", 10);
	}

	public static void testExcelUtilsCpx() throws Exception {
		List<String> dates = new ArrayList<String>();
		int maxDates = 8;
		for (int i = 0; i < maxDates; ++i) {
			dates.add("2013-08-11");
		}
		List<ItemCount> itemCounts = new ArrayList<ItemCount>();
		for (int i = 0; i < 82; ++i) {
			ItemCount ic = new ItemCount();
			List<Integer> counts = new ArrayList<Integer>();
			for (int j = 0; j < maxDates; ++j) {
				counts.add(j);
			}
			ic.setCounts(counts);
			ic.setName("item" + i);
			itemCounts.add(ic);
		}
		new ExcelGenerator().generateExcelByTemplate("E:\\tmp\\ex-cpx.xlsx", "E:\\tmp\\template-matrix.xlsx", dates,
				"dates", itemCounts, "items", 12);
	}
}
