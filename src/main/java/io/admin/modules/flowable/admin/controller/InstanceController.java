package io.admin.modules.flowable.admin.controller;


import io.admin.common.dto.AjaxResult;
import io.admin.modules.flowable.admin.service.MyTaskService;
import io.admin.modules.flowable.core.dto.response.TaskResponse;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskQuery;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/flowable/instance")
public class InstanceController {


    @Resource
    MyTaskService myTaskService;

    @Resource
    HistoryService historyService;



    @GetMapping("img")
    public void instanceImg(String businessKey, String id, HttpServletResponse response) throws IOException {
        if (StringUtils.isNotEmpty(businessKey)) {
            HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
            query.processInstanceBusinessKey(businessKey);
            query.notDeleted().orderByProcessInstanceStartTime()
                    .desc();
            List<HistoricProcessInstance> list = query
                    .listPage(0, 1);
            Assert.state(!list.isEmpty(), "暂无流程信息");
            HistoricProcessInstance instance = list.get(0);

            id = instance.getId();
        }

        BufferedImage image = myTaskService.drawImage(id);
        ImageIO.write(image, "jpg", response.getOutputStream());
    }




}
