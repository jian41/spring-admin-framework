import React from 'react';
import {Editor as TinyMceEditor} from '@tinymce/tinymce-react';


/**
 * 富文本编辑器
 */
export class FieldEditor extends React.Component {
    render() {
        let uploadUrl = 'admin/sysFile/upload'
        let jsUrl = 'admin/tinymce/tinymce.min.js';
        const {value,onChange,height} = this.props

        return <>
            <TinyMceEditor
                initialValue={value}
                tinymceScriptSrc={jsUrl}
                init={{
                    min_height: 300,
                    language: 'zh_CN',
                    height: height,

                    // 上传图片
                    images_upload_url: uploadUrl,
                    images_upload_base_path: '',
                    promotion: false, // 不显示升级按钮（右上角）
                    cache_suffix: '?v=v7.7',
                }}
                onChange={e => {
                    if (onChange) {
                        onChange(e.target.getContent())
                    }
                }}
            />
        </>;
    }
}
