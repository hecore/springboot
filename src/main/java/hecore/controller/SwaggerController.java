package hecore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hecore.entity.Th;
import hecore.service.ThService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;


@RestController
@RequestMapping("/rest")
@Api("SwaggerController api show")
public class SwaggerController {

	@Autowired
	private ThService thService;

	@ApiOperation("Api操作:获取信息")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "header", name = "id", dataType = "int", required = true, value = "id数值", defaultValue = "1111"),
		@ApiImplicitParam(paramType = "query", name = "name", dataType = "String", required = true, value = "名称", defaultValue = "hecore")
	})
	@ApiResponses({
		@ApiResponse(code=400,message="请求参数没填好"),
		@ApiResponse(code=404,message="请求路径没有或页面跳转路径不对")
	})
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public List<Th> getThs() {
		List<Th> ftl = thService.findThList();
		System.out.println(ftl);
		if (ftl.size() == 0) {
			return null;
		} else {
			return ftl;
		}
	}

}
