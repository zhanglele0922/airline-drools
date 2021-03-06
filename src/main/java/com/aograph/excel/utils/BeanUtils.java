package com.aograph.excel.utils;

import com.aograph.excel.annotation.Dictionary;
import com.aograph.excel.annotation.ExlColumn;
import com.aograph.excel.annotation.ExlSheet;
import com.aograph.excel.annotation.ExlTransient;
import com.aograph.excel.bean.ExcelResult;
import com.aograph.excel.bean.ExcelRow;
import com.aograph.excel.bean.ExcelSheet;
import com.aograph.excel.converter.DictionaryConverter;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Bean工具类
 *
 * @author LiuJunGuang
 */
public class BeanUtils {
	private static Logger log = LoggerFactory.getLogger(BeanUtils.class);
	private static FieldComparator fieldComparator = new FieldComparator();
	
	private BeanUtils() {
		super();
	}

	/**
	 * 获取字典中指定的类型值,如果字典中没有指定的值，则返回value
	 *
	 * @author LiuJunGuang
	 * @param rowField Row 注解
	 * @param value 属性值
	 * @param dic2value 字典值转换成对象值，true - 根据Dictionary 的value 查找，返回name，false - 根据Dictionary 的name查找，返回 value
	 * @return String 字典值，或者字典名称
	 */
	private static String getDictionaryValue(ExlColumn rowField, String value, boolean dic2value) {
		if (rowField == null) {
			return value;
		}
		Dictionary[] dictionaries = rowField.dictionaries();
		if (dictionaries == null || dictionaries.length == 0 || value == null || "".equals(value)) {
			return value;
		}

		for (Dictionary dictionary : dictionaries) {
			if (dic2value) {
				if (value.equals(dictionary.value())) {
					return dictionary.name();
				}
			} else {
				if (value.equals(dictionary.name())) {
					return dictionary.value();
				}
			}
		}

		return value;
	}

	/**
	 * 将excelResult 转换成javaBean
	 *
	 * @author LiuJunGuang
	 * @param excelResult ExcelResult结果集
	 * @param clazz clazz 需要生成的JavaBean对象
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return 如果列表为空，或者为null，则直接返回null,如果发生异常则直接返回null
	 */
	public static <T> List<T> toBean(ExcelResult excelResult, Class<T> clazz, DictionaryConverter... converters) {
		ExlSheet sheet = clazz.getAnnotation(ExlSheet.class);

		if (sheet != null) {
			String sheetName = sheet.value();
			if (sheetName != null && !"".equals(sheetName)) {
				return toBean(excelResult.toMap(sheetName), clazz, true, converters);
			}

			int sheetIndex = sheet.index();
			if (sheetIndex >= 0) {
				return toBean(excelResult.toMap(sheetIndex), clazz, true, converters);
			}
		}
		return toBean(excelResult.toMap(), clazz, true, converters);
	}

	/**
	 * 将excelResult 转换成javaBean
	 *
	 * @author LiuJunGuang
	 * @param excelResult ExcelResult结果集
	 * @param clazz clazz 需要生成的JavaBean对象
	 * @param sheetIndex sheetIndex 索引号
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return 如果列表为空，或者为null，则直接返回null,如果发生异常则直接返回null
	 */
	public static <T> List<T> toBean(ExcelResult excelResult, Class<T> clazz, int sheetIndex,
			DictionaryConverter... converters) {
		return toBean(excelResult.toMap(sheetIndex), clazz, true, converters);
	}

	/**
	 * 将excelResult 转换成javaBean
	 *
	 * @author LiuJunGuang
	 * @param excelResult ExcelResult结果集
	 * @param clazz clazz 需要生成的JavaBean对象
	 * @param sheetName ExcelSheet 名子
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return 如果列表为空，或者为null，则直接返回null,如果发生异常则直接返回null
	 */
	public static <T> List<T> toBean(ExcelResult excelResult, Class<T> clazz, String sheetName,
			DictionaryConverter... converters) {
		return toBean(excelResult.toMap(sheetName), clazz, true, converters);
	}

	/**
	 * 将ListMap 转换成javaBean
	 *
	 * @author LiuJunGuang
	 * @param list 需要转换成Bean对象的List数据
	 * @param clazz clazz 需要生成的JavaBean对象
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return 如果列表为空，或者为null，则直接返回null,如果发生异常则直接返回null
	 */
	public static <T> List<T> toBean(List<Map<String, String>> list, Class<T> clazz,
			DictionaryConverter... converters) {
		return toBean(list, clazz, true, converters);
	}

	/**
	 * 将ListMap 转换成javaBean
	 *
	 * @author LiuJunGuang
	 * @param list 需要转换成Bean对象的List数据
	 * @param clazz 需要生成的JavaBean对象
	 * @param ignoreException true 忽略异常信息，false 抛出异常信息
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return 如果列表为空，或者为null，则直接返回null
	 */
	public static <T> List<T> toBean(List<Map<String, String>> list, Class<T> clazz, boolean ignoreException,
			DictionaryConverter... converters) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		for (Map<String, String> map : list) {
			T bean = toBean(map, clazz, ignoreException, converters);
			if (bean != null) {
				result.add(bean);
			}
		}
		return result;
	}

	/**
	 * 将结果对象转换成Excel结果集对象,并忽略异常信息
	 *
	 * @author LiuJunGuang
	 * @param list 对象列表
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return ExcelResult Excel结果对象
	 */
	public static <T> ExcelResult toResult(List<T> list, DictionaryConverter... converters) {
		return toResult(list, true, converters);
	}

	/**
	 * 将结果对象转换成Excel结果集对象
	 *
	 * @author LiuJunGuang
	 * @param list 对象列表
	 * @param ignoreException true 忽略异常信息，false 抛出异常信息
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return Excel结果对象
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> ExcelResult toResult(List<T> list, boolean ignoreException, DictionaryConverter... converters) {
		if (list == null || list.isEmpty()) {
			return null;
		}
		Class clazz = list.get(0).getClass();
		String sheetName = clazz.getSimpleName();
		int sheetIndex = 0;
		ExlSheet sheet = (ExlSheet) clazz.getAnnotation(ExlSheet.class);
		// 设置sheet名称
		if (sheet != null) {
			sheetName = sheet.value();
			if (sheetName == null || "".equals(sheetName)) {
				sheetName = clazz.getSimpleName();
			}
			sheetIndex = sheet.index();
			if (sheetIndex < 0) {
				sheetIndex = 0;
			}
		}

		// 设置结果信息
		ExcelSheet exlSheet = new ExcelSheet(sheetName, sheetIndex);
		exlSheet.addHeadRow(getHeads(clazz));
		for (int i = 0; i < list.size(); i++) {
			T obj = list.get(i);
			Map<String, String> map = toMap(obj, ignoreException, converters);
			if (map == null) {
				continue;
			}

			ExcelRow row = new ExcelRow(i + 1);
			row.addCell(map.values());
			exlSheet.addRow(row);
		}
		return new ExcelResult(exlSheet);
	}

	// 获取Excel标题信息
	protected static List<String> getHeads(Class<?> clazz) {
		Field[] fields = ReflectionUtils.getFields(clazz);// 根据Class对象获得属性 私有的也可以获得
		Arrays.sort(fields, fieldComparator);// 属性按照index 排序
		List<String> heads = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			try {
				String fieldName = field.getName();
				// 属性没有读方法，则直接忽略
				if (!ReflectionUtils.hasReadMethod(clazz, fieldName)) {
					continue;
				}

				// 需要忽略的属性
				ExlTransient trans = field.getAnnotation(ExlTransient.class);
				if (trans != null && trans.value()) {
					continue;
				}

				ExlColumn rowField = field.getAnnotation(ExlColumn.class);
				String name = field.getName();
				if (rowField != null && !"".equals(rowField.value())) {
					name = rowField.value();
				}
				heads.add(name);
			} catch (Exception e) {
				log.warn("获取{}类的属性{}的读方法出错！原因：{}", clazz.getName(), field.getName(), e.getMessage());
			}
		}
		return heads;
	}

	/**
	 * 根据Map设置Class属性，使用{@link ExlColumn} 注解}
	 *
	 * @author LiuJunGuang
	 * @param map 属性值Map
	 * @param clazz 需要生成的JavaBean对象
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 字典转换器，可选
	 * @return 如果Map为空或者null，则返回 null,如果转换失败则返回null
	 */
	public static <T> T toBean(Map<String, String> map, Class<T> clazz, DictionaryConverter... converters) {
		return toBean(map, clazz, true, converters);
	}

	/**
	 * 根据Map设置Class属性，使用{@link ExlColumn} 注解}
	 *
	 * @author LiuJunGuang
	 * @param map 属性值Map
	 * @param clazz 需要生成的JavaBean对象
	 * @param ignoreException 是否忽略转换异常，true 忽略，异常之后返回null，否则抛出异常
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return 如果Map为空或者null，则返回 null
	 */
	public static <T> T toBean(Map<String, String> map, Class<T> clazz, boolean ignoreException,
			DictionaryConverter... converters) {
		if (map == null || map.isEmpty()) {
			return null;
		}

		Field[] fields = ReflectionUtils.getFields(clazz);// 根据Class对象获得属性 私有的也可以获得
		T obj;
		try {
			obj = clazz.getConstructor().newInstance();
		} catch (Exception e1) {
			throw new ConversionException("生成javaBean失败！", e1);
		}
		String[] keys = map.keySet().toArray(new String[0]);
		for (Field field : fields) {
			try {
				String fieldName = field.getName();
				// 没有Set方法直接跳过该属性
				if (!ReflectionUtils.hasWriteMethod(clazz, fieldName)) {
					continue;
				}

				// 添加忽略注解也直接跳过
				ExlTransient trans = field.getAnnotation(ExlTransient.class);// 需要忽略的属性
				if (trans != null && trans.value()) {
					continue;
				}

				ExlColumn rowField = field.getAnnotation(ExlColumn.class);
				if(rowField == null) {
					continue;
				}
				
				String name = field.getName();
				if (rowField != null) {
					name = rowField.value();
					if (name == null || "".equals(name)) {
						int index = rowField.index();
						if (index >= 0) {
							name = keys[index];
						}
					}
				}
				if (name == null || "".equals(name)) {
					name = field.getName();
				}
				String value = MapUtils.getString(map, name);
				value = getDictionaryValue(rowField, value, true);
				value = ConverterUtils.converter(field.getName(), rowField.index(), value, converters);
				ReflectionUtils.setProperty(obj, field.getName(), value);
			} catch (Exception e) {
				if (ignoreException) {
					log.warn(e.getMessage(), e);
				} else {
					log.error(e.getMessage(), e);
					throw new ConversionException("生成javaBean失败！", e);
				}
			}
		}
		return obj;
	}

	/**
	 * 将对象列表转换成Map列表，并忽略异常信息
	 *
	 * @author LiuJunGuang
	 * @param list 对象列表
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return List 中封装的是Map
	 */
	public static <T> List<Map<String, String>> toMap(List<T> list, DictionaryConverter... converters) {
		return toMap(list, true, converters);
	}

	/**
	 * 将对象列表转换成Map列表
	 *
	 * @author LiuJunGuang
	 * @param list 对象列表
	 * @param ignoreException true 忽略转换中的异常，false 抛出转换中的异常信息
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return List 中封装的是Map
	 */
	public static <T> List<Map<String, String>> toMap(List<T> list, boolean ignoreException,
			DictionaryConverter... converters) {
		if (list == null || list.isEmpty()) {
			return null;
		}

		List<Map<String, String>> result = new LinkedList<>();
		for (T t : list) {
			Map<String, String> map = toMap(t, ignoreException, converters);
			if (map != null) {
				result.add(map);
			}
		}
		return result;
	}

	/**
	 * 将Java对象转换成Map对象,并忽略异常信息
	 *
	 * @author LiuJunGuang
	 * @param obj 对象
	 * @param <T> clazz 指定的类型，任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return Map 如果属性有注解，则key - 为Row 注解中的名称，value 为属性值
	 */
	public static <T> Map<String, String> toMap(T obj, DictionaryConverter... converters) {
		return toMap(obj, true, converters);
	}

	/**
	 * 将Java对象转换成Map对象
	 *
	 * @author LiuJunGuang
	 * @param obj 对象
	 * @param ignoreException true 忽略转换中的异常，false 抛出转换中的异常信息
	 * @param <T> 任意对象类型
	 * @param converters 可选，参数类型转换器
	 * @return Map 如果属性有注解，则key - 为Row 注解中的名称，value 为属性值
	 */
	public static <T> Map<String, String> toMap(T obj, boolean ignoreException, DictionaryConverter... converters) {
		if (obj == null) {
			if (ignoreException) {
				return null;
			} else {
				throw new IllegalArgumentException("obj must not be null!");
			}
		}

		try {
			Field[] fields = ReflectionUtils.getFields(obj.getClass());// 根据Class对象获得属性 私有的也可以获得
			Map<String, String> map = new LinkedHashMap<>();
			Arrays.sort(fields, fieldComparator);// 属性按照index 排序
			int index = 0;
			for (Field field : fields) {
				index++;
				String fieldName = field.getName();
				// 没有Get方法直接跳过该属性
				if (!ReflectionUtils.hasReadMethod(obj.getClass(), fieldName)) {
					continue;
				}

				// 需要忽略的属性
				ExlTransient trans = field.getAnnotation(ExlTransient.class);
				if (trans != null && trans.value()) {
					continue;
				}

				ExlColumn rowField = field.getAnnotation(ExlColumn.class);
				String name = field.getName();
				if (rowField != null && !"".equals(rowField.value())) {
					name = rowField.value();
				}

				String value = ReflectionUtils.getProperty(obj, fieldName);
				value = getDictionaryValue(rowField, value, false);
				int indexTemp = rowField == null ? index : rowField.index();
				value = ConverterUtils.converter(fieldName, indexTemp, value, converters);
				map.put(name, value);
			}
			return map;
		} catch (Exception e) {
			if (ignoreException) {
				log.warn(e.getMessage(), e);
				return null;
			} else {
				log.error(e.getMessage(), e);
				throw new ConversionException("生成Map失败！", e);
			}
		}
	}

}
