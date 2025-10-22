package co.edu.uco.nose.crosscutting.helpers;

public final class BooleanHelper {

	private static final boolean DEFAULT_VALUE = false;

	private BooleanHelper() {
	}

	public static boolean getDefault() {
		return DEFAULT_VALUE;
	}

	public static boolean getDefault(final Boolean value) {
		return ObjectHelper.getDefault(value, DEFAULT_VALUE);
	}
	
	public static boolean isDefault(final Boolean value) {
		return ObjectHelper.getDefault(value, getDefault()) == getDefault();
	}

}
