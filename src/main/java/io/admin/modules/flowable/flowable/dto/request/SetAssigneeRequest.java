package io.admin.modules.flowable.flowable.dto.request;

import lombok.Data;

@Data
public class SetAssigneeRequest {
    String taskId;
    String assignee;
}
