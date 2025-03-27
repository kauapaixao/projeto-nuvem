# Usa uma imagem do Node.js
FROM node:20

# Define o diretório de trabalho
WORKDIR /app

# Copia os arquivos do projeto para o container
COPY . .

# Instala as dependências
RUN npm install

# Constrói a aplicação Next.js
RUN npm run build

# Expõe a porta do Next.js
EXPOSE 8080

# Comando para iniciar o Next.js
CMD ["npm", "run", "start"]
