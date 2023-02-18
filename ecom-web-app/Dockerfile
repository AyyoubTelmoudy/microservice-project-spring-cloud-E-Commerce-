
FROM node:12.14-alpine

WORKDIR /app

COPY package*.json ./
RUN npm install
COPY . .
RUN npm install -g @angular/cli@10.0.4 && npm run build --prod
EXPOSE 4200
CMD ["npm", "start"]





