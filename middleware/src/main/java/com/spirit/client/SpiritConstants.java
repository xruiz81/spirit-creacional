package com.spirit.client;

import java.math.BigDecimal;
import java.text.DecimalFormat;


public class SpiritConstants {
	/* Lenght fields */
	private static final int MAX_LENGHT_DECIMAL_VALUES = 22;
	private static final int MAX_LENGHT_INTEGER_VALUES = 10;
	/* Special characters */
	private static final String PERIOD_CHARACTER = ".";
	private static final String PLACEHOLDER_CHARACTER = "-";
	private static final String SPLIT_CHARACTER = "_";
	private static final String COLON_CHARACTER = ":";
	private static final String SEMICOLON_CHARACTER = ";";
	private static final String EMPTY_CHARACTER = "";
	private static final String COMMA_CHARACTER = ",";
	private static final String SLASH_CHARACTER = "/";
	private static final String BLANK_SPACE_CHARACTER = " ";
	private static final String COMMENT_SEPARATOR_CHARACTER = ";";
	private static final String PENDING_SAVE_CHANGES_CHARACTER = "*";
	private static final String LESS_THAN_CHARACTER = "<";
	private static final String GREATER_THAN_CHARACTER = ">";
	private static final String WILDCARD_CHARACTER = "%";
	private static final String PLUS_OPERATOR = "+";
	private static final String MINUS_OPERATOR = "-";
	/* Common words */
	private static final String OPTION_YES = "Sí";
	private static final String OPTION_NO = "No";
	private static final Object[] OPTIONS = {OPTION_YES, OPTION_NO};
	private static final String CUSTOMER_WALLET_TYPE = "CLIENTE";
	private static final String PROVIDER_WALLET_TYPE = "PROVEEDOR";
	private static final String WITHOUT_SERIAL_NUMBER = "S/N";
	private static final String UNAUTHORIZED_ACCOUNTING_ENTRY = "PRE-ASIENTO";
	private static final String AUTHORIZED_ACCOUNTING_ENTRY = "ASIENTO";
	private static final String FIXED_VALUE_IN_ACCOUNTING_ENTRY_TEMPLATE = "FIXVAL";
	private static final String CALCULATED_CROSSING_VALUE_IN_ACCOUNTING_ENTRY_TEMPLATE = "CRSVAL";
	private static final String ISSUED_CHECK = "EMITIDO";
	private static final String ISSUED_CHECK_WALLET_ORIGIN = "CARTERA";
	private static final String CANCELED_CHECK = "COBRADO";
	private static final String BEGINNING_HTML_TAG = "<html>";
	private static final String ENDING_HTML_TAG = "</html>";
	private static final String RETENTION_IVA_MNEMONIC = "RETEIVA";
	private static final String RETENTION_IVA_TYPE = "I";
	private static final String RETENTION_INCOME_MNEMONIC = "RETERENTA";
	private static final String RETENTION_INCOME_TYPE = "R";
	private static final String ACTIVE_STATUS = "ACTIVE";
	private static final String INACTIVE_STATUS = "INACTIVE";
	private static final String BUDGET_REFERENCE_TYPE = "P";
	private static final String MEDIA_PLAN_REFERENCE_TYPE = "I";
	private static final String NONE_REFERENCE_TYPE = "N";
	/* Constant values */
	private static final Object NULL_VALUE = null;
	private static final BigDecimal ZERO_VALUE = BigDecimal.ZERO;
	private static final int PROCESS_OK = 0;
	private static final int PROCESS_ERROR = -1;
	private static final int SERIAL_NUMBER_SPLIT_POSITION = 2;
	private static final int JDIALOG_CHECK_WIDTH = 600;
	private static final int JDIALOG_CHECK_HEIGHT = 650;
	/* Java classes names */
	private static final String JTEXTFIELD_CLASS_NAME = "javax.swing.JTextField";
	private static final String JFORMATTEDTEXTFIELD_CLASS_NAME = "javax.swing.JFormattedTextField";
	private static final String JCOMBOBOX_CLASS_NAME = "javax.swing.JComboBox";
	private static final String JTABLE_CLASS_NAME = "javax.swing.JTable";
	private static final String BIG_DECIMAL_CLASS_NAME = "java.math.BigDecimal";
	/* Format patterns */
	private static final String DATE_FORMAT_PATTERN = "dd-MM-yyyy";
	private static final DecimalFormat DECIMAL_FORMAT_PATTERN = new DecimalFormat("#,##0.00");
	
	public static int getMaxLenghtDecimalValues() {
		return MAX_LENGHT_DECIMAL_VALUES;
	}
	public static int getMaxLenghtIntegerValues() {
		return MAX_LENGHT_INTEGER_VALUES;
	}
	public static String getPeriodCharacter() {
		return PERIOD_CHARACTER;
	}
	public static String getPlaceholderCharacter() {
		return PLACEHOLDER_CHARACTER;
	}
	public static String getColonCharacter() {
		return COLON_CHARACTER;
	}
	public static String getSemicolonCharacter() {
		return SEMICOLON_CHARACTER;
	}
	public static String getEmptyCharacter() {
		return EMPTY_CHARACTER;
	}
	public static String getCommaCharacter() {
		return COMMA_CHARACTER;
	}
	public static String getSlashCharacter() {
		return SLASH_CHARACTER;
	}
	public static String getBlankSpaceCharacter() {
		return BLANK_SPACE_CHARACTER;
	}
	public static String getCommentSeparatorCharacter() {
		return COMMENT_SEPARATOR_CHARACTER;
	}
	public static String getPendingSaveChangesCharacter() {
		return PENDING_SAVE_CHANGES_CHARACTER;
	}
	public static String getLessThanCharacter() {
		return LESS_THAN_CHARACTER;
	}
	public static String getGreaterThanCharacter() {
		return GREATER_THAN_CHARACTER;
	}
	public static String getWildcardCharacter() {
		return WILDCARD_CHARACTER;
	}
	public static String getSplitCharacter() {
		return SPLIT_CHARACTER;
	}
	public static String getPlusOperator() {
		return PLUS_OPERATOR;
	}
	public static String getMinusOperator() {
		return MINUS_OPERATOR;
	}
	public static String getOptionYes() {
		return OPTION_YES;
	}
	public static String getOptionNo() {
		return OPTION_NO;
	}
	public static Object[] getOptions() {
		return OPTIONS;
	}
	public static String getCustomerWalletType() {
		return CUSTOMER_WALLET_TYPE;
	}
	public static String getProviderWalletType() {
		return PROVIDER_WALLET_TYPE;
	}
	public static String getWithoutSerialNumber() {
		return WITHOUT_SERIAL_NUMBER;
	}
	public static String getUnauthorizedAccountingEntry() {
		return UNAUTHORIZED_ACCOUNTING_ENTRY;
	}
	public static String getAuthorizedAccountingEntry() {
		return AUTHORIZED_ACCOUNTING_ENTRY;
	}
	public static String getFixedValueInAccountingEntryTemplate() {
		return FIXED_VALUE_IN_ACCOUNTING_ENTRY_TEMPLATE;
	}
	public static String getCalculatedCrossingValueInAccountingEntryTemplate() {
		return CALCULATED_CROSSING_VALUE_IN_ACCOUNTING_ENTRY_TEMPLATE;
	}
	public static String getIssuedCheck() {
		return ISSUED_CHECK;
	}
	public static String getIssuedCheckWalletOrigin() {
		return ISSUED_CHECK_WALLET_ORIGIN;
	}
	public static String getCanceledCheck() {
		return CANCELED_CHECK;
	}
	public static String getBeginningHtmlTag() {
		return BEGINNING_HTML_TAG;
	}
	public static String getEndingHtmlTag() {
		return ENDING_HTML_TAG;
	}
	public static String getRetentionIvaMnemonic() {
		return RETENTION_IVA_MNEMONIC;
	}
	public static String getRetentionIncomeMnemonic() {
		return RETENTION_INCOME_MNEMONIC;
	}
	public static String getRetentionIvaType() {
		return RETENTION_IVA_TYPE;
	}
	public static String getRetentionIncomeType() {
		return RETENTION_INCOME_TYPE;
	}
	public static String getActiveStatus() {
		return ACTIVE_STATUS;
	}
	public static String getInactiveStatus() {
		return INACTIVE_STATUS;
	}
	public static String getBudgetReferenceType() {
		return BUDGET_REFERENCE_TYPE;
	}
	public static String getMediaPlanReferencieType() {
		return MEDIA_PLAN_REFERENCE_TYPE;
	}
	public static String getNoneReferenceType() {
		return NONE_REFERENCE_TYPE;
	}
	public static Object getNullValue() {
		return NULL_VALUE;
	}
	public static BigDecimal getZeroValue() {
		return ZERO_VALUE;
	}
	public static int getProcessOk() {
		return PROCESS_OK;
	}
	public static int getProcessError() {
		return PROCESS_ERROR;
	}
	public static int getSerialNumberSplitPosition() {
		return SERIAL_NUMBER_SPLIT_POSITION;
	}
	public static int getJdialogCheckWidth() {
		return JDIALOG_CHECK_WIDTH;
	}
	public static int getJdialogCheckHeight() {
		return JDIALOG_CHECK_HEIGHT;
	}
	public static String getJtextfieldClassName() {
		return JTEXTFIELD_CLASS_NAME;
	}
	public static String getJformattedtextfieldClassName() {
		return JFORMATTEDTEXTFIELD_CLASS_NAME;
	}
	public static String getJcomboboxClassName() {
		return JCOMBOBOX_CLASS_NAME;
	}
	public static String getJtableClassName() {
		return JTABLE_CLASS_NAME;
	}
	public static String getDateFormatPattern() {
		return DATE_FORMAT_PATTERN;
	}
	public static DecimalFormat getDecimalFormatPattern() {
		return DECIMAL_FORMAT_PATTERN;
	}
	public static String getBigDecimalClassName() {
		return BIG_DECIMAL_CLASS_NAME;
	}
}
