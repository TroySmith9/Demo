package json;

public class DataSyncProvider {

	private String userName;

	private String password;

	private String encode;

	private Long accountBookID;

	private String device;

	private String syncMode;

	private String appName;

	private String protocolVersion;

	private String appVersion;

	private String UDID;
	
//	private 

	public String getUserName() {
		return userName;
	}

	public void setUserName( String userName ) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword( String password ) {
		this.password = password;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode( String encode ) {
		this.encode = encode;
	}

	public Long getAccountBookID() {
		return accountBookID;
	}

	public void setAccountBookID( Long accountBookID ) {
		this.accountBookID = accountBookID;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice( String device ) {
		this.device = device;
	}

	public String getSyncMode() {
		return syncMode;
	}

	public void setSyncMode( String syncMode ) {
		this.syncMode = syncMode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName( String appName ) {
		this.appName = appName;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion( String protocolVersion ) {
		this.protocolVersion = protocolVersion;
	}

	public String getAppVersion() {
		return appVersion;
	}

	public void setAppVersion( String appVersion ) {
		this.appVersion = appVersion;
	}

	public String getUDID() {
		return UDID;
	}

	public void setUDID( String uDID ) {
		UDID = uDID;
	}

}
