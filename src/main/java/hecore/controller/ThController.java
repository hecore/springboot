
/**
 * @author Administrator
 *
 */
package hecore.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import hecore.entity.Th;
import hecore.service.ThService;
//普通Controller  路由|字符串   RestController Json串等
@RequestMapping("/th")
//restController 自动将List 转成json 免去renderJson
@RestController
public class ThController{
	@Autowired
	ThService thservice;
	private final AtomicLong counter=new AtomicLong();
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Th> getThs() {
		System.out.println("程序写入");		
		List<Th> ftl = thservice.findThList();
		System.out.println(ftl);
		if(ftl.size()==0){
			return null;
		}else{
			return ftl;
		}
	}
	//, method = RequestMethod.GET 不指定所以所有的方法都可以映射到这里
	@RequestMapping(value = "/inshow")
	//参数和返回值描述
	public  @ResponseBody Th thin(@RequestParam(value="name",required=false, defaultValue="World") String name){
		return new Th(counter.incrementAndGet(), name);
		//这里默认是long类型
	}
}