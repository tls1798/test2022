package com.bit.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.model.EmpDto;
import com.bit.util.BitMy;

public class EmpListController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("application/json;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.println("{\"emp\":[");
		String sql="select * from emp";
		try(
				Connection conn=BitMy.getConnection();
				Statement stmt=conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				){
			while(rs.next()) {
				if(!rs.isFirst()) pw.print(',');
				
				EmpDto bean=new EmpDto();
				bean.setEmpno(rs.getInt("empno"));
				bean.setEname(rs.getString("ename"));
				bean.setSal(rs.getInt("sal"));
				pw.println("{");
				pw.println("\"empno\":"+bean.getEmpno());
				pw.println(",\"ename\":\""+bean.getEname()+"\"");
				pw.println(",\"sal\":"+bean.getSal());
				pw.println("}");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		pw.println("]}");
	}
	
}
