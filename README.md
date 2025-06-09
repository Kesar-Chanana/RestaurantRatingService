# Restaurant Rating Service - Cloud Native Java App 

This is a simple Java-based Restaurant Rating Service that I containerized using **Docker** and deployed on a **Kubernetes cluster** with **Minikube** for local testing. The infrastructure is partly managed via **Terraform** (Infrastructure as Code).

The goal of this project is to demonstrate cloud-native application patterns:
- Containerization
- Kubernetes orchestration
- Infrastructure as Code (IaC)
- (Optional) CI/CD with GitHub Actions

---

## 🛠️ Tech Stack

- Java 17
- Docker 🐳
- Kubernetes (Minikube)
- Terraform
- GitHub Actions (optional - planned)
- PowerShell / Windows

---

## 🚀 Features

✅ Java REST API service  
✅ Dockerized application  
✅ Kubernetes Deployment + Service (NodePort)  
✅ Terraform-managed Kubernetes Namespace  
✅ Local development using Minikube  

---

## 🚀 How to Run Locally

### 1️⃣ Build Docker Image

```bash
docker build -t restaurant-rating-app:latest .
```

---

### Start your Minikube cluster

```bash
minikube start
```

---

### Load environment to talk to Minikube Docker
```bash
minikube -p minikube docker-env | Invoke-Expression
```

### Deploy Kubernetes resources

```bash
kubectl apply -f k8s-deployment.yaml
kubectl apply -f service.yaml
```

---

### Terraform - create Namespace
```bash
terraform init
terraform apply -auto-approve
```
### Access service

```bash
minikube service restaurant-rating-service --url
```

---

### 5. Provision infrastructure with Terraform

```bash
terraform init
terraform apply -auto-approve
```

---

## ✅ Prerequisites

- Docker installed and running
- Minikube installed
- kubectl installed
- Terraform installed
- GitHub Actions configured (if using CI/CD)

---

## 🛠️ Technologies Used

- Java Spring Boot
- Docker
- Kubernetes
- Terraform