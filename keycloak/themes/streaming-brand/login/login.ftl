<#macro registrationLayout>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8" />
    <title>Iniciar sesión</title>
    <link rel="stylesheet" href="${url.resourcesPath}/css/styles.css" />
</head>
<body>
    <div class="kc-wrapper">
        <div class="login-box">
            <div class="logo-box">
                <img src="${url.resourcesPath}/../../resources/img/logo.png" alt="Streaming Logo" class="logo" />
                <h1 class="app-title">STREAMING APP</h1>
            </div>

            <#nested "form">
            </#nested>

            <#if realm.password && realm.registrationAllowed>
                <div class="signup-link">
                    <a href="${url.registrationUrl}">Crear cuenta</a>
                </div>
            </#if>
        </div>
    </div>
</body>
</html>
</#macro>