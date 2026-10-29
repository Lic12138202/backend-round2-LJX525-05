# Test API script for data-workshop
# Usage: powershell -ExecutionPolicy Bypass -File .\test-api.ps1

$base = "http://127.0.0.1:8089/api/records"
$enc = [System.Text.Encoding]::UTF8

function Post($name, $json) {
    Write-Host "===== $name =====" -ForegroundColor Cyan
    try {
        $resp = Invoke-WebRequest -Uri $base -Method Post `
            -ContentType "application/json; charset=utf-8" `
            -Body $enc.GetBytes($json) -UseBasicParsing
        Write-Host "HTTP $($resp.StatusCode) | $($resp.Content)"
    } catch {
        $r = $_.Exception.Response
        if ($r) {
            Write-Host "HTTP $([int]$r.StatusCode)" -ForegroundColor Yellow
            $sr = New-Object System.IO.StreamReader($r.GetResponseStream())
            Write-Host $sr.ReadToEnd()
        } else {
            Write-Host "ERR: $_"
        }
    }
}

function Get-Req($name, $url) {
    Write-Host "===== $name =====" -ForegroundColor Cyan
    try {
        $resp = Invoke-WebRequest -Uri $url -UseBasicParsing
        Write-Host "HTTP $($resp.StatusCode) | $($resp.Content)"
    } catch {
        $r = $_.Exception.Response
        if ($r) {
            Write-Host "HTTP $([int]$r.StatusCode)" -ForegroundColor Yellow
            $sr = New-Object System.IO.StreamReader($r.GetResponseStream())
            Write-Host $sr.ReadToEnd()
        } else {
            Write-Host "ERR: $_"
        }
    }
}

Post "1-save-ok" '{"title":"t1","content":"Hello MySQL"}'
Post "2-save-ok" '{"title":"t2","content":"MyBatis insert"}'
Post "3-save-ok" '{"title":"t3","content":"index and page"}'
$urlPage = $base + "?page=1" + "&size=2"
Get-Req "4-page" $urlPage
Get-Req "5-by-id" "$base/1"
Get-Req "6-by-title" "$base/title?title=t2"
Post "7-empty-title-400" '{"title":"","content":"x"}'
$urlBad = $base + "?page=0" + "&size=5"
Get-Req "8-page0-400" $urlBad

Write-Host ""
Write-Host "ALL DONE." -ForegroundColor Green
