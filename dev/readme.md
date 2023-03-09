# Инструкция по запуску docker:

1. `cd dev`
2. `docker-compose build`
3. `docker-compose up -d`

![Работа контейнеров в docker](resources/docker-works.png)

# Инструкция по запуску в minikube:

1. `minikube start`
2. `eval $(minikube docker-env)`
3. `DOCKER_BUILDKIT=1 docker build -t <handyman | rancher | landscape> .` в корне каждого сервиса.
4. `cd dev`
5. `kubectl apply -f kube`
