package com.inspur.api.cluster;

import java.util.List;

import com.inspur.util.jdbcutil;

public class cluster_service {
   public List<cluster_api> findall() {
	   cluster_dao d=new cluster_dao();
	   try {
		return d.getdao();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}finally{
		jdbcutil.closeConnection();
	}
   }
}
