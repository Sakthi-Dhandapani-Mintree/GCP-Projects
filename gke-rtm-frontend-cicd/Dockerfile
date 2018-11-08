FROM node:8

WORKDIR /usr/src/site/
COPY package*.json ./
RUN npm install

COPY . .

EXPOSE 8090

CMD ["npm", "start"]
