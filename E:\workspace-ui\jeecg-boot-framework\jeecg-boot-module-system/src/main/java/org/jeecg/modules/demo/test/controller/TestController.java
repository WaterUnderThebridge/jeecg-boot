package org.jeecg.modules.demo.test.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.test.entity.Test;
import org.jeecg.modules.demo.test.service.ITestService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: test
 * @Author: jeecg-boot
 * @Date:   2020-03-03
 * @Version: V1.0
 */
@Api(tags="test")
@RestController
@RequestMapping("/test/test")
@Slf4j
public class TestController extends JeecgController<Test, ITestService> {
	@Autowired
	private ITestService testService;
	
	/**
	 * 分页列表查询
	 *
	 * @param test
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "test-分页列表查询")
	@ApiOperation(value="test-分页列表查询", notes="test-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Test test,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Test> queryWrapper = QueryGenerator.initQueryWrapper(test, req.getParameterMap());
		Page<Test> page = new Page<Test>(pageNo, pageSize);
		IPage<Test> pageList = testService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param test
	 * @return
	 */
	@AutoLog(value = "test-添加")
	@ApiOperation(value="test-添加", notes="test-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Test test) {
		testService.save(test);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param test
	 * @return
	 */
	@AutoLog(value = "test-编辑")
	@ApiOperation(value="test-编辑", notes="test-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Test test) {
		testService.updateById(test);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test-通过id删除")
	@ApiOperation(value="test-通过id删除", notes="test-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "test-批量删除")
	@ApiOperation(value="test-批量删除", notes="test-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test-通过id查询")
	@ApiOperation(value="test-通过id查询", notes="test-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Test test = testService.getById(id);
		if(test==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(test);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param test
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Test test) {
        return super.exportXls(request, test, Test.class, "test");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Test.class);
    }

}
