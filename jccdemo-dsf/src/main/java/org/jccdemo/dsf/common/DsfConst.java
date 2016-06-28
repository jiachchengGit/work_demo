package org.jccdemo.dsf.common;

public class DsfConst {
	public static final int PORT=8077;
	public static final String IP="127.0.0.1";
	public static final int MSG_HEADER_LENGTH=147;
	public static final int SERVER_PORT=8077;
	public static final String TEST_VERSION_ALIAS="TEST_VERSION_ALIAS";
	public interface ServerStatus{
		public int RUN=1;
		public int STOP=2;
		public int DELETE=3;
	}
	
	public interface MsgType{
		public int HeartBeat=1;
		public int MethodCall=2;
	}
}
