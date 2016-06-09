package org.jccdemo.dsf.javanio.model;

public abstract class BaseMsg {
	public static enum MsgType{
		Heart(1),
		Business(2);
		private int msgType;
		
		private MsgType(int msgType){
			this.msgType=msgType;
		}
		
		public int getMsgType() {
			return msgType;
		}
		
	}
	public abstract int getMsgType();
}
