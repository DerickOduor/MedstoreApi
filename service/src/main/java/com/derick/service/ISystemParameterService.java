package com.derick.service;

import com.derick.domain.SystemParameter;

import java.util.List;

public interface ISystemParameterService {
    public SystemParameter getSystemParameter(String Name) throws Exception;
    public SystemParameter getSystemParameter(int Id) throws Exception;
    public List<SystemParameter> getSystemParameters() throws Exception;
}
