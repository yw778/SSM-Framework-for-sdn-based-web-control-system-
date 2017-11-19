package com.main.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.main.model.LRoutab;
import com.main.service.LRouTabService;
import com.main.service.ReadFileService;

@RestController
@RequestMapping("/routale")
public class RoutableController {
	@Resource
	LRouTabService lRouTabService;
	@Resource
	ReadFileService readFileService;
	boolean firstEn=true;
	@RequestMapping("/newTable.do")
	public void configPort( String mac,String ip,String port,
			HttpServletResponse response){
		
		//System.out.println(mac+ip+port);
		//String command="dpid"+"\n"+
		//mac:目的网络地址
		//ip:子网掩码
		//port:出端口
		LRoutab lRoutab=new LRoutab();
		lRoutab.setDest(mac);
		
		lRoutab.setInter(port);
		if(firstEn){
		lRoutab.setNexthop("192.168.1.4");		
		lRoutab.setCost(2);
		firstEn=false;
		}else{
		lRoutab.setNexthop("192.168.0.222");		
		lRoutab.setCost(1);	
		}
		
		lRoutab.setSwitype(1);
		lRoutab.setPre(0);
		
		lRoutab.setRby2(ip);
		if(lRouTabService.selectByPrimaryKey(lRoutab.getDest())==null)
		lRouTabService.insertSelective(lRoutab);
		else lRouTabService.updateByPrimaryKeySelective(lRoutab);
	/*	LRoutab lRoutab1=new LRoutab();
		lRoutab1.setDest("192.168.0.9");
		lRoutab1.setCost(2);
		lRoutab1.setInter("G0/2");
		lRoutab1.setNexthop("192.168.0.0");
		lRoutab1.setPre(0);
		lRoutab1.setSwitype(1);
		lRoutab1.setRby2("24");
		lRouTabService.insertSelective(lRoutab1);
		
		LRoutab lRoutab2=new LRoutab();
		lRoutab1.setDest("92.168.0.1");
		lRoutab1.setCost(1);
		lRoutab1.setInter("G0/1");
		lRoutab1.setNexthop("192.168.0.1");
		lRoutab1.setPre(0);
		lRoutab1.setSwitype(1);
		lRoutab1.setRby2("24");
		lRouTabService.insertSelective(lRoutab2);*/
		
		
		try {
			
			
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			PrintWriter out = response.getWriter();
			out.println("已完成配置");
		    out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/*
	@RequestMapping("/newTable.do")
	public @ResponseBody List<Integer> getSpeed(){
		List<Integer> speeds =new ArrayList<Integer>();
		speeds=readFileService.read();
		
		return speeds;
		
		
	}*/
	
	

}
