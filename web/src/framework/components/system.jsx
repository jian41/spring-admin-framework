import React from 'react';
import {FieldRemoteTreeSelect, FieldSelect} from './field';
import {FRemoteSelect, FRemoteSelectMultiple} from "../form-components";


export function FieldUserSelect(props) {
  return <FRemoteSelect url="admin/sysUser/options" {...props} />;
}
export function FieldUserSelectMultiple(props) {
  return <FRemoteSelectMultiple  url="admin/sysUser/options" {...props} />;
}

export function FieldUnitTreeSelect(props) {
  return <FieldRemoteTreeSelect url="admin/sysOrg/unitTree" {...props} />;
}

export function FieldDeptTreeSelect(props) {
  return <FieldRemoteTreeSelect url="admin/sysOrg/deptTree" {...props} />;
}

export function FieldOrgTreeSelect(props) {
  return <FieldRemoteTreeSelect url="admin/sysOrg/deptTree" {...props} />;
}

export function FieldOrgTreeMultipleSelect(props) {
  return <FieldSelect multiple={true} url="/sysOrg/deptTree" {...props} />;
}


