# 🔒 SmartHome - Configuração de Segurança

## ✅ Configuração Implementada

### Arquivos de Configuração
- **`.env`** - Credenciais reais (NÃO vai para o Git)
- **`.env.example`** - Modelo para outros desenvolvedores
- **`.gitignore`** - Protege arquivos sensíveis
- **`application.properties`** - Configuração base com valores padrão
- **`application-dev.properties`** - Configuração específica para desenvolvimento

### Estrutura de Segurança
```
📁 smarthome/
├── .env                    ← Credenciais reais (PROTEGIDO)
├── .env.example           ← Modelo para outros devs
├── .gitignore             ← Protege arquivos sensíveis
├── start-backend.sh       ← Script para iniciar aplicação
└── backend-smarthome/
    └── src/main/resources/
        ├── application.properties      ← Configuração base
        └── application-dev.properties  ← Configuração dev
```

## 🚀 Como Usar

### Opção 1: Script Automático (Recomendado)
```bash
./start-backend.sh
```

### Opção 2: Manual
```bash
# 1. Iniciar PostgreSQL
cd docker
docker-compose up -d postgres

# 2. Iniciar Backend
cd ../backend-smarthome
./mvnw spring-boot:run
```

### Opção 3: Com Profile Dev
```bash
cd backend-smarthome
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## 🔧 Para Outros Desenvolvedores

1. **Copiar arquivo de exemplo:**
   ```bash
   cp .env.example .env
   ```

2. **Editar credenciais:**
   ```bash
   nano .env
   ```

3. **Iniciar aplicação:**
   ```bash
   ./start-backend.sh
   ```

## 🛡️ Segurança Implementada

- ✅ Credenciais removidas do código
- ✅ Arquivos sensíveis protegidos pelo .gitignore
- ✅ Variáveis de ambiente configuradas
- ✅ Docker Compose usando variáveis
- ✅ Configuração para diferentes ambientes

## 📝 Credenciais Atuais

- **Banco:** PostgreSQL (localhost:5432)
- **Database:** smart_home_bda
- **User:** postgres
- **Password:** f@123!09f
- **JWT Secret:** Configurado
- **Porta:** 8081

## ⚠️ Importante

- **NUNCA** commite o arquivo `.env`
- **SEMPRE** use o `.env.example` como modelo
- **MUDE** as credenciais em produção
- **USE** profiles diferentes para cada ambiente
