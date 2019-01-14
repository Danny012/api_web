package com.inspur.api.hbase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.inspur.util.jdbcutil;
import com.mysql.jdbc.Statement;

public class hbase_dao {
   public List<hbase_api> getdao() throws  Exception {
	   List<hbase_api> apiList=new ArrayList<hbase_api>();
	   jdbcutil  ju=new jdbcutil();	   
	   String sql ="select * from hbase_api order by id asc";
	   Statement stmt = (Statement)ju.getconnection().createStatement();
	   ResultSet rs = stmt.executeQuery(sql);		                                                          
			while (rs.next()) {
				hbase_api  d=new hbase_api();
			       d.setId(rs.getInt("id"));
			       d.setResult( rs.getString("result"));				       
			       d.setDate(rs.getTimestamp("date"));			      
			       apiList.add(d);
			  }
	   return apiList;
   }
}
