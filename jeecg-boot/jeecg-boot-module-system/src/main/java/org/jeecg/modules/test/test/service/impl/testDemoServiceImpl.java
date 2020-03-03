package org.jeecg.modules.test.test.service.impl;

import org.jeecg.modules.test.test.entity.testDemo;
import org.jeecg.modules.test.test.mapper.testDemoMapper;
import org.jeecg.modules.test.test.service.ItestDemoService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: test_demo
 * @Author: jeecg-boot
 * @Date:   2020-03-03
 * @Version: V1.0
 */
@Service
public class testDemoServiceImpl extends ServiceImpl<testDemoMapper, testDemo> implements ItestDemoService {

}
