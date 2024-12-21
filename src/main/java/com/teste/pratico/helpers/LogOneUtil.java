package com.teste.pratico.helpers;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class LogOneUtil {
	public static boolean nuloOuVazio(String arg) {
		return (Objects.isNull(arg) || arg.isEmpty());
	}

	public static boolean nuloOuVazio(Integer arg) {
		return (Objects.isNull(arg) || arg == 0);
	}

	public static boolean nuloOuVazio(Long arg) {
		return (Objects.isNull(arg) || arg == 0);
	}

	public static <T extends Object> boolean nuloOuVazio(Collection<T> arg) {
		return (Objects.isNull(arg) || arg.isEmpty());
	}

	public static boolean nuloOuVazio(Map<?, ?> arg) {
		return (Objects.isNull(arg) || arg.isEmpty());
	}

	public static <T extends Object> boolean nuloOuVazio(T arg) {
		return Objects.isNull(arg);
	}

	public static String convertDataToString(Date data) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = dateFormat.format(data);
		return dataFormatada;
	}
}
