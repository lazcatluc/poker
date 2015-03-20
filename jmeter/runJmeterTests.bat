for %%f in (*.jmx) do (
	del "%%~nf.log"
	jmeter.bat -n -t %%f -l "%%%~nf.log"
	find /c "true" "%%~nf.log"
)
