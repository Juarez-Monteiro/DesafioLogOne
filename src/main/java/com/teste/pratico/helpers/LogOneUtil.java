package com.teste.pratico.helpers;

import java.util.Collection;
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
	
	public static boolean nuloOuVazio(Map<?,?> arg) {
		return (Objects.isNull(arg) || arg.isEmpty());
	}
	
	public static <T extends Object> boolean nuloOuVazio(T arg) {
		return Objects.isNull(arg);
	}
}
