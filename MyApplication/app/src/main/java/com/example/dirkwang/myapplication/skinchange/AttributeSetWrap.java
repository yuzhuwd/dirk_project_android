package com.example.dirkwang.myapplication.skinchange;

import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseIntArray;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dirkwang on 2017/5/10.
 */
class AttributeSetWrap implements AttributeSet {
    private AttributeSet core;

    AttributeSetWrap(AttributeSet core) {
        this.core = core;
    }

    public void setCore(AttributeSet core) {
        this.core = core;
    }

    private int attrCount;

    @Override
    public int getAttributeCount() {
        if (core != null) {
            attrCount = core.getAttributeCount();
            return attrCount;
        }
        return attrCount;
    }

    private SparseArray<String> attrNameMap = new SparseArray<>();

    @Override
    public String getAttributeName(int index) {
        if (core != null) {
            String name = core.getAttributeName(index);
            attrNameMap.put(index, name);
            return name;
        }
        return attrNameMap.get(index, "");
    }

    private SparseArray<String> attrValueMap = new SparseArray<>();

    @Override
    public String getAttributeValue(int index) {
        if (core != null) {
            String name = core.getAttributeValue(index);
            attrValueMap.put(index, name);
            return name;
        }
        return attrValueMap.get(index, "");
    }

    private Map<String, String> attrValueMap2 = new HashMap<>();

    @Override
    public String getAttributeValue(String namespace, String name) {
        String key = namespace + "|" + name;
        if (core != null) {
            String result = core.getAttributeValue(namespace, name);
            attrValueMap2.put(key, result);
            return result;
        }
        return attrValueMap2.get(key);
    }

    private String posDesc;

    @Override
    public String getPositionDescription() {
        if (core != null) {
            posDesc = core.getPositionDescription();
            return posDesc;
        }
        return posDesc;
    }

    private SparseIntArray attrNameResMap = new SparseIntArray();

    @Override
    public int getAttributeNameResource(int index) {
        if (core != null) {
            int result = core.getAttributeNameResource(index);
            attrNameResMap.put(index, result);
            return result;
        }
        return attrNameResMap.get(index, 0);
    }

    private Map<String, Integer> attrListValueMap = new HashMap<>();

    @Override
    public int getAttributeListValue(String namespace, String attribute, String[] options, int defaultValue) {
        String key = namespace + "|" + attribute + "|[" + TextUtils.join(",", options) + "]|" + defaultValue;
        if (core != null) {
            int result = core.getAttributeListValue(namespace, attribute, options, defaultValue);
            attrListValueMap.put(key, result);
            return result;
        }
        return attrListValueMap.get(key);
    }

    private Map<String, Boolean> attrBoolValueMap = new HashMap<>();

    @Override
    public boolean getAttributeBooleanValue(String namespace, String attribute, boolean defaultValue) {
        String key = namespace + "|" + attribute + "|" + defaultValue;
        if (core != null) {
            boolean result = core.getAttributeBooleanValue(namespace, attribute, defaultValue);
            attrBoolValueMap.put(key, result);
            return result;
        }
        return attrBoolValueMap.get(key);
    }

    private Map<String, Integer> attrResValueMap = new HashMap<>();

    @Override
    public int getAttributeResourceValue(String namespace, String attribute, int defaultValue) {
        String key = namespace + "|" + attribute + "|" + defaultValue;
        if (core != null) {
            int result = core.getAttributeResourceValue(namespace, attribute, defaultValue);
            attrResValueMap.put(key, result);
            return result;
        }
        return attrResValueMap.get(key);
    }

    private Map<String, Integer> attrIntValueMap = new HashMap<>();

    @Override
    public int getAttributeIntValue(String namespace, String attribute, int defaultValue) {
        String key = namespace + "|" + attribute + "|" + defaultValue;
        if (core != null) {
            int result = core.getAttributeIntValue(namespace, attribute, defaultValue);
            attrIntValueMap.put(key, result);
            return result;
        }
        return attrIntValueMap.get(key);
    }

    private Map<String, Integer> attrUnsignedIntValueMap = new HashMap<>();

    @Override
    public int getAttributeUnsignedIntValue(String namespace, String attribute, int defaultValue) {
        String key = namespace + "|" + attribute + "|" + defaultValue;
        if (core != null) {
            int result = core.getAttributeUnsignedIntValue(namespace, attribute, defaultValue);
            attrUnsignedIntValueMap.put(key, result);
            return result;
        }
        return attrUnsignedIntValueMap.get(key);
    }

    private Map<String, Float> attrFloatValueMap = new HashMap<>();

    @Override
    public float getAttributeFloatValue(String namespace, String attribute, float defaultValue) {
        String key = namespace + "|" + attribute + "|" + defaultValue;
        if (core != null) {
            float result = core.getAttributeFloatValue(namespace, attribute, defaultValue);
            attrFloatValueMap.put(key, result);
            return result;
        }
        return attrFloatValueMap.get(key);
    }

    private Map<String, Integer> attrListValueMap2 = new HashMap<>();

    @Override
    public int getAttributeListValue(int index, String[] options, int defaultValue) {
        String key = index + "|[" + TextUtils.join(",", options) + "]|" + defaultValue;
        if (core != null) {
            int result = core.getAttributeListValue(index, options, defaultValue);
            attrListValueMap2.put(key, result);
            return result;
        }
        return attrListValueMap2.get(key);
    }

    private Map<String, Boolean> attrBoolValueMap2 = new HashMap<>();

    @Override
    public boolean getAttributeBooleanValue(int index, boolean defaultValue) {
        String key = index + "|" + defaultValue;
        if (core != null) {
            boolean result = core.getAttributeBooleanValue(index, defaultValue);
            attrBoolValueMap2.put(key, result);
            return result;
        }
        return attrBoolValueMap2.get(key);
    }

    private Map<String, Integer> attrResValueMap2 = new HashMap<>();

    @Override
    public int getAttributeResourceValue(int index, int defaultValue) {
        String key = index + "|" + defaultValue;
        if (core != null) {
            int result = core.getAttributeResourceValue(index, defaultValue);
            attrResValueMap2.put(key, result);
            return result;
        }
        return attrResValueMap2.get(key);
    }

    private Map<String, Integer> attrIntValueMap2 = new HashMap<>();

    @Override
    public int getAttributeIntValue(int index, int defaultValue) {
        String key = index + "|" + defaultValue;
        if (core != null) {
            int result = core.getAttributeIntValue(index, defaultValue);
            attrIntValueMap2.put(key, result);
            return result;
        }
        return attrIntValueMap2.get(key);
    }

    private Map<String, Integer> attrUnsignedIntValueMap2 = new HashMap<>();

    @Override
    public int getAttributeUnsignedIntValue(int index, int defaultValue) {
        String key = index + "|" + defaultValue;
        if (core != null) {
            int result = core.getAttributeUnsignedIntValue(index, defaultValue);
            attrUnsignedIntValueMap2.put(key, result);
            return result;
        }
        return attrUnsignedIntValueMap2.get(key);
    }

    private Map<String, Float> attrFloatValueMap2 = new HashMap<>();

    @Override
    public float getAttributeFloatValue(int index, float defaultValue) {
        String key = index + "|" + defaultValue;
        if (core != null) {
            float result = core.getAttributeFloatValue(index, defaultValue);
            attrFloatValueMap2.put(key, result);
            return result;
        }
        return attrFloatValueMap2.get(key);
    }

    private String idAttr;

    @Override
    public String getIdAttribute() {
        if (core != null) {
            idAttr = core.getIdAttribute();
            return idAttr;
        }
        return idAttr;
    }

    private String classAttr;

    @Override
    public String getClassAttribute() {
        if (core != null) {
            classAttr = core.getClassAttribute();
            return classAttr;
        }
        return classAttr;
    }

    private SparseIntArray idAttrResValue = new SparseIntArray();

    @Override
    public int getIdAttributeResourceValue(int defaultValue) {
        if (core != null) {
            int result = core.getIdAttributeResourceValue(defaultValue);
            idAttrResValue.put(defaultValue, result);
            return result;
        }
        return idAttrResValue.get(defaultValue, 0);
    }

    private int styleAttr;

    @Override
    public int getStyleAttribute() {
        if (core != null) {
            styleAttr = core.getStyleAttribute();
            return styleAttr;
        }
        return styleAttr;
    }
}
