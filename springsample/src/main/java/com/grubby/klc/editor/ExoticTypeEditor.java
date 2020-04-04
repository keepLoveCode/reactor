package com.grubby.klc.editor;

import java.beans.PropertyEditorSupport;

/**
 * editor已经过时了，有功能更强大的converter代替
 * 如果需要使用string转其他类型 首选editor ，简单明确
 */
public class ExoticTypeEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        setValue(new ExoticType(text.toLowerCase()));
    }
}
