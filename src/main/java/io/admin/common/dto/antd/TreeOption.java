package io.admin.common.dto.antd;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.admin.common.utils.tree.TreeNode;
import io.admin.common.utils.tree.TreeManager;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * antd 树状选择对象
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TreeOption implements TreeNode<TreeOption> {
    String title;
    String key;

    String parentKey;

    List<TreeOption> children = new ArrayList<>();


    Boolean selectable ;
    Boolean checkable ;
    Boolean disabled ;

    Boolean isLeaf;


    Object data;


    public Object getValue() {
        return key;
    }

    /**
     * 同 key，为了方便
     *
     *
     */
    @Override
    public String getId() {
        return key;
    }


    /**
     * 同 parentKey
     *
     *
     */
    @Override
    public String getPid() {
        return parentKey;
    }


    @Override
    public void setId(String id) {
        this.setKey(id);
    }

    @Override
    public void setPid(String pid) {
        this.setParentKey(pid);
    }

    public TreeOption(String title, String key, String parentKey) {
        this.title = title;
        this.key = key;
        this.parentKey = parentKey;
    }


    public static List<TreeOption> convertTree(List<TreeOption> list) {
        TreeManager<TreeOption> tm = TreeManager.of(list);
        return tm.getTree();
    }


}
