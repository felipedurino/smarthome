#!/bin/bash

echo "🚀 Iniciando SmartHome Backend..."
echo "📁 Navegando para o diretório backend..."
cd backend-smarthome

echo "🔧 Verificando se o PostgreSQL está rodando..."
if ! docker ps | grep -q "smarthome-postgres"; then
    echo "⚠️  PostgreSQL não está rodando. Iniciando..."
    cd ../docker
    docker-compose up -d postgres
    echo "⏳ Aguardando PostgreSQL inicializar..."
    sleep 5
    cd ../backend-smarthome
fi

echo "🏃 Executando aplicação Spring Boot..."
./mvnw spring-boot:run

echo "✅ Backend finalizado!"
