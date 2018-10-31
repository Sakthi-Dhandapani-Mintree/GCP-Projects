#gke-front-end application for Kubernetes 
FROM nginx:alpine
COPY . /usr/share/nginx/html
