#!/bin/bash

echo "Démarrage de Paukemon avec Docker..."

if ! command -v docker &> /dev/null; then
    echo "❌ Docker n'est pas installé. Veuillez installer Docker d'abord."
    exit 1
fi

if ! command -v docker-compose &> /dev/null; then
    echo "❌ Docker Compose n'est pas installé. Veuillez installer Docker Compose d'abord."
    exit 1
fi

echo "Construction de l'image Docker..."
docker-compose build

echo "Lancement de l'application..."
docker-compose up -d

echo "Attente du démarrage de l'application..."
sleep 2

echo "Application démarrée !"
echo "Accédez à l'application sur: http://localhost:8080"
echo "🗄️Console H2 disponible sur: http://localhost:8080/h2-console"
echo ""
echo "Commandes utiles:"
echo "  - Voir les logs: docker-compose logs -f"
echo "  - Arrêter l'application: docker-compose down"
echo "  - Redémarrer: docker-compose restart" 