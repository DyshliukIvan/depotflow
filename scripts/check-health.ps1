param(
    [string]$Url = "http://localhost:8080/actuator/health"
)

for ($attempt = 1; $attempt -le 3; $attempt++) {
    Write-Output "Attempt $attempt"
    $httpCode = curl.exe -sS -o NUL -w "%{http_code}" --max-time 5 "$Url"
    $curlExitCode = $LASTEXITCODE

    if (($curlExitCode -eq 0) -and ($httpCode -eq "200")) {
        Write-Output "Health check passed"
        exit 0
    }

    if ($attempt -lt 3) {Start-Sleep -Seconds 2}

}
Write-Output "Health check failed: curl=$curlExitCode, HTTP=$httpCode"
exit 1
