# Helm Chart for Scalerkart

Helm is a package manager for Kubernetes that simplifies the deployment and management of applications. It uses charts (pre-configured Kubernetes resources) to define, install, and upgrade applications. Below are the detailed steps and code to implement Helm charts for the E-Commerce Application.

## 📖 Table of Contents
1. [What is Helm?](#1-what-is-helm)
2. [Why Use Helm?](#2-why-use-helm)
3. [Helm Chart Structure](#3-helm-chart-structure)
4. [Creating a Helm Chart](#4-creating-a-helm-chart)
5. [Customizing the Helm Chart](#5-customizing-the-helm-chart)
6. [Deploying with Helm](#6-deploying-with-helm)
7. [Upgrading and Rolling Back](#7-upgrading-and-rolling-back)
8. [Additional Resources](#8-additional-resources)
9. [Key Improvements from Original Manifests](#9-key-improvements-from-original-manifests)

---
<a name="1-what-is-helm"></a>
## 1️. What is Helm?
Helm is a tool for managing Kubernetes applications. It uses charts (collections of pre-configured Kubernetes resources) to define, install, and upgrade applications. Helm simplifies the deployment process by templating Kubernetes manifests and managing dependencies.

---
<a name="2-why-use-helm"></a>
## 2️. Why Use Helm?
- **Simplifies Deployment**: Helm reduces the complexity of deploying multiple Kubernetes resources.
- **Reusability**: Helm charts can be reused across environments.
- **Versioning**: Helm supports versioning of deployments.
- **Rollback**: Easily roll back to a previous version if something goes wrong.
- **Dependency Management**: Helm can manage dependencies between services.

---
<a name="3-helm-chart-structure"></a>
## 3️. Helm Chart Structure
A Helm chart has the following structure:

```plaintext
helm-chart/
├── Chart.yaml
├── values.yaml
├── templates/
│   ├── _helpers.tpl
│   ├── namespace.yaml
│   ├── postgres/
│   │   ├── statefulset.yaml
│   │   ├── service.yaml
│   │   ├── pv.yaml
│   ├── mongodb/
│   │   ├── deployment.yaml
│   │   ├── service.yaml
│   │   ├── pvc.yaml
│   │   ├── secret.yaml
│   │   ├── configmap.yaml
│   ├── services/
│   │   ├── api-gateway.yaml
│   │   ├── authentication.yaml
│   │   ├── cart.yaml
│   │   ├── order.yaml
│   │   ├── product.yaml
│   │   ├── frontend.yaml
└── README.md
```

---
<a name="4-creating-a-helm-chart"></a>
## 4️. Creating a Helm Chart

### Step 1: Install Helm
```bash
# Install Helm CLI
curl https://raw.githubusercontent.com/helm/helm/main/scripts/get-helm-3 | bash
```

### Step 2: Create a Helm Chart
```bash
# Create a new Helm chart
helm create helm-chart
```
This will generate a basic chart structure in the `helm-chart` directory.

---
<a name="5-customizing-the-helm-chart"></a>
## 5️. Customizing the Helm Chart

### Step 1: Update `Chart.yaml`
Edit the `Chart.yaml` file to include metadata about your application:

```yaml
apiVersion: v2
name: scalerkart
description: A Helm chart for Scalerkart Application
version: 1.0.0
appVersion: 1.0.0
dependencies:
  - name: postgresql
    version: 12.1.2
    repository: https://charts.bitnami.com/bitnami
  - name: mongodb
    version: 13.6.0
    repository: https://charts.bitnami.com/bitnami
```

### Step 2: Update `values.yaml`
The `values.yaml` file contains default configuration values for your application. Customize it for the E-Commerce Application:

```yaml
# values.yaml
global:
  namespace: scalerkart
  imageRegistry: us-central1-docker.pkg.dev/scalerkart-453310/scalerkart-artifact-registry

postgresql:
  enabled: true
  global:
    postgresql:
      auth:
        database: "scalerkart_ecom"
        username: "scalerkart_user"
        password: "securepassword"
  persistence:
    size: 1Gi
    storageClass: "standard"

mongodb:
  enabled: true
  auth:
    rootPassword: "securepassword"
  persistence:
    size: 5Gi
    storageClass: "standard"

services:
  apiGateway:
    image: api-gateway
    tag: latest
    serviceType: LoadBalancer
    staticIP: 34.123.134.182
    resources:
      requests:
        cpu: "300m"
        memory: "200Mi"
      limits:
        cpu: "600m"
        memory: "400Mi"

  authentication:
    image: authentication
    tag: latest
    dbName: "auth"
    port: 8080
    # ... similar structure for other services

probes:
  liveness:
    initialDelaySeconds: 30
    periodSeconds: 10
    timeoutSeconds: 5
    failureThreshold: 5
  readiness:
    initialDelaySeconds: 30
    periodSeconds: 10
    timeoutSeconds: 5
    failureThreshold: 5
```

### Step 3: Update Templates
Replace the default templates in the `templates/` directory with your Kubernetes manifests. For example:

#### `templates/services/authentication.yaml`
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: {{ .Chart.Name }}-authentication
  namespace: {{ .Values.global.namespace }}
  labels:
    app: {{ .Chart.Name }}-authentication
    chart: {{ .Chart.Name }}-{{ .Chart.Version }}
spec:
  replicas: 1
  selector:
    matchLabels:
      app: {{ .Chart.Name }}-authentication
  template:
    metadata:
      labels:
        app: {{ .Chart.Name }}-authentication
    spec:
      containers:
          - name: authentication
            image: "{{ .Values.global.imageRegistry }}/{{ .Values.services.authentication.image }}:{{ .Values.services.authentication.tag }}"
            imagePullPolicy: Always
            ports:
              - containerPort: {{ .Values.services.authentication.port }}
            env:
              - name: POSTGRES_HOST
                value: "{{ .Chart.Name }}-postgresql.{{ .Values.global.namespace }}.svc.cluster.local"
              - name: POSTGRES_DB
                value: "{{ .Values.postgresql.global.postgresql.auth.database }}_{{ .Values.services.authentication.dbName }}"
              - name: POSTGRES_USER
                valueFrom:
                  secretKeyRef:
                    name: {{ .Chart.Name }}-postgresql
                    key: postgresql-username
              - name: POSTGRES_PASSWORD
                valueFrom:
                  secretKeyRef:
                    name: {{ .Chart.Name }}-postgresql
                    key: postgresql-password
            livenessProbe:
              httpGet:
                path: /api/authentication/health
                port: {{ .Values.services.authentication.port }}
            {{- toYaml .Values.probes.liveness | nindent 12 }}
            readinessProbe:
              httpGet:
                path: /api/authentication/health
                port: {{ .Values.services.authentication.port }}
            {{- toYaml .Values.probes.readiness | nindent 12 }}
            resources:
              {{- toYaml .Values.services.authentication.resources | nindent 12 }}
```

#### `templates/postgres/secret.yaml`
```yaml
apiVersion: v1
kind: Secret
metadata:
  name: {{ .Chart.Name }}-postgresql
  namespace: {{ .Values.global.namespace }}
type: Opaque
data:
  postgresql-username: {{ .Values.postgresql.global.postgresql.auth.username | b64enc }}
  postgresql-password: {{ .Values.postgresql.global.postgresql.auth.password | b64enc }}
```

---
<a name="6-deploying-with-helm"></a>
## 6️. Deploying with Helm

### Step 1: Install the Chart
```bash
# Install the Helm chart
helm upgrade --install ecommerce ./ecommerce-chart \
  --namespace scalerkart \
  --create-namespace \
  --set postgresql.persistence.size=2Gi \
  --set mongodb.auth.rootPassword=strongpassword
```

### Step 2: Verify the Deployment
```bash
# List all releases
helm list -n scalerkart

# Check Kubernetes resources
kubectl get all -n scalerkart
```

---
<a name="7-upgrading-and-rolling-back"></a>
## 7️. Upgrading and Rolling Back

### Upgrade the Chart
```bash
# Upgrade the Helm chart
helm upgrade ecommerce ./helm-chart -n scalerkart
```

### Rollback to a Previous Version
```bash
# List release history
helm history ecommerce

# Rollback to a specific revision
helm rollback ecommerce 1 -n scalerkart
```

---
<a name="8-additional-resources"></a>
## 8. Additional Resources
- [Helm Documentation](https://helm.sh/docs/)
- [Kubernetes Documentation](https://kubernetes.io/docs/home/)
- [Helm Chart Best Practices](https://helm.sh/docs/chart_best_practices/)

---
<a name="9-key-improvements-from-original-manifests"></a>
## 9. Key Improvements from Original Manifests
- **Centralized Configuration**: All environment-specific values in `values.yaml`
- **Reduced Redundancy**: Common patterns abstracted into templates
- **Security Enhancements**: Proper secret management
- **Version Control**: Helm's built-in versioning and rollback
- **Dependency Management**: Using official charts for databases
- **Scalability**: Easy to add new services using existing templates
- **Multi-environment Support**: Different values files for dev/staging/prod

---