package org.jeecg.modules.test.test.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.test.test.entity.testDemo;
import org.jeecg.modules.test.test.service.ItestDemoService;
import java.util.Date;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

 /**
 * @Description: test_demo
 * @Author: jeecg-boot
 * @Date:   2020-03-03
 * @Version: V1.0
 */
@Slf4j
@Api(tags="test_demo")
@RestController
@RequestMapping("/test/testDemo")
public class testDemoController extends JeecgController<testDemo, ItestDemoService> {
	@Autowired
	private ItestDemoService testDemoService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testDemo
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "test_demo-分页列表查询")
	@ApiOperation(value="test_demo-分页列表查询", notes="test_demo-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(testDemo testDemo,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<testDemo> queryWrapper = QueryGenerator.initQueryWrapper(testDemo, req.getParameterMap());
		Page<testDemo> page = new Page<testDemo>(pageNo, pageSize);
		IPage<testDemo> pageList = testDemoService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 * 添加
	 *
	 * @param testDemo
	 * @return
	 */
	@AutoLog(value = "test_demo-添加")
	@ApiOperation(value="test_demo-添加", notes="test_demo-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody testDemo testDemo) {
		testDemoService.save(testDemo);
		return Result.ok("添加成功！");
	}
	
	/**
	 * 编辑
	 *
	 * @param testDemo
	 * @return
	 */
	@AutoLog(value = "test_demo-编辑")
	@ApiOperation(value="test_demo-编辑", notes="test_demo-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody testDemo testDemo) {
		testDemoService.updateById(testDemo);
		return Result.ok("编辑成功!");
	}
	
	/**
	 * 通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test_demo-通过id删除")
	@ApiOperation(value="test_demo-通过id删除", notes="test_demo-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testDemoService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 * 批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "test_demo-批量删除")
	@ApiOperation(value="test_demo-批量删除", notes="test_demo-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testDemoService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功！");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "test_demo-通过id查询")
	@ApiOperation(value="test_demo-通过id查询", notes="test_demo-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		testDemo testDemo = testDemoService.getById(id);
		return Result.ok(testDemo);
	}

  /**
   * 导出excel
   *
   * @param request
   * @param testDemo
   */
  @RequestMapping(value = "/exportXls")
  public ModelAndView exportXls(HttpServletRequest request, testDemo testDemo) {
      return super.exportXls(request, testDemo, testDemo.class, "test_demo");
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
      return super.importExcel(request, response, testDemo.class);
  }

}
