package com.autotune.common.data.system.info.device;

import com.autotune.analyzer.utils.AnalyzerConstants;

public interface DeviceHandler {
    public void addDevice(AnalyzerConstants.DeviceType deviceType, DeviceDetails deviceInfo);
    public void removeDevice(AnalyzerConstants.DeviceType deviceType, DeviceDetails deviceInfo);
    public void updateDevice(AnalyzerConstants.DeviceType deviceType, DeviceDetails deviceInfo);
}
