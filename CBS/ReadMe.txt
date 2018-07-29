- to test the system core functionalities, run application on tomcat server
	#core functionalites
		- add custoemr, add account, deposit, wihdraw.

- to test Reconciliation Batch Processing,
	-  insert data in file 'import' t the transaction table
	- run class 'BatchTest' in package 'test' as a java application 
	- you may test scheduled job by uncommenting //@Scheduled(cron = "0 12 11 * * ?"), and set the time .
	