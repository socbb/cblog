package cn.socbb.common.freemarker;

import cn.socbb.common.utils.DateUtils;
import cn.socbb.common.utils.StringUtils;
import freemarker.template.*;
import org.springframework.util.NumberUtils;

import java.util.Date;
import java.util.Map;

/**
 * freemarker 工具类
 * Created by cbb on 2018/7/25.
 */
public class Freemarkers {

    private static final String REQUIRED = "The '%s' paramter is required";

    private static final String NOT_MATCH = "The '%s' parameter not a %s";

    private static final String SPLIT = ",";

    public static String getString(TemplateModel model, String name, String def) throws TemplateModelException {
        String text;
        if (model == null) {
            text = def;
        } else if (model instanceof TemplateScalarModel) {
            TemplateScalarModel scalarModel = (TemplateScalarModel) model;
            text = scalarModel.getAsString();
        } else if ((model instanceof TemplateNumberModel)) {
            // 如果是数字，也转换成字符串
            TemplateNumberModel numberModel = (TemplateNumberModel) model;
            Number number = numberModel.getAsNumber();
            text = number.toString();
        } else {
            throw new TemplateModelException(String.format(NOT_MATCH, name, "string"));
        }
        return text;
    }

    public static String getString(TemplateModel model, String name) throws TemplateModelException {
        return getString(model, name, null);
    }

    public static String getString(Map<String, TemplateModel> params, String name, String def) throws TemplateModelException {
        TemplateModel model = params.get(name);
        return getString(model, name, def);
    }

    public static String getString(Map<String, TemplateModel> params, String name) throws TemplateModelException {
        TemplateModel model = params.get(name);
        return getString(model, name);
    }

    public static String[] getStrings(TemplateModel model, String name) throws TemplateModelException {
        String text = getString(model, name);
        return StringUtils.split(text, SPLIT);
    }

    public static String[] getStrings(Map<String, TemplateModel> params, String name) throws TemplateModelException {
        String text = StringUtils.full2Half(getString(params, name));
        return StringUtils.split(text, SPLIT);
    }


    public static Date getDate(TemplateModel model, String name) throws TemplateModelException {
        if (model == null) {
            return null;
        } else if (model instanceof TemplateDateModel) {
            TemplateDateModel dateModel = (TemplateDateModel) model;
            return dateModel.getAsDate();
        } else if (model instanceof TemplateScalarModel) {
            TemplateScalarModel scalarModel = (TemplateScalarModel) model;
            String text = scalarModel.getAsString();
            return DateUtils.parse(text);
        } else {
            throw new TemplateModelException(String.format(NOT_MATCH, name, "date"));
        }
    }

    public static Date getDate(Map<String, TemplateModel> params, String name) throws TemplateModelException {
        TemplateModel model = params.get(name);
        return getDate(model, name);
    }

    public static <T extends Number> T getNumber(TemplateModel model, String name, Class<T> targetClass)
            throws TemplateModelException {
        if (model == null) {
            return null;
        } else if (model instanceof TemplateNumberModel) {
            TemplateNumberModel numberModel = (TemplateNumberModel) model;
            Number number = numberModel.getAsNumber();
            return NumberUtils.convertNumberToTargetClass(number, targetClass);
        } else if (model instanceof TemplateScalarModel) {
            TemplateScalarModel scalarModel = (TemplateScalarModel) model;
            String text = scalarModel.getAsString();
            if (StringUtils.isNotBlank(text)) {
                try {
                    return NumberUtils.parseNumber(text, targetClass);
                } catch (NumberFormatException e) {
                    throw new TemplateModelException(String.format(NOT_MATCH, name, "number"), e);
                }
            } else {
                return null;
            }
        } else {
            throw new TemplateModelException(String.format(NOT_MATCH, name, "number"));
        }
    }

    public static <T extends Number> T getNumber(Map<String, TemplateModel> params, String name, Class<T> targetClass)
            throws TemplateModelException {
        TemplateModel model = params.get(name);
        return getNumber(model, name, targetClass);
    }

    public static Long getLong(TemplateModel model, String name) throws TemplateModelException {
        return getNumber(model, name, Long.class);
    }

    public static Long getLong(TemplateModel model, String name, Long def) throws TemplateModelException {
        Long result = getNumber(model, name, Long.class);
        return result != null ? result : def;
    }

    public static Long getLong(Map<String, TemplateModel> params, String name) throws TemplateModelException {
        return getNumber(params, name, Long.class);
    }

    public static Long getLong(Map<String, TemplateModel> params, String name, Long def) throws TemplateModelException {
        Long result = getNumber(params, name, Long.class);
        return result != null ? result : def;
    }

    public static Long[] getLongs(TemplateModel model, String name) throws TemplateModelException {
        String text = getString(model, name);

        if (text == null) {
            return null;
        } else if (StringUtils.isBlank(text)) {
            return new Long[0];
        }
        String[] stringArray = StringUtils.split(text, SPLIT);
        int length = stringArray.length;
        Long[] numberArray = new Long[length];
        try {
            for (int i = 0; i < length; i++) {
                numberArray[i] = Long.valueOf(stringArray[i]);
            }
            return numberArray;
        } catch (NumberFormatException e) {
            throw new TemplateModelException(String.format(NOT_MATCH, name, "long array"));
        }
    }

    public static Long[] getLongs(Map<String, TemplateModel> params, String name) throws TemplateModelException {
        TemplateModel model = params.get(name);
        return getLongs(model, name);
    }

    public static Integer getInteger(TemplateModel model, String name) throws TemplateModelException {
        return getNumber(model, name, Integer.class);
    }

    public static Integer getInteger(TemplateModel model, String name, Integer def) throws TemplateModelException {
        Integer result = getNumber(model, name, Integer.class);
        return result != null ? result : def;
    }

    public static Integer getInteger(Map<String, TemplateModel> params, String name) throws TemplateModelException {
        return getNumber(params, name, Integer.class);
    }

    public static Integer getInteger(Map<String, TemplateModel> params, String name, Integer def) throws TemplateModelException {
        Integer result = getNumber(params, name, Integer.class);
        return result != null ? result : def;
    }

    public static Integer[] getIntegers(TemplateModel model, String name) throws TemplateModelException {
        String text = getString(model, name);
        if (text == null) {
            return null;
        } else if (StringUtils.isBlank(text)) {
            return new Integer[0];
        }
        String[] stringArray = StringUtils.split(text, SPLIT);
        int length = stringArray.length;
        Integer[] numberArray = new Integer[length];
        try {
            for (int i = 0; i < length; i++) {
                numberArray[i] = Integer.valueOf(stringArray[i]);
            }
            return numberArray;
        } catch (NumberFormatException e) {
            throw new TemplateModelException(String.format(NOT_MATCH, name, "integer array"));
        }
    }

    public static Integer[] getIntegers(Map<String, TemplateModel> params, String name) throws TemplateModelException {
        TemplateModel model = params.get(name);
        return getIntegers(model, name);
    }

    public static Boolean getBoolean(TemplateModel model, String name, Boolean def) throws TemplateModelException {
        Boolean result;
        if (model == null) {
            result = null;
        } else if (model instanceof TemplateBooleanModel) {
            TemplateBooleanModel booleanModel = (TemplateBooleanModel) model;
            result = booleanModel.getAsBoolean();
        } else if (model instanceof TemplateScalarModel) {
            TemplateScalarModel scalarModel = (TemplateScalarModel) model;
            String text = scalarModel.getAsString();
            if (StringUtils.isNotBlank(text)) {
                result = Boolean.valueOf(text);
            } else {
                result = null;
            }
        } else {
            throw new TemplateModelException(String.format(NOT_MATCH, name, "boolean"));
        }
        return result != null ? result : def;
    }

    public static Boolean getBoolean(TemplateModel model, String name) throws TemplateModelException {
        return getBoolean(model, name, null);
    }

    public static Boolean getBoolean(Map<String, TemplateModel> params, String name) throws TemplateModelException {
        TemplateModel model = params.get(name);
        return getBoolean(model, name);
    }

    public static Boolean getBoolean(Map<String, TemplateModel> params, String name, Boolean def) throws TemplateModelException {
        TemplateModel model = params.get(name);
        return getBoolean(model, name, def);
    }
}
