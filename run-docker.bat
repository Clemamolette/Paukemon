@echo off
echo Démarrage de Paukemon avec Docker...

docker --version >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker n'est pas installé. Veuillez installer Docker d'abord.
    pause
    exit /b 1
)

docker-compose --version >nul 2>&1
if errorlevel 1 (
    echo ❌ Docker Compose n'est pas installé. Veuillez installer Docker Compose d'abord.
    pause
    exit /b 1
)

echo Construction de l'image Docker...
docker-compose build

echo Lancement de l'application...
docker-compose up -d

echo Attente du démarrage de l'application...
timeout /t 2 /nobreak >nul

echo Application démarrée !
echo Accédez à l'application sur: http://localhost:8080
echo Console H2 disponible sur: http://localhost:8080/h2-console
echo.
echo Commandes utiles:
echo   - Voir les logs: docker-compose logs -f
echo   - Arrêter l'application: docker-compose down
echo   - Redémarrer: docker-compose restart
