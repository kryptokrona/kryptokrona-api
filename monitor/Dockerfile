FROM node:18-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm install --frozen-lockfile
COPY . .
RUN npm run build

FROM node:18-alpine
WORKDIR /app
COPY --from=builder /app/build build/
COPY --from=builder /app/node_modules node_modules/
COPY --from=builder /app/index.js index.js
COPY --from=builder /app/package.json package.json
EXPOSE 3000
ENV NODE_ENV=production
CMD [ "node", "index.js" ]
