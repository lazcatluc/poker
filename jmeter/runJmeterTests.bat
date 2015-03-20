for %%f in (*.jmx) do (
	
	del "%%~nf.jmx.log"
	
	jmeter.bat -n -t %%f -l "%%~nf.jmx.log"
	
	find /c "true" "%%~nf.jmx.log"

)
