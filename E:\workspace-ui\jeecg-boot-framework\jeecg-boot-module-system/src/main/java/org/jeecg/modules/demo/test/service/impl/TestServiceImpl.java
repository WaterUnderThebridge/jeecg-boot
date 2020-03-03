package org.jeecg.modules.demo.test.service.impl;

import org.jeecg.modules.demo.test.entity.Test;
import org.jeecg.modules.demo.test.mapper.TestMapper;
import org.jeecg.modules.demo.test.service.ITestService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: test
 * @Author: jeecg-boot
 * @Date:   2020-03-03
 * @Version: V1.0
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
