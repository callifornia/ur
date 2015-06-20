package com.prokopiv.web.config;

import com.prokopiv.web.exception.DataBaseException;

public interface InitializationDataBase {
	
	public void createTables() throws DataBaseException;
	
	public void uploadData() throws DataBaseException;
}
