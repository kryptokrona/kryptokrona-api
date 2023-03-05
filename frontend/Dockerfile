FROM node:16-alpine AS builder
WORKDIR /app
COPY package*.json .
RUN yarn install --frozen-lockfile
COPY . .
RUN yarn build

FROM mhart/alpine-node:slim-16
WORKDIR /app
COPY --from=builder /app/build build/
COPY --from=builder /app/node_modules node_modules/
COPY package.json .
EXPOSE 3000
ENV NODE_ENV=production
CMD [ "node", "build" ]