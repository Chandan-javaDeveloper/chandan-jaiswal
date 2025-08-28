package com.jne.command_orchestrator.command;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DeviceCommand {
	 private String deviceId;
	 
	    @JsonProperty("command")
	    private String commandType;
	    private int priority;
	    private String trackingId;
	    private int retryCount;
	    
		public DeviceCommand() {
			super();
			// TODO Auto-generated constructor stub
		}

		public DeviceCommand(String deviceId, String commandType, int priority, String trackingId, int retryCount) {
			super();
			this.deviceId = deviceId;
			this.commandType = commandType;
			this.priority = priority;
			this.trackingId = trackingId;
			this.retryCount = retryCount;
		}

		public String getDeviceId() {
			return deviceId;
		}

		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}

		public String getCommandType() {
			return commandType;
		}

		public void setCommandType(String commandType) {
			this.commandType = commandType;
		}

		public int getPriority() {
			return priority;
		}

		public void setPriority(int priority) {
			this.priority = priority;
		}

		public String getTrackingId() {
			return trackingId;
		}

		public void setTrackingId(String trackingId) {
			this.trackingId = trackingId;
		}

		public int getRetryCount() {
			return retryCount;
		}

		public void setRetryCount(int retryCount) {
			this.retryCount = retryCount;
		}

		@Override
		public String toString() {
			return "DeviceCommand [deviceId=" + deviceId + ", commandType=" + commandType + ", priority=" + priority
					+ ", trackingId=" + trackingId + ", retryCount=" + retryCount + "]";
		}
	    
	    
	}
