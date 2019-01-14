package com.inspur.api.cluster;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inspur.util.jdbcutil;
import com.mysql.jdbc.Statement;

public class cluster_dao {
   public List<cluster_api> getdao() throws  Exception {
	   List<cluster_api> apiList=new ArrayList<cluster_api>();
	   jdbcutil  ju=new jdbcutil();	   
	   String sql ="select * from rest_api order by id asc";
	   Statement stmt = (Statement)ju.getconnection().createStatement();
	   ResultSet rs = stmt.executeQuery(sql);		                                                          
			while (rs.next()) {
				cluster_api  d=new cluster_api();
			       d.setId(rs.getInt("id"));
			       d.setResult( rs.getString("result"));				       
			       d.setDate(rs.getTimestamp("date"));			      
			       apiList.add(d);
			  }
	   return apiList;
   }
}
