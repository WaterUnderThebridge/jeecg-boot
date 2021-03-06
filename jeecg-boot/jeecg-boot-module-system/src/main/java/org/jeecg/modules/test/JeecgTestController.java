package org.jeecg.modules.test;


import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/jeecgTest")
@Slf4j
public class JeecgTestController {

    /**
     * hello world
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/hello")
    public Result<String> hello() {
        Result<String> result = new Result<String>();
        result.setResult("Hello Tony wu world!");
        result.setSuccess(true);
        return result;
    }
}
