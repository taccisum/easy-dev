CREATE TABLE foo ( id VARCHAR(64) NOT NULL PRIMARY KEY , bar1 VARCHAR(100), bar2 VARCHAR(100), created_on DATETIME NOT NULL, created_by VARCHAR(64) NOT NULL, updated_on DATETIME, updated_by VARCHAR(64), deleted_flag INT NOT NULL);