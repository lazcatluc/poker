for %%f in (*.jmx) do (
	
	del "%%~nf.jmx.log"
	
	jmeter.bat -JHOST=%1 -JPORT=%2 -JNAME=%3 -n -t %%f -l "%%~nf.jmx.log"
	
	find /c "true" "%%~nf.jmx.log"

)
